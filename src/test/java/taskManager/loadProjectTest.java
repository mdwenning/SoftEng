package taskManager;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;
import taskManager.http.*;

public class loadProjectTest extends LambdaTest{
    @Test
    public void testLoadProject(){
        try {
            deleteProjectRequest dprD = new deleteProjectRequest("testProjectLoad");
            deleteProjectResponse respD = new deleteProjectHandler().handleRequest(dprD, createContext("create"));

            createProjectRequest cprC = new createProjectRequest("testProjectLoad");
            createProjectResponse respC = new createProjectHandler().handleRequest(cprC, createContext("create"));

            addTaskRequest atr1 = new addTaskRequest("assignTestTask1","testProjectLoad");
            addTaskResponse atrsp1 = new addTaskHandler().handleRequest(atr1, createContext("create"));
            addTaskRequest atr2 = new addTaskRequest("assignTestTask2","testProjectLoad");
            addTaskResponse atrsp2 = new addTaskHandler().handleRequest(atr2, createContext("create"));
            addTaskRequest atr3 = new addTaskRequest("assignTestTask3","testProjectLoad");
            addTaskResponse atrsp3 = new addTaskHandler().handleRequest(atr3, createContext("create"));

            addTeammateRequest atmr1 = new addTeammateRequest("testTeammate1", "testProjectLoad");
            addTeammateResponse respT1 = new addTeammateHandler().handleRequest(atmr1, createContext("create"));
            addTeammateRequest atmr2 = new addTeammateRequest("testTeammate2", "testProjectLoad");
            addTeammateResponse respT2 = new addTeammateHandler().handleRequest(atmr2, createContext("create"));


            loadProjectRequest ltr = new loadProjectRequest("testProjectLoad");
            loadProjectResponse resp = new loadProjectHandler().handleRequest(ltr, createContext("create"));
            Assert.assertEquals(200, resp.statusCode);

            Assert.assertEquals("testProjectLoad", ltr.getName());
            ltr.setName("listTasksProjectTest-set");
            Assert.assertEquals("listTasksProjectTest-set", ltr.getName());

            loadProjectResponse arErr = new loadProjectResponse(400, "err");
            Assert.assertEquals("err", arErr.error);
            Assert.assertEquals(400, arErr.statusCode);

            loadProjectRequest lprqConstructorTest = new loadProjectRequest();
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Error:" + e.getMessage());
        }
    }

}
