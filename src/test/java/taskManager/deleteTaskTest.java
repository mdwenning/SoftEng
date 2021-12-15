package taskManager;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;
import taskManager.http.*;

public class deleteTaskTest extends LambdaTest{
    @Test
    public void testDeleteTask(){
        try {
            deleteProjectRequest dprD = new deleteProjectRequest("testTaskDelete");
            deleteProjectResponse respD = new deleteProjectHandler().handleRequest(dprD, createContext("create"));

            createProjectRequest cpr = new createProjectRequest("testTaskDelete");
            createProjectResponse respC = new createProjectHandler().handleRequest(cpr, createContext("create"));

            addTaskRequest atr1 = new addTaskRequest("testTaskDelete1","testTaskDelete");
            addTaskResponse atrsp1 = new addTaskHandler().handleRequest(atr1, createContext("create"));

            deleteTaskRequest dpr = new deleteTaskRequest(atrsp1.getTaskID());
            deleteTaskResponse resp = new deleteTaskHandler().handleRequest(dpr, createContext("delete"));
            Assert.assertEquals(200, resp.statusCode);

            deleteTaskResponse respErrTest = new deleteTaskResponse("delTaskTst", 200, "errorMsg");
            Assert.assertEquals("delTaskTst", respErrTest.name);
            Assert.assertEquals(200, respErrTest.statusCode);
            Assert.assertEquals("errorMsg", respErrTest.error);
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Error:" + e.getMessage());
        }
    }
}
