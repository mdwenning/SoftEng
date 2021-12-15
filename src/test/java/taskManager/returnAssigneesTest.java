package taskManager;

import org.junit.Assert;
import org.junit.Test;
import taskManager.http.listTasksRequest;
import taskManager.http.listTasksResponse;

public class returnAssigneesTest extends LambdaTest{

    @Test
    public void returnAssignees(){
        listTasksRequest ltr = new listTasksRequest("tp3");
        listTasksResponse resp = new listTasksHandler().handleRequest(ltr, createContext("create"));
        Assert.assertEquals(200, resp.statusCode);
    }
}
