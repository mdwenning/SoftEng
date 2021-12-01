package taskManager;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import taskManager.db.projectsDAO;
import taskManager.http.createProjectRequest;
import taskManager.http.createProjectResponse;
import taskManager.model.Project;

public class createProjectHandler implements RequestHandler<createProjectRequest, createProjectResponse> {

    boolean createProject(String name) throws Exception{
        projectsDAO dao = new projectsDAO();
        Project exist = dao.getProject(name);
        Project project = new Project(name);
        if(exist == null){
            return dao.addProject(project);
        }
        else{
            return false;
        }
    }
    @Override
    public createProjectResponse handleRequest(createProjectRequest req, Context context) {
        createProjectResponse response;
        try{
            if (createProject(req.name)) {
                response = new createProjectResponse(req.name);
            }
            else {
                response = new createProjectResponse(req.name, 422);
            }
        }
        catch(Exception e){
            response = new createProjectResponse("Unable to create project: " + req.name + "(" + e.getMessage() + ")", 400);
        }
        return response;
    }
}
