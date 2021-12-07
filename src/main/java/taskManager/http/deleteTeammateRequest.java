package taskManager.http;

public class deleteTeammateRequest {
    public String name;
    public String projectName;
    public void setName(String name){this.name = name;}
    public void setProjectName(String projectName){this.projectName = projectName;}
    public String getName(){return name;}
    public String getProjectName(){return projectName;}
    public deleteTeammateRequest(String name, String projectName){
        this.name = name;
        this.projectName = projectName;
    }
    public deleteTeammateRequest(){};
    public String toString() {
        return "Delete(" + name + " " + projectName + ")";
    }
}
