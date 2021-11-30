package taskManager.http;

public class createProjectResponse {
    public final String name;
    public final int httpCode;

    public createProjectResponse(String s, int code){
        this.name = s;
        this.httpCode = code;
    }
    public createProjectResponse(String s){
        this.name = s;
        this.httpCode = 200;
    }
    public String toString(){
        return "Response("+name+")";
    }
}
