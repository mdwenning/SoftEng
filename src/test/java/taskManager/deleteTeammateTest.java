package taskManager;

import org.junit.Assert;
import org.junit.Test;
import taskManager.http.*;

public class deleteTeammateTest extends LambdaTest{
    @Test
    public void testAddTeammate(){
        try{
            deleteProjectRequest dprD = new deleteProjectRequest("testProjectDelTeammate");
            deleteProjectResponse respD = new deleteProjectHandler().handleRequest(dprD, createContext("create"));

            createProjectRequest cpr = new createProjectRequest("testProjectDelTeammate");
            createProjectResponse respP = new createProjectHandler().handleRequest(cpr, createContext("create"));

            addTeammateRequest atrA = new addTeammateRequest("testTeammateD", "testProjectDelTeammate");
            addTeammateResponse respA = new addTeammateHandler().handleRequest(atrA, createContext("create"));
            deleteTeammateRequest dtr = new deleteTeammateRequest("testTeammateD", "testProjectDelTeammate");
            deleteTeammateResponse resp = new deleteTeammateHandler().handleRequest(dtr, createContext("create"));
            Assert.assertEquals(200, resp.statusCode);

            Assert.assertEquals("Delete(testTeammateD: testProjectDelTeammate)", dtr.toString());
            Assert.assertEquals(dtr.getName(), "testTeammateD");
            Assert.assertEquals(dtr.getProjectName(), "testProjectDelTeammate");

            dtr.setName("testTeammateD-set");
            dtr.setProjectName("testProjectDelTeammate-set");

            Assert.assertEquals(dtr.getName(), "testTeammateD-set");
            Assert.assertEquals(dtr.getProjectName(), "testProjectDelTeammate-set");

            deleteTeammateResponse dtrspErrTest = new deleteTeammateResponse("delTeammateTest", "testProjDel", 200, "Error test");
            Assert.assertEquals(dtrspErrTest.name, "delTeammateTest");
            Assert.assertEquals(dtrspErrTest.projectName, "testProjDel");
            Assert.assertEquals(200, dtrspErrTest.statusCode);
            Assert.assertEquals(dtrspErrTest.error, "Error test");

            deleteTeammateRequest dtmrqConstructorTest = new deleteTeammateRequest();
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Error:" + e.getMessage());
        }
    }
}
