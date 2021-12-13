package taskManager;

import org.junit.Assert;
import org.junit.Test;
import taskManager.http.addTeammateRequest;
import taskManager.http.addTeammateResponse;

public class addTeammateTest extends LambdaTest{
    @Test
    public void testAddTeammate(){
        try{
            addTeammateRequest atr = new addTeammateRequest("bob igor", "newProj");
            addTeammateResponse resp = new addTeammateHandler().handleRequest(atr, createContext("create"));
            Assert.assertEquals(400, resp.httpCode);
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Error:" + e.getMessage());
        }
    }
}
