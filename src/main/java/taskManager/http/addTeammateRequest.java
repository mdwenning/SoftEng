package taskManager.http;

public class addTeammateRequest {
    public String name;
    public String projectName;

    public String getName(){return name;}
    public void setName(String name){this.name = name;}
    public String getProjectName(){return projectName;}
    public void setProjectName(String projectName){this.projectName = projectName;}
    public addTeammateRequest(String name, String projectName){
        this.name = name;
        this.projectName = projectName;
    }
    public addTeammateRequest(){}
}
