package taskManager.http;

public class assignResponse {
    public final String name;
    public final String projectName;
    public final String taskName;
    public final int httpCode;
    public final String error;

    public assignResponse(String n, String p, String t, int code){
        this.name = n;
        this.projectName = p;
        this.taskName = t;
        this.httpCode = code;
        this.error = "";
    }
    public assignResponse(String error, int code){
        this.error = error;
        this.httpCode = code;
        this.name = "";
        this.projectName = "";
        this.taskName = "";
    }
}
