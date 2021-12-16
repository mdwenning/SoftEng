package taskManager;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;
import taskManager.http.*;

public class markTaskCompleteTest extends LambdaTest{
    @Test
    public void testTaskMarkComplete(){
        try {
            deleteProjectRequest dprD = new deleteProjectRequest("testTaskMarkComplete");
            deleteProjectResponse respD = new deleteProjectHandler().handleRequest(dprD, createContext("create"));

            createProjectRequest cpr = new createProjectRequest("testTaskMarkComplete");
            createProjectResponse resp = new createProjectHandler().handleRequest(cpr, createContext("create"));

            addTaskRequest atr1 = new addTaskRequest("markCompleteTestTask1","testTaskMarkComplete");
            addTaskResponse atrsp1 = new addTaskHandler().handleRequest(atr1, createContext("create"));

            addTeammateRequest atmr1 = new addTeammateRequest("markCompleteTeammate1", "testTaskMarkComplete");
            addTeammateResponse resptm1 = new addTeammateHandler().handleRequest(atmr1, createContext("create"));

            assignRequest ar1 = new assignRequest("markCompleteTeammate1", "testTaskMarkComplete", atrsp1.getTaskID());
            assignResponse assignresp1 = new assignmentHandler().handleRequest(ar1, createContext("create"));

            markTaskCompleteRequest mrq = new markTaskCompleteRequest(atrsp1.getTaskID());
            markTaskCompleteResponse mrsp = new markTaskCompleteHandler().handleRequest(mrq, createContext("create"));
            Assert.assertEquals(200, mrsp.statusCode);


            //Toggle Complete Check
//            markTaskCompleteRequest mrq2 = new markTaskCompleteRequest(atrsp1.getTaskID());
//            markTaskCompleteResponse mrsp2 = new markTaskCompleteHandler().handleRequest(mrq2, createContext("create"));
//            Assert.assertEquals(200, mrsp2.statusCode);

            Assert.assertEquals(atrsp1.getTaskID(), mrq.getIdTask());
            mrq.setIdTask("taskID-setTest");
            Assert.assertEquals("taskID-setTest", mrq.getIdTask());

            markTaskCompleteResponse arErr = new markTaskCompleteResponse(400, "err");
            Assert.assertEquals("err", arErr.error);
            Assert.assertEquals(400, arErr.statusCode);

            markTaskCompleteRequest mtcrqConstructorTest = new markTaskCompleteRequest();

            deleteProjectRequest dprDD = new deleteProjectRequest("testTaskMarkComplete");
            deleteProjectResponse respDD = new deleteProjectHandler().handleRequest(dprDD, createContext("create"));


        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Error:" + e.getMessage());
        }
    }
}
