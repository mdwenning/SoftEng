package taskManager;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import taskManager.db.projectsDAO;
import taskManager.http.addTaskRequest;
import taskManager.http.addTaskResponse;
import taskManager.http.assignRequest;
import taskManager.http.assignResponse;
import taskManager.model.Assignment;
import taskManager.model.Project;
import taskManager.model.Task;
import taskManager.model.Teammate;

public class assignmentHandler implements RequestHandler<assignRequest, assignResponse> {
    void toggleAssignment(String name, String projectName, String idTask) throws Exception{
        projectsDAO dao = new projectsDAO();
        Task task = dao.getTask(idTask);
        Teammate tm = dao.getTeammate(name, projectName);
        Assignment assignment = new Assignment(task.idTask, tm.idTeammate, task.idProject);
        dao.toggleAssignment(assignment);
    }
    @Override
    public assignResponse handleRequest(assignRequest req, Context context){
        assignResponse response;
        try{
            toggleAssignment(req.name, req.projectName, req.idTask);
            response = new assignResponse(req.name, req.idTask, 200);

        }
        catch(Exception e){
            response = new assignResponse("Unable to execute assignment: " + e.getMessage(), 400);
        }
        return response;
    }
}
