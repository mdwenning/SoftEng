package taskManager;

import org.junit.Assert;
import org.junit.Test;
import taskManager.http.*;

public class listTeammatesTest extends LambdaTest{
    @Test
    public void testListTeammates(){
        //deleteAllProjectsResponse respD = deleteAllProjectsHandler.deleteAllProjectsFromDB();

        createProjectRequest cprC = new createProjectRequest("listTeamProjectTest");
        createProjectResponse respC = new createProjectHandler().handleRequest(cprC, createContext("create"));

        addTeammateRequest atr1 = new addTeammateRequest("testListTeammate1", "listTeamProjectTest");
        addTeammateResponse resp1 = new addTeammateHandler().handleRequest(atr1, createContext("create"));
        addTeammateRequest atr2 = new addTeammateRequest("testListTeammate2", "listTeamProjectTest");
        addTeammateResponse resp2 = new addTeammateHandler().handleRequest(atr2, createContext("create"));
        addTeammateRequest atr3 = new addTeammateRequest("testListTeammate3", "listTeamProjectTest");
        addTeammateResponse resp3 = new addTeammateHandler().handleRequest(atr3, createContext("create"));

        listTeammatesRequest rq = new listTeammatesRequest("listTeamProjectTest");
        listTeammatesResponse resp = new listTeammatesHandler().handleRequest(rq, createContext("create"));
        Assert.assertEquals(200, resp.statusCode);

        Assert.assertEquals(rq.getName(), "listTeamProjectTest");
        rq.setName("listTeamProjectTest-set");
        Assert.assertEquals(rq.getName(), "listTeamProjectTest-set");

        listTeammatesResponse respErr = new listTeammatesResponse(400, "errMsg");
        Assert.assertEquals(400, respErr.statusCode);
        Assert.assertEquals("errMsg", respErr.error);
    }
}
