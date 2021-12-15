package taskManager.http;

public class renameTaskRequest {
    public String idTask;
    public String newName;
    public String getIdTask(){return idTask;}
    public void setIdTask(String idTask){this.idTask = idTask;}
    public String getNewName(){return newName;}
    public void setNewName(String newName){this.newName = newName;}
    public renameTaskRequest(String idTask, String newName){
        this.idTask = idTask;
        this.newName = newName;
    }
    public renameTaskRequest(){}
}
