package taskManager;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;
import taskManager.http.*;

public class deleteAllProjectsTest extends LambdaTest{
    @Test
    public void testDeleteAllProjects(){
        try {
            deleteAllProjectsResponse resp = deleteAllProjectsHandler.deleteAllProjectsFromDB();
            Assert.assertEquals(200, resp.statusCode);

            deleteAllProjectsResponse respErr = new deleteAllProjectsResponse(400, "errMsg");
            Assert.assertEquals(400, respErr.statusCode);
            Assert.assertEquals("errMsg", respErr.error);
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Error:" + e.getMessage());
        }
    }
}

