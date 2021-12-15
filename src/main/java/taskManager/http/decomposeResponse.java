package taskManager.http;

public class decomposeResponse {
    public final String name;
    public final String projectName;
    public final String idParent;
    public final int httpCode;
    public final String error;

    public decomposeResponse(String s, String p, String t, int code){
        this.name = s;
        this.projectName = p;
        this.idParent = t;
        this.httpCode = code;
        this.error = "";
    }
    public decomposeResponse(String errorMessage, int code){
        this.error = errorMessage;
        this.httpCode = code;
        this.idParent = "";
        this.name = "";
        this.projectName = "";
    }
    public String toString(){return "Task("+name+":"+projectName+")";}

    public String getTaskID(){
        return this.idParent;
    }
}
