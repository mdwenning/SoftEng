package taskManager;

import org.junit.Assert;
import org.junit.Test;
import taskManager.http.*;

public class addAssignmentTest extends LambdaTest{
    @Test
    public void testAddAssignment(){
        try{
            deleteProjectRequest dpr = new deleteProjectRequest("testProjectAssign");
            deleteProjectResponse respD = new deleteProjectHandler().handleRequest(dpr, createContext("create"));

            createProjectRequest cpr = new createProjectRequest("testProjectAssign");
            createProjectResponse respC = new createProjectHandler().handleRequest(cpr, createContext("create"));

            addTaskRequest atr1 = new addTaskRequest("assignTestTask1","testProjectAssign");
            addTaskResponse atrsp1 = new addTaskHandler().handleRequest(atr1, createContext("create"));
            addTaskRequest atr2 = new addTaskRequest("assignTestTask2","testProjectAssign");
            addTaskResponse atrsp2 = new addTaskHandler().handleRequest(atr2, createContext("create"));

            addTeammateRequest atmr1 = new addTeammateRequest("assignTestTeammate", "testProjectAssign");
            addTeammateResponse respT1 = new addTeammateHandler().handleRequest(atmr1, createContext("create"));

            assignRequest ar1 = new assignRequest("assignTestTeammate", "testProjectAssign", atrsp1.getTaskID());
            assignResponse assignresp1 = new assignmentHandler().handleRequest(ar1, createContext("create"));
            assignRequest ar2 = new assignRequest("assignTestTeammate", "testProjectAssign", atrsp2.getTaskID());
            assignResponse assignresp2 = new assignmentHandler().handleRequest(ar2, createContext("create"));
            Assert.assertEquals(200, assignresp1.httpCode);
            Assert.assertEquals(200, assignresp2.httpCode);

            Assert.assertEquals(ar1.getName(), "assignTestTeammate");
            Assert.assertEquals(ar1.getProjectName(), "testProjectAssign");
            Assert.assertEquals(ar1.getidTask(), assignresp1.idTask);

            ar1.setName("assignTestTeammate-set");
            ar1.setProjectName("testProjectAssign-set");
            ar1.setidTask("1234-testID");

            Assert.assertEquals(ar1.getName(), "assignTestTeammate-set");
            Assert.assertEquals(ar1.getProjectName(), "testProjectAssign-set");
            Assert.assertEquals(ar1.getidTask(), "1234-testID");

            assignResponse arErr = new assignResponse("err", 400);
            Assert.assertEquals("err", arErr.error);
            Assert.assertEquals(400, arErr.httpCode);
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Error:" + e.getMessage());
        }
    }
}
