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
    public void setTaskName(String name){this.taskName = taskName;}

    public assignRequest(String n, String t, String p){
        this.name = n;
        this.projectName = p;
        this.taskName = t;
    }

    public assignRequest(){}
}
