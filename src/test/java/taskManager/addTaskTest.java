package taskManager;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;
import taskManager.http.*;

public class addTaskTest extends LambdaTest{
    @Test
    public void testAddTask(){
        try {
            deleteProjectRequest dprD = new deleteProjectRequest("testProjectTask");
            deleteProjectResponse respD = new deleteProjectHandler().handleRequest(dprD, createContext("create"));

            createProjectRequest cpr = new createProjectRequest("testProjectTask");
            createProjectResponse respC = new createProjectHandler().handleRequest(cpr, createContext("create"));

            addTaskRequest atr = new addTaskRequest("test task","testProjectTask");
            addTaskResponse atrsp = new addTaskHandler().handleRequest(atr, createContext("create"));

            Assert.assertEquals(200, atrsp.httpCode);

            Assert.assertEquals(atrsp.toString(), "Task(test task:testProjectTask)");
            Assert.assertEquals(atr.getName(), "test task");

            listTasksRequest ltr = new listTasksRequest("testProjectTask");
            listTasksResponse resp = new listTasksHandler().handleRequest(ltr, createContext("create"));
            Assert.assertEquals(200, resp.statusCode);

            atr.setName("test task - set");
            Assert.assertEquals(atr.getName(), "test task - set");
            Assert.assertEquals(atr.getprojectName(), "testProjectTask");

            atr.setprojectName("testProjectTask - set");
            Assert.assertEquals(atr.getprojectName(), "testProjectTask - set");

            addTaskResponse atrspErrTest = new addTaskResponse("Error test", resp.statusCode);
            Assert.assertEquals(atrspErrTest.error, "Error test");
            Assert.assertEquals(resp.statusCode, atrspErrTest.httpCode);

            addTaskRequest atrqConstructorTest = new addTaskRequest();
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Error:" + e.getMessage());
        }
    }

}
