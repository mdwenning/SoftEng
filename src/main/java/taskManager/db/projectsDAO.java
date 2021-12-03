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
        return new Task(idTask, idProject, idParent, name, isComplete);
    }

    public Assignment generateAssignment(ResultSet rs) throws Exception {
        String idTask = rs.getString("idTask");
        String idTeammate = rs.getString("idTeammate");
        String idProject = rs.getString("idProject");
        return new Assignment(idTask, idTeammate, idProject);
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
            for (Teammate t : all) {
                if (t.idProject == p.idProject) {
                    allTeammates.add(t);
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
                if (t.idProject == p.idProject) {
                    allTasks.add(t);
                }
            }
            return allTasks;
        } catch (Exception e) {
            throw new Exception("Failed in getting tasks: " + e.getMessage());
        }
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
}

