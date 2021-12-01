package taskManager;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import taskManager.db.projectsDAO;
import taskManager.http.listProjectsResponse;
import taskManager.model.Project;

import java.util.List;

public class listProjectsHandler implements RequestHandler<Object, listProjectsResponse> {
    List<Project> getProjects() throws Exception{
        projectsDAO dao = new projectsDAO();
        return dao.getAllProjects();
    }
    @Override
    public listProjectsResponse handleRequest(Object input, Context context){
        listProjectsResponse response;
        try{
            List<Project> list = getProjects();
            response = new listProjectsResponse(list, 200);
        }
        catch(Exception e){
            response = new listProjectsResponse(403, e.getMessage());
        }
        return response;
    }
}
