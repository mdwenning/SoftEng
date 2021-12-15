package taskManager;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import taskManager.db.projectsDAO;
import taskManager.http.renameTaskRequest;
import taskManager.http.renameTaskResponse;

public class renameTaskHandler implements RequestHandler<renameTaskRequest, renameTaskResponse> {

    @Override
    public renameTaskResponse handleRequest(renameTaskRequest req, Context context){
        renameTaskResponse response;
        try{
            projectsDAO dao = new projectsDAO();
            if(dao.renameTask(req.idTask, req.newName)){
                response = new renameTaskResponse(req.newName, req.idTask, 200);
            }
            else{
                response = new renameTaskResponse("Failed to rename project", 400);
            }
        }
        catch(Exception e){
            response = new renameTaskResponse(e.getMessage(), 403);
        }
        return response;
    }
}
