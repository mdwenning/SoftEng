package taskManager;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import taskManager.db.projectsDAO;
import taskManager.http.listTeammatesRequest;
import taskManager.http.listTeammatesResponse;
import taskManager.model.Teammate;

import java.util.List;

public class listTeammatesHandler implements RequestHandler<listTeammatesRequest, listTeammatesResponse> {
    List<Teammate> getTeammates(String projectName) throws Exception{
        projectsDAO dao = new projectsDAO();
        return dao.getAllTeammates(projectName);
    }
    @Override
    public listTeammatesResponse handleRequest(listTeammatesRequest req, Context context){
        listTeammatesResponse response;
        try{
           List<Teammate> list = getTeammates(req.projName);
           response = new listTeammatesResponse(list, 200);
        }
        catch(Exception e){
            response = new listTeammatesResponse(403, e.getMessage());
        }
        return response;
    }
}
