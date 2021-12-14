package taskManager.http;

public class addTaskResponse {
    public final String name;
    public final String projectName;
    public final String taskID;
    public final int httpCode;
    public final String error;

    public addTaskResponse(String s, String p, String t, int code){
        this.name = s;
        this.projectName = p;
        this.taskID = t;
        this.httpCode = code;
        this.error = "";
    }
    public addTaskResponse(String errorMessage, int code){
        this.error = errorMessage;
        this.httpCode = code;
        this.taskID = "";
        this.name = "";
        this.projectName = "";
    }
    public String toString(){return "Task("+name+":"+projectName+")";}

    public String getTaskID(){
        return this.taskID;
    }
}
