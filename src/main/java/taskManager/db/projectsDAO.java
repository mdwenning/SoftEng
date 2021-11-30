package taskManager.db;

import taskManager.model.Project;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class projectsDAO {
    java.sql.Connection conn;
    final String tblName = "Project";

    public projectsDAO(){
        try  {
            conn = DatabaseUtil.connect();
        } catch (Exception e) {
            e.printStackTrace();
            conn = null;
        }
    }

    public boolean addProject(Project project) throws Exception{
        try{
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE name = ?;");
            ps.setString(1, project.name);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Project p = generateProject(rs);
                rs.close();
                return false;
            }
            ps = conn.prepareStatement("INSERT INTO " + tblName + " (name) value(?);");
            ps.setString(1, project.name);
            ps.execute();
            return true;
        }
        catch(Exception e){
            throw new Exception("Failed to create project: " + e.getMessage());
        }
    }

    public Project generateProject(ResultSet rs) throws Exception{
        String name = rs.getString("name");
        return new Project(name);
    }

    public Project getProject(String name) throws Exception{
        try {
            Project project = null;
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE name=?;");
            ps.setString(1,  name);
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
}
