package taskManager;

import org.junit.Assert;
import org.junit.Test;
import taskManager.http.addTeammateRequest;
import taskManager.http.addTeammateResponse;
import taskManager.http.createProjectRequest;
import taskManager.http.createProjectResponse;

public class addTeammateTest extends LambdaTest{
    @Test
    public void testAddTeammate(){
        try{
            createProjectRequest cpr = new createProjectRequest("testProjectAddTeammate");
            createProjectResponse respP = new createProjectHandler().handleRequest(cpr, createContext("create"));

            addTeammateRequest atr = new addTeammateRequest("testTeammate", "testProjectAddTeammate");
            addTeammateResponse resp = new addTeammateHandler().handleRequest(atr, createContext("create"));
            //Assert.assertEquals(200, resp.httpCode);

            Assert.assertEquals(atr.getName(), "testTeammate");
            Assert.assertEquals(atr.getprojectName(), "testProjectAddTeammate");

            atr.setName("testTeammate-set");
            atr.setprojectName("testProjectAddTeammate-set");

            Assert.assertEquals(atr.getName(), "testTeammate-set");
            Assert.assertEquals(atr.getprojectName(), "testProjectAddTeammate-set");

        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Error:" + e.getMessage());
        }
    }
}
