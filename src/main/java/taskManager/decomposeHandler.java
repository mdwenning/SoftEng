package taskManager;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import taskManager.db.projectsDAO;
import taskManager.http.decomposeRequest;
import taskManager.http.decomposeResponse;


public class decomposeHandler implements RequestHandler<decomposeRequest, decomposeResponse> {
    @Override
    public decomposeResponse handleRequest(decomposeRequest req, Context context){
        decomposeResponse response;
        try{
            projectsDAO dao = new projectsDAO();
            dao.decompose(req.name, req.projectName, req.idParent);
            response = new decomposeResponse(req.name, req.projectName, req.idParent, 200);
        }
        catch(Exception e){
            response = new decomposeResponse("unable to decompose task: " + e.getMessage(), 400);
        }
        return response;
    }
}
