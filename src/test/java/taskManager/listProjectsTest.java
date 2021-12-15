package taskManager;

import org.junit.Assert;
import org.junit.Test;
import taskManager.http.*;

public class listProjectsTest extends LambdaTest{
    @Test
    public void testListProjects(){
        deleteAllProjectsResponse respD = deleteAllProjectsHandler.deleteAllProjectsFromDB();

        createProjectRequest cpr1 = new createProjectRequest("listProject1");
        createProjectResponse resp1 = new createProjectHandler().handleRequest(cpr1, createContext("create"));
        createProjectRequest cpr2 = new createProjectRequest("listProject2");
        createProjectResponse resp2 = new createProjectHandler().handleRequest(cpr2, createContext("create"));
        createProjectRequest cpr3 = new createProjectRequest("listProject3");
        createProjectResponse resp3 = new createProjectHandler().handleRequest(cpr3, createContext("create"));

        listProjectsResponse resp = new listProjectsHandler().handleRequest(null, createContext("create"));
        Assert.assertEquals(200, resp.statusCode);

        Assert.assertEquals("All Projects(3)", resp.toString());

        listProjectsResponse respErr = new listProjectsResponse(400, "errMsg");
        Assert.assertEquals(400, respErr.statusCode);
        Assert.assertEquals("errMsg", respErr.error);
    }
}
