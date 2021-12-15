package taskManager;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import taskManager.db.projectsDAO;
import taskManager.http.deleteAllProjectsResponse;
import taskManager.http.deleteProjectRequest;
import taskManager.http.deleteProjectResponse;

public class deleteAllProjectsHandler {//implements RequestHandler<deleteProjectRequest, deleteProjectResponse> {
    //@Override
    public static deleteAllProjectsResponse deleteAllProjectsFromDB(){
        deleteAllProjectsResponse response = null;
        projectsDAO dao = new projectsDAO();
        try {
            dao.deleteAllProjects();
            response = new deleteAllProjectsResponse(200);

        } catch (Exception e) {
            response = new deleteAllProjectsResponse(403, "Unable to delete all projects");
        }
        return response;
    }
}
