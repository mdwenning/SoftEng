package taskManager.http;

public class assignResponse {
    public final String idTeammate;
    public final String idTask;
    public final int httpCode;
    public final String error;

    public assignResponse(String idTeammate, String idTask, int code){
        this.idTeammate = idTeammate;
        this.idTask = idTask;
        this.httpCode = code;
        this.error = "";
    }
    public assignResponse(String error, int code){
        this.error = error;
        this.httpCode = code;
        this.idTeammate = "";
        this.idTask = "";
    }
}
