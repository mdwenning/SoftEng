package taskManager.http;

public class deleteTaskRequest {
    public String name;
    public String projectName;
    //public void setName(String name){this.name = name;}
    public void setProjectName(String projectName){this.projectName = projectName;}
    public String getName(){return name;}
    public String getProjectName(){return projectName;}
    public deleteTaskRequest(String name, String projectName){
        this.name = name;
        this.projectName = projectName;
    }
    public deleteTaskRequest(){};
    public String toString() {
        return "Delete(" + name + ": " + projectName + ")";
    }
}
