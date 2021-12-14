package taskManager;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import taskManager.db.projectsDAO;
import taskManager.http.deleteTeammateRequest;
import taskManager.http.deleteTeammateResponse;
import taskManager.model.Teammate;

public class deleteTeammateHandler implements RequestHandler<deleteTeammateRequest, deleteTeammateResponse> {
    @Override
    public deleteTeammateResponse handleRequest(deleteTeammateRequest req, Context context){
        deleteTeammateResponse response = null;
        projectsDAO dao = new projectsDAO();
        try {
            Teammate delTeammate = dao.getTeammate(req.name, req.projectName);
            if (dao.deleteTeammate(delTeammate)) {
                response = new deleteTeammateResponse(req.name, req.projectName, 200);
            } else {
                response = new deleteTeammateResponse(req.name, req.projectName, 422, "Unable to delete teammate.");
            }
        } catch (Exception e) {
            response = new deleteTeammateResponse(req.name, req.projectName, 403, "Unable to delete teammate: " + req.name + "(" + e.getMessage() + ")");
        }

        return response;
    }
}
