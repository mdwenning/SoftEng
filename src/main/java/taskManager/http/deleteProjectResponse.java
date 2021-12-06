package taskManager.http;

public class deleteProjectResponse {
    public final String name;
    public final int statusCode;
    public final String error;

    public deleteProjectResponse(String name, int statusCode){
        this.name = name;
        this.statusCode = statusCode;
        this.error = "";
    }
    public deleteProjectResponse(String name, int statusCode, String error){
        this.name = name;
        this.statusCode = statusCode;
        this.error = error;
    }
}
