package taskManager;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;
import taskManager.http.createProjectRequest;
import taskManager.http.createProjectResponse;

public class CreateProjectTest extends LambdaTest{
    @Test
    public void testCreateProject(){
        createProjectRequest cpr = new createProjectRequest("testProject3");
        createProjectResponse resp = new createProjectHandler().handleRequest(cpr, createContext("create"));
        Assert.assertEquals(400, resp.httpCode);
    }
}
