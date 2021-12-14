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
            Task delTask = dao.getTask(req.idTask);
            if (dao.deleteTask(delTask)) {
                response = new deleteTaskResponse(delTask.name, 200);
            } else {
                response = new deleteTaskResponse(delTask.name, 422, "Unable to delete task.");
            }
        } catch (Exception e) {
            response = new deleteTaskResponse(req.idTask, 403, "Unable to delete task: " + req.idTask + "(" + e.getMessage() + ")");
        }

        return response;
    }
}
