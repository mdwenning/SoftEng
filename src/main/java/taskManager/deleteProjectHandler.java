package taskManager;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import taskManager.db.projectsDAO;
import taskManager.http.deleteProjectRequest;
import taskManager.http.deleteProjectResponse;

public class deleteProjectHandler implements RequestHandler<deleteProjectRequest, deleteProjectResponse> {
    @Override
    public deleteProjectResponse handleRequest(deleteProjectRequest req, Context context){
        deleteProjectResponse response = null;
        projectsDAO dao = new projectsDAO();
        try {
            if (dao.deleteProject(req.name)) {
                response = new deleteProjectResponse(req.name, 200);
            } else {
                response = new deleteProjectResponse(req.name, 422, "Unable to delete project.");
            }
        } catch (Exception e) {
            response = new deleteProjectResponse(req.name, 403, "Unable to delete project: " + req.name + "(" + e.getMessage() + ")");
        }

        return response;
    }
}
