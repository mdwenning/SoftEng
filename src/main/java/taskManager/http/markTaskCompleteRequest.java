package taskManager.http;

public class markTaskCompleteRequest {
    public String idTask;
    public String getIdTask(){return idTask;}
    public void setIdTask(String idTask){this.idTask = idTask;}
    public markTaskCompleteRequest(String idTask){this.idTask = idTask;}
    public markTaskCompleteRequest(){}
}
