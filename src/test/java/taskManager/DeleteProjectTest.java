package taskManager;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;
import taskManager.http.createProjectRequest;
import taskManager.http.createProjectResponse;
import taskManager.http.deleteProjectRequest;
import taskManager.http.deleteProjectResponse;

public class DeleteProjectTest extends LambdaTest{
    @Test
    public void testCreateProject(){
        try {
            createProjectRequest cpr = new createProjectRequest("testProjectDelete");
            createProjectResponse respC = new createProjectHandler().handleRequest(cpr, createContext("create"));

            deleteProjectRequest dpr = new deleteProjectRequest("testProjectDelete");
            deleteProjectResponse resp = new deleteProjectHandler().handleRequest(dpr, createContext("delete"));
            Assert.assertEquals(200, resp.statusCode);
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Error:" + e.getMessage());
        }
    }
}
