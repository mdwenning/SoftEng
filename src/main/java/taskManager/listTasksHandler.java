package taskManager;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import taskManager.db.projectsDAO;
import taskManager.http.listTasksRequest;
import taskManager.http.listTasksResponse;
import taskManager.model.Task;

import java.util.List;


public class listTasksHandler implements RequestHandler<listTasksRequest, listTasksResponse> {
    List<Task> getTasks(String projectName) throws Exception{
        projectsDAO dao = new projectsDAO();
        List<Task> allTasks = dao.getAllTasks(projectName);
        return allTasks;//dao.sortByOrder(allTasks);
    }
    @Override
    public listTasksResponse handleRequest(listTasksRequest req, Context context){
        listTasksResponse response;
        try{
            List<Task> list = getTasks(req.name);
            response = new listTasksResponse(list, 200);
        }
        catch(Exception e){
            response = new listTasksResponse(403, e.getMessage());
        }
        return response;
    }
}
