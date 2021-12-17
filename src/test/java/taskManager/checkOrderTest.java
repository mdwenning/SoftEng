package taskManager;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;
import taskManager.db.projectsDAO;
import taskManager.http.*;
import taskManager.model.Task;

public class checkOrderTest extends LambdaTest{
    @Test
    public void testTaskOrder(){
        try {
            deleteProjectRequest dprD = new deleteProjectRequest("testProjectTaskOrder");
            deleteProjectResponse respD = new deleteProjectHandler().handleRequest(dprD, createContext("create"));

            createProjectRequest cpr = new createProjectRequest("testProjectTaskOrder");
            createProjectResponse respC = new createProjectHandler().handleRequest(cpr, createContext("create"));

            addTaskRequest atr1 = new addTaskRequest("test task1","testProjectTaskOrder");
            addTaskResponse atrsp1 = new addTaskHandler().handleRequest(atr1, createContext("create"));
            decomposeRequest dctr = new decomposeRequest("test task1_1", "testProjectTaskOrder", atrsp1.getTaskID());
            decomposeResponse dctrsp = new decomposeHandler().handleRequest(dctr, createContext("create"));
            addTaskRequest atr2 = new addTaskRequest("test task2","testProjectTaskOrder");
            addTaskResponse atrsp2 = new addTaskHandler().handleRequest(atr2, createContext("create"));
            addTaskRequest atr3 = new addTaskRequest("test task3","testProjectTaskOrder");
            addTaskResponse atrsp3 = new addTaskHandler().handleRequest(atr3, createContext("create"));

            listTasksRequest ltrq = new listTasksRequest("testProjectTaskOrder");
            listTasksResponse ltrps = new listTasksHandler().handleRequest(ltrq, createContext("create"));
            //Assert.assertEquals(1, ltrps.list);

            deleteProjectRequest dprDD = new deleteProjectRequest("testProjectTaskOrder");
            deleteProjectResponse respDD = new deleteProjectHandler().handleRequest(dprDD, createContext("create"));
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Error:" + e.getMessage());
        }
    }
}
