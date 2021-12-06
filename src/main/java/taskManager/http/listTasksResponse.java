package taskManager.http;

import taskManager.model.Task;
import taskManager.model.Teammate;

import java.util.ArrayList;
import java.util.List;

public class listTasksResponse {
    public final List<Task> list;
    public final int statusCode;
    public final String error;

    public listTasksResponse(List<Task> list, int code){
        this.list = list;
        this.statusCode = code;
        this.error = "";
    }
    public listTasksResponse(int code, String error){
        this.list = new ArrayList<>();
        this.statusCode = code;
        this.error = error;
    }
}
