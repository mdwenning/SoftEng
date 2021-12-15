package taskManager;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;
import taskManager.http.*;

public class CreateProjectTest extends LambdaTest{
    @Test
    public void testCreateProject(){
        try {
            deleteProjectRequest dpr = new deleteProjectRequest("testProjectCreate");
            deleteProjectResponse respD = new deleteProjectHandler().handleRequest(dpr, createContext("create"));

            createProjectRequest cpr = new createProjectRequest("testProjectCreate");
            createProjectResponse resp = new createProjectHandler().handleRequest(cpr, createContext("create"));
            Assert.assertEquals(200, resp.httpCode);

            Assert.assertEquals(resp.toString(), "Response(testProjectCreate)");
            Assert.assertEquals(cpr.getName(), "testProjectCreate");
            cpr.setName("testProjectCreate-set");
            Assert.assertEquals(cpr.getName(), "testProjectCreate-set");

            createProjectResponse respErr = new createProjectResponse("createProjErrTst", 400);
            Assert.assertEquals(400, respErr.httpCode);
            Assert.assertEquals("createProjErrTst", respErr.name);

            createProjectRequest cprqConstructorTest = new createProjectRequest();
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Error:" + e.getMessage());
        }
    }
}
