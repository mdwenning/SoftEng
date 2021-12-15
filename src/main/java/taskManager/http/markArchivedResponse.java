package taskManager.http;

public class markArchivedResponse {
    public final String name;
    public final int statusCode;
    public final String error;

    public markArchivedResponse(String name, int statusCode){
        this.name = name;
        this.statusCode = statusCode;
        this.error = "";
    }
    public markArchivedResponse(int statusCode, String error){
        this.name = "";
        this.statusCode = statusCode;
        this.error = error;
    }
}
