package taskManager.http;

public class addTaskRequest {
    public String name;
    public String projectName;

    public String getName(){return name;}
    public void setName(String name){this.name = name;}
    public String getprojectName(){return projectName;}
    public void setprojectName(String projectName){this.projectName = projectName;}
    public addTaskRequest(String name, String projectName){
        this.name = name;
        this.projectName = projectName;
    }
    public addTaskRequest(){}
}
