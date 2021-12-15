package taskManager;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;
import taskManager.http.*;

public class deleteTaskTest extends LambdaTest{
    @Test
    public void testDeleteTask(){
        try {
            deleteTaskRequest dpr = new deleteTaskRequest("f55b8f91-4af4-46f0-981e-fb8681448a74");
            deleteTaskResponse resp = new deleteTaskHandler().handleRequest(dpr, createContext("delete"));
            //Assert.assertEquals(200, resp.statusCode);
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Error:" + e.getMessage());
        }
    }
}
