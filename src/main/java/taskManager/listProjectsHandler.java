package taskManager;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import taskManager.db.projectsDAO;
import taskManager.http.listProjectsResponse;
import taskManager.model.Project;

import java.util.List;

public class listProjectsHandler implements RequestHandler<Object, listProjectsResponse> {

    @Override
    public listProjectsResponse handleRequest(Object input, Context context){
        listProjectsResponse response;
        try{
            projectsDAO dao = new projectsDAO();
            List<Project> list = dao.getAllProjects();
            for(Project p : list){
                p.setPerc(dao.getPerc(p.name));
            }
            response = new listProjectsResponse(list, 200);
        }
        catch(Exception e){
            response = new listProjectsResponse(403, e.getMessage());
        }
        return response;
    }
}
