package taskManager;

import org.junit.Assert;
import org.junit.Test;
import taskManager.http.assignRequest;
import taskManager.http.assignResponse;
public class addAssignmentTest extends LambdaTest{
    @Test
    public void testAddAssignment(){
        assignRequest ar = new assignRequest("teammate1", "1: Task 1", "newestProj");
        assignResponse resp = new assignmentHandler().handleRequest(ar, createContext("create"));
        Assert.assertEquals(400, resp.httpCode);
    }
}
