package taskManager.http;

public class addTeammateResponse {
    public final String name;
    public final String projectName;
    public final int httpCode;
    public final String error;

    public addTeammateResponse(String s, String p, int code){
        this.name = s;
        this.projectName = p;
        this.httpCode = code;
        this.error = "";
    }
    public addTeammateResponse(String errorMessage, int code){
        this.error = errorMessage;
        this.httpCode = code;
        this.name = "";
        this.projectName = "";
    }
    public String toString(){return "Response("+name+": "+projectName+")";}
}
