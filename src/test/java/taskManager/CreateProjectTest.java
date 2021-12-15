package taskManager;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;
import taskManager.http.createProjectRequest;
import taskManager.http.createProjectResponse;
import taskManager.http.deleteProjectRequest;
import taskManager.http.deleteProjectResponse;

public class CreateProjectTest extends LambdaTest{
    @Test
    public void testCreateProject(){
        try {
            try{
                deleteProjectRequest dpr = new deleteProjectRequest("testProjectCreate");
                deleteProjectResponse respD = new deleteProjectHandler().handleRequest(dpr, createContext("delete"));

            }
            catch (Exception e){
                System.out.println("CreateProjectTest - Project Doesn't Exist - Continue: " + e.getMessage());
            }

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
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Error:" + e.getMessage());
        }
    }
}
