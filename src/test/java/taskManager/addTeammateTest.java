package taskManager;

import org.junit.Assert;
import org.junit.Test;
import taskManager.http.*;

public class addTeammateTest extends LambdaTest{
    @Test
    public void testAddTeammate(){
        try{
            deleteProjectRequest dprD = new deleteProjectRequest("testProjectAddTeammate");
            deleteProjectResponse respD = new deleteProjectHandler().handleRequest(dprD, createContext("create"));

            createProjectRequest cpr = new createProjectRequest("testProjectAddTeammate");
            createProjectResponse respP = new createProjectHandler().handleRequest(cpr, createContext("create"));

            addTeammateRequest atr = new addTeammateRequest("testTeammate", "testProjectAddTeammate");
            addTeammateResponse resp = new addTeammateHandler().handleRequest(atr, createContext("create"));
            Assert.assertEquals(200, resp.httpCode);

            Assert.assertEquals("Response(testTeammate: testProjectAddTeammate)", resp.toString());
            Assert.assertEquals(atr.getName(), "testTeammate");
            Assert.assertEquals(atr.getProjectName(), "testProjectAddTeammate");

            atr.setName("testTeammate-set");
            atr.setProjectName("testProjectAddTeammate-set");

            Assert.assertEquals(atr.getName(), "testTeammate-set");
            Assert.assertEquals(atr.getProjectName(), "testProjectAddTeammate-set");

            addTeammateResponse atrspErrTest = new addTeammateResponse("Error test", resp.httpCode);
            Assert.assertEquals(atrspErrTest.error, "Error test");
            Assert.assertEquals(resp.httpCode, atrspErrTest.httpCode);

            addTeammateRequest atmrqConstructorTest = new addTeammateRequest();

            deleteProjectRequest dprDD = new deleteProjectRequest("testProjectAddTeammate");
            deleteProjectResponse respDD = new deleteProjectHandler().handleRequest(dprDD, createContext("create"));

        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Error:" + e.getMessage());
        }
    }
}
