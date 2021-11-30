package taskManager.db;

import taskManager.model.Project;

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

    public boolean addProject(){return true;}

    public Project generateProject(String name){
        Project project = null;
        return project;
    }
}
