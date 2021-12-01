package taskManager.http;

import taskManager.model.Project;

import java.util.ArrayList;
import java.util.List;

public class listProjectsResponse {
    public final List<Project> list;
    public final int statusCode;
    public final String error;

    public listProjectsResponse(List<Project> list, int code){
        this.list = list;
        this.statusCode = code;
        this.error = "";
    }
    public listProjectsResponse(int code, String errorMessage){
        this.list = new ArrayList<Project>();
        this.statusCode = code;
        this.error = errorMessage;
    }
    public String toString() {
        if (list == null) { return "Empty Projects"; }
        return "All Projects(" + list.size() + ")";
    }
}

