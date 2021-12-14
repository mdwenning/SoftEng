package taskManager;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;
import taskManager.db.projectsDAO;
import taskManager.http.*;
import taskManager.model.Task;

public class DeleteProjectTest extends LambdaTest{
    @Test
    public void testDeleteProject(){
        try {
//            createProjectRequest cpr = new createProjectRequest("testProjectDelete");
//            createProjectResponse respC = new createProjectHandler().handleRequest(cpr, createContext("create"));
//
//            addTaskRequest atr1 = new addTaskRequest("test task1","testProjectDelete");
//            addTaskResponse atrsp1 = new addTaskHandler().handleRequest(atr1, createContext("create"));
//            addTaskRequest atr2 = new addTaskRequest("test task2","testProjectDelete");
//            addTaskResponse atrsp2 = new addTaskHandler().handleRequest(atr2, createContext("create"));
//            addTaskRequest atr3 = new addTaskRequest("test task3","testProjectDelete");
//            addTaskResponse atrsp3 = new addTaskHandler().handleRequest(atr3, createContext("create"));
//
//            addTeammateRequest atmr1 = new addTeammateRequest("testTeammate1", "testProjectDelete");
//            addTeammateResponse respT1 = new addTeammateHandler().handleRequest(atmr1, createContext("create"));
//            addTeammateRequest atmr2 = new addTeammateRequest("testTeammate2", "testProjectDelete");
//            addTeammateResponse respT2 = new addTeammateHandler().handleRequest(atmr2, createContext("create"));

//            assignRequest ar1 = new assignRequest("testTeammate1", "testProjectDelete", "80906ffa-0018-4101-af64-359fd29134f8");
//            assignResponse assignresp1 = new assignmentHandler().handleRequest(ar1, createContext("create"));
//            assignRequest ar2 = new assignRequest("testTeammate2", "testProjectDelete", "bfd616ea-1532-45c0-880b-d3bdb1cbf395");
//            assignResponse assignresp2 = new assignmentHandler().handleRequest(ar2, createContext("create"));
//            assignRequest ar3 = new assignRequest("testTeammate1", "testProjectDelete", "e0956f5f-5b67-4df0-a59c-aa7708c8e182");
//            assignResponse assignresp3 = new assignmentHandler().handleRequest(ar3, createContext("create"));
//            assignRequest ar4 = new assignRequest("testTeammate2", "testProjectDelete", "e0956f5f-5b67-4df0-a59c-aa7708c8e182");
//            assignResponse assignresp4 = new assignmentHandler().handleRequest(ar4, createContext("create"));

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
