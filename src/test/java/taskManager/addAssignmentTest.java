package taskManager;

import org.junit.Assert;
import org.junit.Test;
import taskManager.http.*;

public class addAssignmentTest extends LambdaTest{
    @Test
    public void testAddAssignment(){
        try{
//            deleteProjectRequest dpr = new deleteProjectRequest("testProjectAssign");
//            deleteProjectResponse resp = new deleteProjectHandler().handleRequest(dpr, createContext("delete"));
//
//            createProjectRequest cpr = new createProjectRequest("testProjectAssign");
//            createProjectResponse respC = new createProjectHandler().handleRequest(cpr, createContext("create"));
//
//            addTaskRequest atrT = new addTaskRequest("testTask4Assign","testProjectAssign");
//            addTaskResponse atrspT = new addTaskHandler().handleRequest(atrT, createContext("create"));
//
//            addTeammateRequest atr = new addTeammateRequest("testTeammate1", "testProjectAssign");
//            addTeammateResponse respT = new addTeammateHandler().handleRequest(atr, createContext("create"));

//           assignRequest ar = new assignRequest("Teammate1", "TestProj1", "b5daf4be-70da-4b03-85f7-074d71a88804");
//           assignResponse resp = new assignmentHandler().handleRequest(ar, createContext("create"));
//           Assert.assertEquals(200, resp.httpCode);
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Error:" + e.getMessage());
        }
    }
}
