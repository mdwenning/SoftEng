package taskManager.http;

public class deleteTaskRequest {
    public String idTask;
    public void setidTask(String idTask){this.idTask = idTask;}
    public String getidTask(){return idTask;}
    public deleteTaskRequest(String idTask){
        this.idTask = idTask;
    }
    public deleteTaskRequest(){};
    public String toString() {
        return "Delete(" + idTask + ")";
    }
}
