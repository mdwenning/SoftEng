package taskManager;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import taskManager.db.projectsDAO;
import taskManager.http.deleteTaskRequest;
import taskManager.http.deleteTaskResponse;
import taskManager.model.Task;

public class deleteTaskHandler implements RequestHandler<deleteTaskRequest, deleteTaskResponse> {
    @Override
    public deleteTaskResponse handleRequest(deleteTaskRequest req, Context context){
        deleteTaskResponse response = null;
        projectsDAO dao = new projectsDAO();
        try {
            Task delTask = dao.getTask(req.name, req.projectName);
            if (dao.deleteTask(delTask)) {
                response = new deleteTaskResponse(req.name, req.projectName, 200);
            } else {
                response = new deleteTaskResponse(req.name, req.projectName, 422, "Unable to delete task.");
            }
        } catch (Exception e) {
            response = new deleteTaskResponse(req.name, req.projectName, 403, "Unable to delete task: " + req.name + "(" + e.getMessage() + ")");
        }

        return response;
    }
}
