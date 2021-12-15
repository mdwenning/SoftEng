package taskManager.http;

import taskManager.model.Project;
import java.util.ArrayList;
import java.util.List;

public class deleteAllProjectsResponse {
    //public final List<Project> projectNames;
    public final int statusCode;
    public final String error;

    public deleteAllProjectsResponse(int statusCode){
        //this.name = name;
        this.statusCode = statusCode;
        this.error = "";
    }
    public deleteAllProjectsResponse(int statusCode, String error){
        //this.name = name;
        this.statusCode = statusCode;
        this.error = error;
    }
}
