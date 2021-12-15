package taskManager.http;

public class assignRequest {
    public String name;
    public String projectName;
    public String idTask;

    public String getName(){return name;}
    public void setName(String name){this.name = name;}
    public String getProjectName(){return projectName;}
    public void setProjectName(String projectName){this.projectName = projectName;}
    public String getidTask(){return idTask;}
    public void setidTask(String idTask){this.idTask = idTask;}

    public assignRequest(String name, String projectName, String idTask){
        this.name = name;
        this.projectName = projectName;
        this.idTask = idTask;

    }
    public assignRequest(){}
}
