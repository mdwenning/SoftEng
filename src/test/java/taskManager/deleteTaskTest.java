package taskManager;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;
import taskManager.http.*;

public class deleteTaskTest extends LambdaTest{
    @Test
    public void testDeleteTask(){
        try {
            deleteTaskRequest dpr = new deleteTaskRequest("New task", "newestProject");
            deleteTaskResponse resp = new deleteTaskHandler().handleRequest(dpr, createContext("delete"));

            //addTaskRequest atr = new addTaskRequest("test task","testProjectTask");
            //addTaskResponse atrsp = new addTaskHandler().handleRequest(atr, createContext("create"));

            Assert.assertEquals(200, resp.statusCode);



        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Error:" + e.getMessage());
        }
    }
}
