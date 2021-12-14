package taskManager.http;

public class deleteTaskResponse {
    public final String name;
    public final int statusCode;
    public final String error;

    public deleteTaskResponse(String name, int statusCode){
        this.name = name;
        this.statusCode = statusCode;
        this.error = "";
    }
    public deleteTaskResponse(String name, int statusCode, String error){
        this.name = name;
        this.statusCode = statusCode;
        this.error = error;
    }
}
