package taskManager;

import org.junit.Assert;
import org.junit.Test;
import taskManager.http.*;

public class deleteTeammateAndGetAllTasksTest extends LambdaTest{
    @Test
    public void testAddTeammateAndGetTasks(){
        try{
            deleteProjectRequest dprD = new deleteProjectRequest("testProjectDelTeammateTsk");
            deleteProjectResponse respD = new deleteProjectHandler().handleRequest(dprD, createContext("create"));

            createProjectRequest cpr = new createProjectRequest("testProjectDelTeammateTsk");
            createProjectResponse respP = new createProjectHandler().handleRequest(cpr, createContext("create"));

            addTeammateRequest atrA = new addTeammateRequest("testTeammateDTsk", "testProjectDelTeammateTsk");
            addTeammateResponse respA = new addTeammateHandler().handleRequest(atrA, createContext("create"));

            addTaskRequest atr = new addTaskRequest("testTeammateDTsk","testProjectDelTeammateTsk");
            addTaskResponse atrsp = new addTaskHandler().handleRequest(atr, createContext("create"));

            assignRequest ar1 = new assignRequest("testTeammateDTsk", "testProjectDelTeammateTsk", atrsp.getTaskID());
            assignResponse assignresp1 = new assignmentHandler().handleRequest(ar1, createContext("create"));


            deleteTeammateRequest dtr = new deleteTeammateRequest("testTeammateDTsk", "testProjectDelTeammateTsk");
            deleteTeammateResponse resp = new deleteTeammateHandler().handleRequest(dtr, createContext("create"));
            Assert.assertEquals(200, resp.statusCode);

            loadProjectRequest ltrL = new loadProjectRequest("testProjectDelTeammateTsk");
            loadProjectResponse respL = new loadProjectHandler().handleRequest(ltrL, createContext("create"));

            Assert.assertEquals(200, respL.statusCode);

            deleteProjectRequest dprDD = new deleteProjectRequest("testProjectDelTeammateTsk");
            deleteProjectResponse respDD = new deleteProjectHandler().handleRequest(dprDD, createContext("create"));


        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Error:" + e.getMessage());
        }
    }
}
