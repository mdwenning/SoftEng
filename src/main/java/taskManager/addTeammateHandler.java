package taskManager;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import taskManager.db.projectsDAO;
import taskManager.http.addTeammateRequest;
import taskManager.http.addTeammateResponse;
import taskManager.http.createProjectResponse;
import taskManager.model.Project;
import taskManager.model.Teammate;


public class addTeammateHandler implements RequestHandler<addTeammateRequest, addTeammateResponse> {
    boolean addTeammate(String name, String projectName) throws Exception{
        projectsDAO dao = new projectsDAO();
        Teammate exist = dao.getTeammate(name, projectName);
        Project project = dao.getProject(projectName);
        Teammate teammate = new Teammate(name, project.idProject);
        if(exist == null){
            return dao.addTeammate(teammate);
        }
        else{
            return false;
        }
    }

    @Override
    public addTeammateResponse handleRequest(addTeammateRequest req, Context context){
        addTeammateResponse response;
        try{
            if(addTeammate(req.name, req.projectName)) {
                response = new addTeammateResponse(req.name, req.projectName, 200);
            }
            else{
                response = new addTeammateResponse(req.name + " is already on this team", 422);
            }
        }
        catch(Exception e){
            response = new addTeammateResponse("Unable to add Teammate: " + req.name + " to project: " + req.projectName + "(" + e.getMessage() + ")", 400);
        }
        return response;
    }
}
