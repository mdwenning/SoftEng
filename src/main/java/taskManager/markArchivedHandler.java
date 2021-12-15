package taskManager;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import taskManager.db.projectsDAO;
import taskManager.http.markArchivedRequest;
import taskManager.http.markArchivedResponse;

public class markArchivedHandler implements RequestHandler<markArchivedRequest, markArchivedResponse> {
    @Override
    public markArchivedResponse handleRequest(markArchivedRequest req, Context context){
        markArchivedResponse response;
        try{
            projectsDAO dao = new projectsDAO();
            dao.toggleArchived(req.name);
            response = new markArchivedResponse(req.name, 200);
        }
        catch(Exception e){
            response = new markArchivedResponse(403, e.getMessage());
        }
        return response;
    }
}
