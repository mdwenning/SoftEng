package taskManager;

import org.junit.Assert;
import org.junit.Test;
import taskManager.http.*;

public class deleteAssignmentTest extends LambdaTest{
    @Test
    public void testDeleteAssignment(){
        try{
            deleteProjectRequest dpr = new deleteProjectRequest("testProjectAssignDel");
            deleteProjectResponse respD = new deleteProjectHandler().handleRequest(dpr, createContext("create"));

            createProjectRequest cpr = new createProjectRequest("testProjectAssignDel");
            createProjectResponse respC = new createProjectHandler().handleRequest(cpr, createContext("create"));

            addTaskRequest atr1 = new addTaskRequest("assignDelTestTask1","testProjectAssignDel");
            addTaskResponse atrsp1 = new addTaskHandler().handleRequest(atr1, createContext("create"));
            addTaskRequest atr2 = new addTaskRequest("assignDelTestTask2","testProjectAssignDel");
            addTaskResponse atrsp2 = new addTaskHandler().handleRequest(atr2, createContext("create"));

            addTeammateRequest atmr1 = new addTeammateRequest("assignDelTestTeammate", "testProjectAssignDel");
            addTeammateResponse respT1 = new addTeammateHandler().handleRequest(atmr1, createContext("create"));

            assignRequest ar1 = new assignRequest("assignDelTestTeammate", "testProjectAssignDel", atrsp1.getTaskID());
            assignResponse assignresp1 = new assignmentHandler().handleRequest(ar1, createContext("create"));
            assignRequest ar2 = new assignRequest("assignDelTestTeammate", "testProjectAssignDel", atrsp2.getTaskID());
            assignResponse assignresp2 = new assignmentHandler().handleRequest(ar2, createContext("create"));

            assignRequest ar11 = new assignRequest("assignDelTestTeammate", "testProjectAssignDel", atrsp1.getTaskID());
            assignResponse resp1 = new assignmentHandler().handleRequest(ar11, createContext("create"));
            assignRequest ar22 = new assignRequest("assignDelTestTeammate", "testProjectAssignDel", atrsp2.getTaskID());
            assignResponse resp2 = new assignmentHandler().handleRequest(ar22, createContext("create"));


            Assert.assertEquals(200, resp1.httpCode);
            Assert.assertEquals(200, resp2.httpCode);

        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Error:" + e.getMessage());
        }
    }
}
