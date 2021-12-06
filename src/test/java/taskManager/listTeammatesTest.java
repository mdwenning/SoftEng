package taskManager;

import org.junit.Assert;
import org.junit.Test;
import taskManager.http.listTeammatesRequest;
import taskManager.http.listTeammatesResponse;

public class listTeammatesTest extends LambdaTest{
    @Test
    public void testListTeammates(){
        listTeammatesRequest ltr = new listTeammatesRequest("newProj");
        listTeammatesResponse resp = new listTeammatesHandler().handleRequest(ltr, createContext("create"));
        Assert.assertEquals(403, resp.statusCode);
    }
}
