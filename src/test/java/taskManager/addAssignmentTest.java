package taskManager;

import org.junit.Assert;
import org.junit.Test;
import taskManager.http.assignRequest;
import taskManager.http.assignResponse;
public class addAssignmentTest extends LambdaTest{
    @Test
    public void testAddAssignment(){
        assignRequest ar = new assignRequest("Steve Jobs", "2: Make another task", "tp3");
        assignResponse resp = new assignmentHandler().handleRequest(ar, createContext("create"));
        Assert.assertEquals(400, resp.httpCode);
    }
}
