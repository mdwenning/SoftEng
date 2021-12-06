package taskManager.http;

public class deleteTeammateResponse {
    public final String name;
    public final String projectName;
    public final int statusCode;
    public final String error;

    public deleteTeammateResponse(String name, String projectName, int statusCode){
        this.name = name;
        this.projectName = projectName;
        this.statusCode = statusCode;
        this.error = "";
    }
    public deleteTeammateResponse(String name, String projectName, int statusCode, String error){
        this.name = name;
        this.projectName = projectName;
        this.statusCode = statusCode;
        this.error = error;
    }
}
