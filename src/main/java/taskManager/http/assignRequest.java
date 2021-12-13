package taskManager.http;

public class assignRequest {
    public String name;
    public String taskName;
    public String projectName;

    public String getName(){return name;}
    public void setName(String name){this.name = name;}
    public String getProjectName(){return projectName;}
    public void setProjectName(String projectName){this.projectName = projectName;}
    public String getTaskName(){return taskName;}
    public void setTaskName(String taskName){this.taskName = taskName;}

    public assignRequest(String name, String taskName, String projectName){
        this.name = name;
        this.taskName = taskName;
        this.projectName = projectName;
    }

    public assignRequest(){}
}
