package taskManager.http;

public class deleteTaskResponse {
    public final String name;
    public final String projName;
    public final int statusCode;
    public final String error;

    public deleteTaskResponse(String name, String projName, int statusCode){
        this.name = name;
        this.projName = projName;
        this.statusCode = statusCode;
        this.error = "";
    }
    public deleteTaskResponse(String name, String projName, int statusCode, String error){
        this.name = name;
        this.projName = projName;
        this.statusCode = statusCode;
        this.error = error;
    }
}
