package taskManager.http;

public class renameTaskResponse {
    public final String newName;
    public final String idTask;
    public final int httpCode;
    public final String error;

    public renameTaskResponse(String newName, String idTask, int httpCode){
        this.newName = newName;
        this.idTask = idTask;
        this.httpCode = httpCode;
        this.error = "";
    }

    public renameTaskResponse(String error, int httpCode){
        this.error = error;
        this.httpCode = httpCode;
        this.newName = "";
        this.idTask = "";
    }
}
