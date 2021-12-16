package taskManager.http;

import taskManager.model.Assignment;
import taskManager.model.Task;
import taskManager.model.Teammate;

import java.util.ArrayList;
import java.util.List;

public class loadProjectResponse {
    public final String name;
    public final List<Teammate> teammates;
    public final List<Task> tasks;
    public final String percentComplete;
    public final int statusCode;
    public final String error;

    public loadProjectResponse(String name, List<Teammate> mems, List<Task> tasks, String percentComplete, int statusCode){
        this.name = name;
        this.teammates = mems;
        this.tasks = tasks;
        this.percentComplete = percentComplete;
        this.statusCode = statusCode;
        this.error = "";
    }

    public loadProjectResponse(int statusCode, String error){
        this.name = "";
        this.teammates = new ArrayList<Teammate>();
        this.tasks = new ArrayList<Task>();
        this.percentComplete = "";
        this.statusCode = statusCode;
        this.error = error;
    }
}
