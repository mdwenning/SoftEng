package taskManager;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import taskManager.db.projectsDAO;
import taskManager.http.addTaskRequest;
import taskManager.http.addTaskResponse;
import taskManager.model.Project;
import taskManager.model.Task;

public class addTaskHandler implements RequestHandler<addTaskRequest, addTaskResponse> {
    String id;
    boolean addTask(String name, String projectName) throws Exception{
        int sequence;
        projectsDAO dao = new projectsDAO();
        Project project = dao.getProject(projectName);
        sequence = dao.getNextSequence(project.name, null);
        Task task = new Task(name, project.idProject, sequence);
        id = task.idTask;
        return dao.addTask(task, projectName);
    }

    @Override
    public addTaskResponse handleRequest(addTaskRequest req, Context context){
        addTaskResponse response;
        try{
            if(addTask(req.name, req.projectName)){
                response = new addTaskResponse(req.name, req.projectName, id, 200);
            }
            else{
                response = new addTaskResponse("Error adding task", 422);
            }
        }
        catch(Exception e){
            response = new addTaskResponse("Unable to add task: " + e.getMessage(), 400);
        }
        return response;
    }
}
