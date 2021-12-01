package taskManager;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import taskManager.db.projectsDAO;
import taskManager.http.listProjectsResponse;
import taskManager.http.loadProjectRequest;
import taskManager.http.loadProjectResponse;
import taskManager.model.Assignment;
import taskManager.model.Project;
import taskManager.model.Task;
import taskManager.model.Teammate;

import java.util.List;

public class loadProjectHandler implements RequestHandler<loadProjectRequest, loadProjectResponse> {
    List<Teammate> getTeammates(String project) throws Exception{
        projectsDAO dao = new projectsDAO();
        return dao.getAllTeammates(project);
    }
    List<Task> getTasks(String project) throws Exception{
        projectsDAO dao = new projectsDAO();
        return dao.getAllTasks(project);
    }

    @Override
    public loadProjectResponse handleRequest(loadProjectRequest req, Context context){
        loadProjectResponse response;
        try{
            List<Teammate> memList = getTeammates(req.name);
            List<Task> tasks = getTasks(req.name);
            response = new loadProjectResponse(req.name, memList, tasks, 200);
        }
        catch(Exception e){
            response = new loadProjectResponse(403, e.getMessage());
        }
        return response;
    }
}
