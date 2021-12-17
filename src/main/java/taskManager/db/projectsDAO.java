package taskManager.db;

import taskManager.model.Assignment;
import taskManager.model.Project;
import taskManager.model.Task;
import taskManager.model.Teammate;

import javax.xml.crypto.dsig.spec.ExcC14NParameterSpec;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class projectsDAO {
    java.sql.Connection conn;

    public projectsDAO() {
        try {
            conn = DatabaseUtil.connect();
        } catch (Exception e) {
            e.printStackTrace();
            conn = null;
        }
    }

    public boolean addProject(Project project) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + "sys.Project" + " WHERE name = ?;");
            ps.setString(1, project.name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Project p = generateProject(rs);
                rs.close();
                return false;
            }
            ps = conn.prepareStatement("INSERT INTO " + "sys.Project" + " (idProject, name, isArchived) value(?,?,?);");
            ps.setString(1, project.idProject);
            ps.setString(2, project.name);
            ps.setInt(3, 0);
            ps.execute();
            return true;
        } catch (Exception e) {
            throw new Exception("Failed to create project: " + e.getMessage());
        }
    }

    public Project generateProject(ResultSet rs) throws Exception {
        String name = rs.getString("name");
        String idProject = rs.getString("idProject");
        int isArchived = rs.getInt("isArchived");
        return new Project(name, idProject, isArchived);
    }

    public Teammate generateTeammate(ResultSet rs) throws Exception {
        String name = rs.getString("name");
        String idProject = rs.getString("idProject");
        String idTeammate = rs.getString("idTeammate");
        return new Teammate(name, idTeammate, idProject);
    }

    public Task generateTask(ResultSet rs) throws Exception {
        String idTask = rs.getString("idTask");
        String idProject = rs.getString("idProject");
        String idParent = rs.getString("idParent");
        String name = rs.getString("name");
        int isComplete = rs.getInt("isComplete");
        int sequence = rs.getInt("sequence");
        return new Task(idTask, idProject, idParent, name, isComplete, sequence);
    }

    public Assignment generateAssignment(ResultSet rs) throws Exception {
        String idTask = rs.getString("idTask");
        String name = rs.getString("idTeammate");
        String idProject = rs.getString("idProject");
        return new Assignment(idTask, name, idProject);
    }

    public Project getProject(String name) throws Exception {
        try {
            Project project = null;
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + "sys.Project" + " WHERE name=?;");
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                project = generateProject(rs);
            }
            rs.close();
            ps.close();

            return project;

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Failed in getting project: " + e.getMessage());
        }
    }

    public List<Project> getAllProjects() throws Exception {
        List<Project> allProjects = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            String query = "SELECT * FROM " + "sys.Project" + ";";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Project p = generateProject(resultSet);
                allProjects.add(p);
            }
            resultSet.close();
            statement.close();
            return allProjects;
        } catch (Exception e) {
            throw new Exception("Failed in getting projects: " + e.getMessage());
        }
    }

    public List<Teammate> getAllTeammates(String project) throws Exception {
        List<Teammate> allTeammates = new ArrayList<>();
        try {
            List<Teammate> all = new ArrayList<>();
            Project p = getProject(project);
            if(p == null){
                throw new Exception("PROJECT DOESN'T EXIST");
            }
            Statement statement = conn.createStatement();
            String query = "SELECT * FROM " + "sys.Teammate" + ";";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Teammate t = generateTeammate(resultSet);
                all.add(t);
            }
            resultSet.close();
            statement.close();

            for (Teammate tm : all) {
                if (Objects.equals(tm.idProject, p.idProject)) {
                    allTeammates.add(tm);
                }
            }
            return allTeammates;
        }
        catch (Exception e) {
            throw new Exception("Failed in getting teammates: " + e.getMessage());
        }
    }

    public List<Task> getAllTasks(String project) throws Exception {
        List<Task> allTasks = new ArrayList<>();
        try {
            List<Task> all = new ArrayList<>();
            Project p = getProject(project);
            Statement statement = conn.createStatement();
            String query = "SELECT * FROM " + "sys.Task" + ";";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Task t = generateTask(resultSet);
                all.add(t);
            }
            resultSet.close();
            statement.close();
            for (Task t : all) {
                if (Objects.equals(t.idProject, p.idProject)) {
                    Task temp = t;
                    temp.assignees = getAssignees(t.idTask);
                    allTasks.add(temp);
                }
            }

            updateOrder(allTasks, project);

            return allTasks;
        } catch (Exception e) {
            throw new Exception("Failed in getting tasks: " + e.getMessage());
        }
    }

    public List<Task> getAllTasks() throws Exception {
        List<Task> allTasks = new ArrayList<>();
        try {
            List<Task> all = new ArrayList<>();
            Statement statement = conn.createStatement();
            String query = "SELECT * FROM " + "sys.Task" + ";";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Task t = generateTask(resultSet);
                allTasks.add(t);
            }
            resultSet.close();
            statement.close();

            return allTasks;
        } catch (Exception e) {
            throw new Exception("Failed in getting tasks: " + e.getMessage());
        }
    }

    public List<String> getAssignees(String idTask) throws Exception{
        List<String> assignees = new ArrayList<>();

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + "sys.Assignments" + " WHERE idTask=?;");
        ps.setString(1, idTask);
        ResultSet resultSet = ps.executeQuery();

        while (resultSet.next()) {
            assignees.add(getTeammate(resultSet.getString("idTeammate")).name);
        }
        return assignees;
    }

    public Teammate getTeammate(String name, String projectName) throws Exception {
        try {
            Teammate teammate = null;
            Project project = getProject(projectName);
            String idProject = project.idProject;
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + "sys.Teammate" + " WHERE (name, idProject) = (?,?);");
            ps.setString(1, name);
            ps.setString(2, idProject);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                teammate = generateTeammate(rs);
            }
            rs.close();
            ps.close();
            return teammate;
        }
        catch(Exception e){
            e.printStackTrace();
            throw new Exception("Failed in getting teammate: " + e.getMessage());
        }
    }

    //USES ONLY ID
    public Teammate getTeammate(String id) throws Exception {
        try {
            Teammate teammate = null;
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + "sys.Teammate" + " WHERE (idTeammate) = (?);");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                teammate = generateTeammate(rs);
            }
            rs.close();
            ps.close();
            return teammate;
        }
        catch(Exception e){
            e.printStackTrace();
            throw new Exception("Failed in adding teammate: " + e.getMessage());
        }
    }

    public boolean addTeammate(Teammate teammate) throws Exception{
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + "sys.Teammate" + " WHERE (name, idProject) = (?,?);");
            ps.setString(1, teammate.name);
            ps.setString(2, teammate.idProject);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Teammate t = generateTeammate(rs);
                rs.close();
                return false;
            }
            ps = conn.prepareStatement("INSERT INTO " + "sys.Teammate" + " (idTeammate, idProject, name) value(?,?,?);");
            ps.setString(3, teammate.name);
            ps.setString(1, teammate.idTeammate);
            ps.setString(2, teammate.idProject);
            ps.execute();
            return true;
        } catch (Exception e) {
            throw new Exception("Failed to create project: " + e.getMessage());
        }
    }

    public boolean deleteTeammate(Teammate tm) throws Exception{ //Also deletes assignments
        try{
            PreparedStatement ps = conn.prepareStatement("DELETE FROM sys.Teammate WHERE idTeammate = ?;");
            ps.setString(1, tm.idTeammate);
            int numAffected = ps.executeUpdate();
            ps.close();
            deleteAssignment(tm.idTeammate, tm.idProject);
            return (numAffected == 1);
        }
        catch(Exception e){
            throw new Exception("Failed to delete teammate from project: " + e.getMessage());
        }
    }

    public boolean deleteProject(String project) throws Exception{
        try{
            List<Task> taskList = getAllTasks(project);
            List<Teammate> tmList = getAllTeammates(project);
            for(Task tsk : taskList){
                deleteTask(tsk);
            }
            for(Teammate tm : tmList){
                deleteTeammate(tm);
            }
            PreparedStatement ps = conn.prepareStatement("DELETE FROM sys.Project WHERE name = ?;");
            ps.setString(1, project);
            int numAffected = ps.executeUpdate();
            ps.close();

            return (numAffected == 1);
        }
        catch(Exception e){
            throw new Exception("Failed to delete project: " + e.getMessage());
        }
    }

    public void deleteAllProjects() throws Exception {
        try{
            List<Project> projects = getAllProjects();
            for(Project proj: projects){
                deleteProject(proj.name);
            }
        }
        catch (Exception e) {
            throw new Exception("Failed to delete all projects: " + e.getMessage());
        }

    }

    public boolean addTask(Task task, String projectName) throws Exception{
        try{
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + "sys.Task" + " WHERE (name, idProject) = (?,?);");
            ps.setString(1, task.name);
            ps.setString(2, task.idProject);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                rs.close();
                return false;
            }
            ps = conn.prepareStatement("INSERT INTO " + "sys.Task" + " (idTask, name, idParent, isComplete, idProject, sequence) value(?,?,?,?,?,?);");
            ps.setString(1, task.idTask);
            ps.setString(2, task.sequence + ": " + task.name);
            ps.setString(3, task.idParent);
            ps.setInt(4, task.isComplete);
            ps.setString(5, task.idProject);
            ps.setInt(6, task.sequence);
            ps.execute();

            //updateOrder(task, projectName);

            return true;
        }
        catch(Exception e){
            throw new Exception("Failed to add task: " + e.getMessage());
        }
    }

    public boolean renameTask(String idTask, String newName) throws Exception{
        try{
            Task task = getTask(idTask);
            String tempName = task.name;
            String[] arr = tempName.split(" ", 2);
            tempName = arr[0] + " " + newName;

            PreparedStatement ps = conn.prepareStatement("UPDATE sys.Task SET name = ? WHERE idTask=?;");
            ps.setString(1, tempName);
            ps.setString(2, idTask);
            ps.execute();
            return true;
        }
        catch(Exception e){
            throw new Exception("Failed to rename task: " + e.getMessage());
        }
    }

    public boolean deleteTask(Task task) throws Exception{
        try{
            PreparedStatement ps = conn.prepareStatement("DELETE FROM sys.Task WHERE idTask = ?;");
            ps.setString(1, task.idTask);
            int numAffected = ps.executeUpdate();
            ps.close();

            //String projNname = task.
            //updateOrder(task, projectName);

            return (numAffected == 1);
        }
        catch(Exception e){
            throw new Exception("Failed to delete task from project: " + e.getMessage());
        }
    }

    //UNUSED
//    public int getNextSequence(Project project) throws Exception{
//        List<Task> allTasks = getAllTasks(project.name);
//        //Eventually once subdivision is implemented, have the parent id also
//        // passed in so you can gather all tasks under that id in a sub list
//        int high = 0;
//        for(Task t : allTasks){
//            if(t.sequence > high){high = t.sequence;}
//        }
//        return high+1;
//
//    }

    public int getNextSequence(String projectName, String idParent) throws Exception{
        try {
            List<Task> allTasks = getAllTasks(projectName);
            List<Task> taskList = new ArrayList<>();
            for(Task t : allTasks){
                if(Objects.equals(t.idParent, idParent)){
                    taskList.add(t);
                }
            }
            return taskList.size() + 1;
        }
        catch(Exception e){
            throw new Exception("Failed to get sequence: " + e.getMessage());
        }
    }

    //UNUSED
//    public int getDepth(String idParent) throws Exception{
//        try{
//            Task task = getTask(idParent);
//            int depth = 1;
//            while(task.idParent != null){
//                depth += 1;
//                task = getTask(task.idParent);
//            }
//            return depth;
//        }
//        catch(Exception e){
//            throw new Exception("Failed to get depth: " + e.getMessage());
//        }
//    }

    //UNUSED
//    public Task getTask(String name, String projectName) throws Exception{
//        try {
//            Task task = null;
//            Project project = getProject(projectName);
//            String idProject = project.idProject;
//            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + "sys.Task" + " WHERE (name, idProject) = (?,?);");
//            ps.setString(1, name);
//            ps.setString(2, idProject);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                task = generateTask(rs);
//            }
//            rs.close();
//            ps.close();
//            return task;
//        }
//        catch(Exception e){
//            e.printStackTrace();
//            throw new Exception("Failed in getting task: " + e.getMessage());
//        }
//    }

    public Task getTask(String idTask) throws Exception{
        try {
            List<Task> list;
            Task task = null;
            list = getAllTasks();
            for (Task t : list) {
                if (Objects.equals(t.idTask, idTask)) {
                    task = t;
                }
            }
            return task;
        }
        catch(Exception e){
            e.printStackTrace();
            throw new Exception("Failed in getting task: " + e.getMessage());
        }
    }

    public void toggleAssignment(Assignment assignment) throws Exception{

        if(checkAssignment(assignment)){
            //delete assignment
            deleteAssignment(assignment.idTask, assignment.idTeammate, assignment.idProject);
        }
        else{
            //create new assignment
            PreparedStatement ps = conn.prepareStatement("INSERT INTO " + "sys.Assignments" + " (idTask, idTeammate, idProject) value(?,?,?);");
            ps.setString(1, assignment.idTask);
            ps.setString(2, assignment.idTeammate);
            ps.setString(3, assignment.idProject);
            ps.execute();
        }
    }

    public boolean checkAssignment(Assignment assignment) throws Exception{
        //This code wouldn't need to exist if I had made a getAssignment function...oh well
        try {
            Assignment a = null;
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + "sys.Assignments" + " WHERE (idTask, idTeammate, idProject)=(?,?,?);");
            ps.setString(1, assignment.idTask);
            ps.setString(2, assignment.idTeammate);
            ps.setString(3, assignment.idProject);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                a = generateAssignment(rs);
            }
            rs.close();
            ps.close();

            if(a == null){
                return false;
            }
            else{
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Failed checking assignment existence: " + e.getMessage());
        }
    }

    public boolean deleteAssignment(String idTask, String idTeammate, String idProject) throws Exception{
        try{
            PreparedStatement ps = conn.prepareStatement("DELETE FROM sys.Assignments WHERE (idTask, idTeammate, idProject)=(?,?,?);");
            ps.setString(1, idTask);
            ps.setString(2, idTeammate);
            ps.setString(3, idProject);
            int numAffected = ps.executeUpdate();
            ps.close();
            return (numAffected == 1);
        }
        catch(Exception e){
            throw new Exception("Failed to delete assignment: " + e.getMessage());
        }
    }

    public boolean deleteAssignment(String idTeammate, String idProject) throws Exception{
        try{
            PreparedStatement ps = conn.prepareStatement("DELETE FROM sys.Assignments WHERE (idTeammate, idProject)=(?,?);");
            ps.setString(1, idTeammate);
            ps.setString(2, idProject);
            int numAffected = ps.executeUpdate();
            ps.close();
            return (numAffected == 1);
        }
        catch(Exception e){
            throw new Exception("Failed to delete assignment: " + e.getMessage());
        }
    }

    public boolean updateAssignment(String idParent, String idTask) throws Exception{
        try{
            PreparedStatement ps = conn.prepareStatement("UPDATE sys.Assignments SET idTask = ? WHERE idTask=?;");
            ps.setString(1, idTask);
            ps.setString(2, idParent);
            ps.execute();
            return true;
        }
        catch(Exception e){
            throw new Exception("Failed to update assignment: " + e.getMessage());
        }
    }

    public boolean toggleComplete(String idTask) throws Exception{
        try {
            if(getTask(idTask).isComplete == 0) {
                PreparedStatement ps = conn.prepareStatement("UPDATE sys.Task SET isComplete = 1 WHERE idTask=?;");
                ps.setString(1, idTask);
                ps.execute();
                return true;
            }
            else{
                PreparedStatement ps = conn.prepareStatement("UPDATE sys.Task SET isComplete = 0 WHERE idTask=?;");
                ps.setString(1, idTask);
                ps.execute();
                return true;
            }
        }
        catch(Exception e){
            throw new Exception("Failed to mark complete: " + e.getMessage());
        }
    }

    public boolean toggleArchived(String projectName) throws Exception{
        try{
            Project proj = getProject(projectName);
            if(proj.isArchived == 0) {
                PreparedStatement ps = conn.prepareStatement("UPDATE sys.Project SET isArchived = 1 WHERE idProject=?;");
                ps.setString(1, proj.idProject);
                ps.execute();
                return true;
            }
            else{
                PreparedStatement ps = conn.prepareStatement("UPDATE sys.Project SET isArchived = 0 WHERE idProject=?;");
                ps.setString(1, proj.idProject);
                ps.execute();
                return true;
            }
        }
        catch(Exception e){
            throw new Exception("Failed to mark archived: " + e.getMessage());
        }
    }

    public boolean decompose(String name, String projectName, String idParent) throws Exception{
        try {
            Task parent = getTask(idParent);
            List<Task> allTasks = getAllTasks(projectName);
            int sequence = getNextSequence(projectName, idParent);

            String tempName = parent.name;
            String[] arr = tempName.split(":", 2);
            tempName = arr[0] + "." + sequence + ": " + name;

            Task task = new Task(tempName, getProject(projectName).idProject, idParent, sequence);
            updateAssignment(idParent, task.idTask);

            PreparedStatement ps = conn.prepareStatement("INSERT INTO " + "sys.Task" + " (idTask, name, idParent, isComplete, idProject, sequence) value(?,?,?,?,?,?);");
            ps.setString(1, task.idTask);
            ps.setString(2, task.name);
            ps.setString(3, task.idParent);
            ps.setInt(4, task.isComplete);
            ps.setString(5, task.idProject);
            ps.setInt(6, task.sequence);
            ps.execute();

            //updateOrder(parent, projectName);

            return true;
        }
        catch(Exception e){
            throw new Exception("Failed to decompose: " + e.getMessage());
        }
    }

    public String getPerc(String projectName) throws Exception{
        try{
            List<Task> allTasks = getAllTasks(projectName);
            List<Task> bottomTasks = new ArrayList<>();
            for(Task task : allTasks){
                boolean isParent = false;
                for(Task child : allTasks){
                    if(Objects.equals(child.idParent, task.idTask)){
                        isParent = true;
                    }
                }
                if(!isParent){
                    bottomTasks.add(task);
                }
            }
            int completed = 0;
            for(Task task : bottomTasks){
                if (task.isComplete == 1){
                    completed += 1;
                }
            }
            double perc;
            if(bottomTasks.size() != 0) {
                perc = ((double) (completed) / bottomTasks.size()) * 100.00;
            }
            else{
                perc = 0.00;
            }
            int percInt = (int) perc;
            return "" + percInt + "%";
        }

        catch(Exception e){
            throw new Exception("Failed to decompose: " + e.getMessage());
        }
    }

    public void updateOrder(List<Task> allTasks, String projectName) throws Exception{
        try{
            Project project = getProject(projectName);
            //List<Task> allTasks = getAllTasks(project.name);

            List<Integer> taskValues = new ArrayList<>();

            for(Task t : allTasks){
                List<Integer> lst = new ArrayList<>();

                char main = t.name.charAt(0);
                int mainInt = (int)main - 48;
                char sub1 = t.name.charAt(2);
                int sub1Int = (int)sub1 - 48;
                char sub2 = t.name.charAt(4);
                int sub2Int = (int)sub2 - 48;
                char sub3 = t.name.charAt(4);
                int sub3Int = (int)sub3 - 48;
                char sub4 = t.name.charAt(4);
                int sub4Int = (int)sub4 - 48;
                char sub5 = t.name.charAt(4);
                int sub5Int = (int)sub5 - 48;
                char sub6 = t.name.charAt(4);
                int sub6Int = (int)sub6 - 48;
                char sub7 = t.name.charAt(4);
                int sub7Int = (int)sub7 - 48;

                if(mainInt > 0 && mainInt<4){
                    lst.add(mainInt);
                }
                if(sub1Int > 0 && sub1Int<4){
                    lst.add(sub1Int);
                }
                if(sub2Int > 0 && sub2Int<4){
                    lst.add(sub2Int);
                }
                if(sub3Int > 0 && sub3Int<4){
                    lst.add(sub3Int);
                }
                if(sub4Int > 0 && sub4Int<4){
                    lst.add(sub4Int);
                }
                if(sub5Int > 0 && sub5Int<4){
                    lst.add(sub5Int);
                }
                if(sub6Int > 0 && sub6Int<4){
                    lst.add(sub6Int);
                }
                if(sub7Int > 0 && sub7Int<4){
                    lst.add(sub7Int);
                }

                int value = 0;
                int multiplier = 1000000;
                for(Integer i: lst){

                    if (i == 1 || i == 2 || i == 3 || i == 4 || i == 5 || i == 6 || i == 7 || i == 8 || i == 9){
                            value = value + i*multiplier;
                    }
                    if(multiplier > 1){
                        multiplier = multiplier / 10;
                    }



                }
                t.setValue(value);
                taskValues.add(value);
            }

//            int o = 0;
//            List<Integer> orderList = new ArrayList<>();
//
//            for(Task t : allTasks){
//                int maxVal = 0;
//                if(orderList.size()>=1){
//                    maxVal = Collections.max(taskValues);
//                }
//
//
//
//                for(Integer val : taskValues){
//                    if (maxVal == t.value){
//                        t.setOrder(o);
////                        if(taskValues.size() > 1){
////                            taskValues.remove(0);
////                        }
//                    }
//                }
//                o = o+1;
//                orderList.add(o);
//
//
//            }
////        for(Task t : allTasks){
////            if
////        }
        }

        catch(Exception e){
            throw new Exception("Failed to update order: " + e.getMessage());
        }
    }

//    public List<Task> sortByOrder(List<Task> taskList) throws Exception{
//        try{
//            List<Task> finalOrderList = taskList;
//            List<Integer> orderList = new ArrayList<>();
//            for(Task t: taskList){
//                orderList.add(t.order);
//            }
//
//            for(Task t: taskList){
//                int minVal = Collections.min(orderList);
//                for(int ord: orderList){
//                    if(t.order == ord){
//                        finalOrderList.add(t);
////                        if(orderList.size()>1){
////                            orderList.remove(0);
////                        }
//                    }
//
//                }
//            }
//            return finalOrderList;
//        }
//        catch(Exception e){
//            throw new Exception("Failed to sort: " + e.getMessage());
//        }
//    }
}

