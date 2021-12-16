package taskManager;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;
import taskManager.http.*;

public class renameTaskTest extends LambdaTest{
    @Test
    public void testRenameTask(){
        try {
            deleteProjectRequest dprD = new deleteProjectRequest("testProjectRenameTask");
            deleteProjectResponse respD = new deleteProjectHandler().handleRequest(dprD, createContext("create"));

            createProjectRequest cpr = new createProjectRequest("testProjectRenameTask");
            createProjectResponse respC = new createProjectHandler().handleRequest(cpr, createContext("create"));

            addTaskRequest atr = new addTaskRequest("taskNeedsRenaming","testProjectRenameTask");
            addTaskResponse atrsp = new addTaskHandler().handleRequest(atr, createContext("create"));

            renameTaskRequest rntrq = new renameTaskRequest(atrsp.getTaskID(), "renamedTask");
            renameTaskResponse rntrsp = new renameTaskHandler().handleRequest(rntrq, createContext("create"));
            Assert.assertEquals(200, rntrsp.httpCode);
            Assert.assertEquals(atrsp.getTaskID(), rntrsp.idTask);


            Assert.assertEquals(atrsp.getTaskID(), rntrq.getIdTask());
            rntrq.setIdTask("taskID-renameTest");
            Assert.assertEquals("taskID-renameTest", rntrq.getIdTask());

            Assert.assertEquals("renamedTask", rntrq.getNewName());
            rntrq.setNewName("renamedTask-set");
            Assert.assertEquals("renamedTask-set", rntrq.getNewName());

            renameTaskResponse arErr = new renameTaskResponse("err", 400);
            Assert.assertEquals("err", arErr.error);
            Assert.assertEquals(400, arErr.httpCode);

            renameTaskRequest rtrqConstructorTest = new renameTaskRequest();

            deleteProjectRequest dprDD = new deleteProjectRequest("testProjectRenameTask");
            deleteProjectResponse respDD = new deleteProjectHandler().handleRequest(dprDD, createContext("create"));

        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Error:" + e.getMessage());
        }
    }

}
