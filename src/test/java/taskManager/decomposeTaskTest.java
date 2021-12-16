package taskManager;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;
import taskManager.http.*;

public class decomposeTaskTest extends LambdaTest{
    @Test
    public void testDecomposeTask(){
        try {
            deleteProjectRequest dprD = new deleteProjectRequest("testProjectDecompose");
            deleteProjectResponse respD = new deleteProjectHandler().handleRequest(dprD, createContext("create"));

            createProjectRequest cpr = new createProjectRequest("testProjectDecompose");
            createProjectResponse respC = new createProjectHandler().handleRequest(cpr, createContext("create"));

            addTaskRequest atr = new addTaskRequest("testTaskDecompose","testProjectDecompose");
            addTaskResponse atrsp = new addTaskHandler().handleRequest(atr, createContext("create"));

            decomposeRequest dctr = new decomposeRequest("testTaskDecompose_subTask1", "testProjectDecompose", atrsp.getTaskID());
            decomposeResponse dctrsp = new decomposeHandler().handleRequest(dctr, createContext("create"));
            Assert.assertEquals(200, dctrsp.httpCode);

            Assert.assertEquals(dctrsp.toString(), "Task(testTaskDecompose_subTask1:testProjectDecompose)");
            Assert.assertEquals(dctrsp.getTaskID(), atrsp.getTaskID());


            Assert.assertEquals(dctr.getName(), "testTaskDecompose_subTask1");
            Assert.assertEquals(dctr.getProjectName(), "testProjectDecompose");
            Assert.assertEquals(dctr.getIdParent(), atrsp.getTaskID());
            dctr.setName("testTaskDecompose_subTask1-set");
            dctr.setProjectName("testProjectDecompose-set");
            dctr.setIdParent("testTaskID-set");
            Assert.assertEquals(dctr.getName(), "testTaskDecompose_subTask1-set");
            Assert.assertEquals(dctr.getProjectName(), "testProjectDecompose-set");
            Assert.assertEquals(dctr.getIdParent(), "testTaskID-set");

            decomposeResponse respErr = new decomposeResponse("decomposeTaskErr", 400);
            Assert.assertEquals(400, respErr.httpCode);
            Assert.assertEquals("decomposeTaskErr", respErr.error);

            decomposeRequest dcrqConstructorTest = new decomposeRequest();
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Error:" + e.getMessage());
        }
    }

}
