package taskManager.http;

public class markTaskCompleteResponse {
    public final String idTask;
    public final int statusCode;
    public final String error;

    public markTaskCompleteResponse(String idTask, int statusCode){
        this.idTask = idTask;
        this.statusCode = statusCode;
        this.error = "";
    }
    public markTaskCompleteResponse(int statusCode, String error){
        this.idTask = "";
        this.error = error;
        this.statusCode = statusCode;
    }

}
