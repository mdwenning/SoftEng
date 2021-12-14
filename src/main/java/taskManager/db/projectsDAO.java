package taskManager.db;

import taskManager.model.Assignment;
import taskManager.model.Project;
import taskManager.model.Task;
import taskManager.model.Teammate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
                    t.assignees = getAssignees(t.idTask);
                    allTasks.add(t);
                }
            }
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
            throw new Exception("Failed in adding teammate: " + e.getMessage());
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

    public boolean deleteTeammate(String teammate, String project) throws Exception{
        try{
            Project p = getProject(project);
            PreparedStatement ps = conn.prepareStatement("DELETE FROM sys.Teammate WHERE (name, idProject) = (?, ?);");
            ps.setString(1, teammate);
            ps.setString(2, p.idProject);
            int numAffected = ps.executeUpdate();
            ps.close();
            return (numAffected == 1);
        }
        catch(Exception e){
            throw new Exception("Failed to delete teammate from project: " + e.getMessage());
        }
    }

    public boolean deleteProject(String project) throws Exception{
        try{
            PreparedStatement ps = conn.prepareStatement("DELETE FROM sys.Project WHERE name = ?;");
            ps.setString(1, project);
            int numAffected = ps.executeUpdate();
            ps.close();

            List<Task> taskList = getAllTasks(project);
            for(Task tsk : taskList){
                deleteTask(tsk);
            }
            return (numAffected == 1);
        }
        catch(Exception e){
            throw new Exception("Failed to delete project: " + e.getMessage());
        }
    }

    public boolean addTask(Task task) throws Exception{
        try{
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + "sys.Task" + " WHERE (name, idProject) = (?,?);");
            ps.setString(1, task.name);
            ps.setString(2, task.idProject);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Teammate t = generateTeammate(rs);
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
            return true;
        }
        catch(Exception e){
            throw new Exception("Failed to add task: " + e.getMessage());
        }
    }

    public boolean deleteTask(Task task) throws Exception{
        try{
            //Project p = getProject(project);
            PreparedStatement ps = conn.prepareStatement("DELETE FROM sys.Task WHERE idTask = ?;");
            ps.setString(1, task.idTask);
            //ps.setString(2, p.idProject);
            int numAffected = ps.executeUpdate();
            ps.close();
            return (numAffected == 1);
        }
        catch(Exception e){
            throw new Exception("Failed to delete teammate from project: " + e.getMessage());
        }
    }

    public int getNextSequence(Project project) throws Exception{
        List<Task> allTasks = getAllTasks(project.name);
        //Eventually once subdivision is implemented, have the parent id also
        // passed in so you can gather all tasks under that id in a sub list
        int high = 0;
        for(Task t : allTasks){
            if(t.sequence > high){high = t.sequence;}
        }
        return high+1;

    }

    public Task getTask(String taskName, String p) throws Exception{
        try {
            List<Task> list;
            Task task = null;
            list = getAllTasks(p);
            for (Task t : list) {
                if (Objects.equals(t.name, taskName)) {
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
            PreparedStatement ps = conn.prepareStatement("DELETE FROM " + "sys.Assignments" + " WHERE (idTask, idTeammate, idProject)=(?,?,?)");
            ps.setString(1, assignment.idTask);
            ps.setString(2, assignment.idTeammate);
            ps.setString(3, assignment.idProject);
            ps.execute();
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
}

