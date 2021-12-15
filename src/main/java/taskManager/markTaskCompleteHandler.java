package taskManager;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import taskManager.db.projectsDAO;
import taskManager.http.markTaskCompleteRequest;
import taskManager.http.markTaskCompleteResponse;

public class markTaskCompleteHandler implements RequestHandler<markTaskCompleteRequest, markTaskCompleteResponse> {
    @Override
    public markTaskCompleteResponse handleRequest(markTaskCompleteRequest req, Context context){
        markTaskCompleteResponse response;
        try{
            projectsDAO dao = new projectsDAO();
            dao.toggleComplete(req.idTask);
            response = new markTaskCompleteResponse(req.idTask, 200);
        }
        catch(Exception e){
            response = new markTaskCompleteResponse(403, e.getMessage());
        }
        return response;
    }
}
