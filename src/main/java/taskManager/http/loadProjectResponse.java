package taskManager.http;

import taskManager.model.Assignment;
import taskManager.model.Task;
import taskManager.model.Teammate;

import java.util.ArrayList;
import java.util.List;

public class loadProjectResponse {
    public final String name;
    public final List<Teammate> mems;
    public final List<Task> tasks;
    public final int statusCode;
    public final String error;

    public loadProjectResponse(String name, List<Teammate> mems, List<Task> tasks, int statusCode){
        this.name = name;
        this.mems = mems;
        this.tasks = tasks;
        this.statusCode = statusCode;
        this.error = "";
    }

    public loadProjectResponse(int statusCode, String error){
        this.name = "";
        this.mems = new ArrayList<Teammate>();
        this.tasks = new ArrayList<Task>();
        this.statusCode = statusCode;
        this.error = error;
    }
}
