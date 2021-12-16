package taskManager;

        import org.junit.Assert;
        import org.junit.Test;
        import taskManager.http.*;

public class listTasksTest extends LambdaTest{
    @Test
    public void testListTasks(){
        //deleteAllProjectsResponse respD = deleteAllProjectsHandler.deleteAllProjectsFromDB();

        try{
            deleteProjectRequest dprD = new deleteProjectRequest("listTasksProjectTest");
            deleteProjectResponse respD = new deleteProjectHandler().handleRequest(dprD, createContext("create"));

            createProjectRequest cprC = new createProjectRequest("listTasksProjectTest");
            createProjectResponse respC = new createProjectHandler().handleRequest(cprC, createContext("create"));

            addTaskRequest atr1 = new addTaskRequest("assignTestTask1","listTasksProjectTest");
            addTaskResponse atrsp1 = new addTaskHandler().handleRequest(atr1, createContext("create"));
            addTaskRequest atr2 = new addTaskRequest("assignTestTask2","listTasksProjectTest");
            addTaskResponse atrsp2 = new addTaskHandler().handleRequest(atr2, createContext("create"));
            addTaskRequest atr3 = new addTaskRequest("assignTestTask3","listTasksProjectTest");
            addTaskResponse atrsp3 = new addTaskHandler().handleRequest(atr3, createContext("create"));

            addTeammateRequest atmr1 = new addTeammateRequest("testListTeammate1", "listTasksProjectTest");
            addTeammateResponse resptm1 = new addTeammateHandler().handleRequest(atmr1, createContext("create"));

            assignRequest ar1 = new assignRequest("assignTestTeammate", "listTasksProjectTest", atrsp1.getTaskID());
            assignResponse assignresp1 = new assignmentHandler().handleRequest(ar1, createContext("create"));

            listTasksRequest ltrq = new listTasksRequest("listTasksProjectTest");
            listTasksResponse ltrps = new listTasksHandler().handleRequest(ltrq, createContext("create"));
            Assert.assertEquals(200, ltrps.statusCode);

            Assert.assertEquals("listTasksProjectTest", ltrq.getName());
            ltrq.setName("listTasksProjectTest-set");
            Assert.assertEquals("listTasksProjectTest-set", ltrq.getName());

            listTasksResponse arErr = new listTasksResponse(400, "err");
            Assert.assertEquals("err", arErr.error);
            Assert.assertEquals(400, arErr.statusCode);

            listTasksRequest ltrqConstructorTest = new listTasksRequest();
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Error:" + e.getMessage());
        }

    }
}
