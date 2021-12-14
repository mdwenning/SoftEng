package taskManager;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;
import taskManager.http.*;

public class DeleteProjectTest extends LambdaTest{
    @Test
    public void testDeleteProject(){
        try {
            createProjectRequest cpr = new createProjectRequest("testProjectDelete");
            createProjectResponse respC = new createProjectHandler().handleRequest(cpr, createContext("create"));

            addTaskRequest atr1 = new addTaskRequest("test task1","testProjectDelete");
            addTaskResponse atrsp1 = new addTaskHandler().handleRequest(atr1, createContext("create"));
            addTaskRequest atr2 = new addTaskRequest("test task2","testProjectDelete");
            addTaskResponse atrsp2 = new addTaskHandler().handleRequest(atr2, createContext("create"));
            addTaskRequest atr3 = new addTaskRequest("test task3","testProjectDelete");
            addTaskResponse atrsp3 = new addTaskHandler().handleRequest(atr3, createContext("create"));

            addTeammateRequest atmr1 = new addTeammateRequest("testTeammate1", "testProjectDelete");
            addTeammateResponse respT1 = new addTeammateHandler().handleRequest(atmr1, createContext("create"));
            addTeammateRequest atmr2 = new addTeammateRequest("testTeammate2", "testProjectDelete");
            addTeammateResponse respT2 = new addTeammateHandler().handleRequest(atmr2, createContext("create"));

            deleteProjectRequest dpr = new deleteProjectRequest("testProjectDelete");
            deleteProjectResponse resp = new deleteProjectHandler().handleRequest(dpr, createContext("create"));
            Assert.assertEquals(200, resp.statusCode);
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Error:" + e.getMessage());
        }
    }
}
