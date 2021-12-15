package taskManager.http;

public class assignRequest {
    public String teammateName;
    public String projectName;
    public String idTask;

    public String getName(){return teammateName;}
    public void setName(String tmName){this.teammateName = tmName;}
    public String getProjectName(){return projectName;}
    public void setProjectName(String projName){this.projectName = projName;}
    public String getidTask(){return idTask;}
    public void setidTask(String idTask){this.idTask = idTask;}

    public assignRequest(String teammateName, String projectName, String idTask){
        this.teammateName = teammateName;
        this.projectName = projectName;
        this.idTask = idTask;

    }

    public assignRequest(){}
}
