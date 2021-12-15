package taskManager;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;
import taskManager.http.*;

public class archiveProjectTest extends LambdaTest{
    @Test
    public void testArchiveProject(){
        try {
            deleteProjectRequest dpr = new deleteProjectRequest("testProjectArchive");
            deleteProjectResponse respD = new deleteProjectHandler().handleRequest(dpr, createContext("create"));

            createProjectRequest cpr = new createProjectRequest("testProjectArchive");
            createProjectResponse resp = new createProjectHandler().handleRequest(cpr, createContext("create"));

            markArchivedRequest arq = new markArchivedRequest("testProjectArchive");
            markArchivedResponse aresp = new markArchivedHandler().handleRequest(arq, createContext("create"));
            Assert.assertEquals(200, aresp.statusCode);

            Assert.assertEquals(arq.getName(), "testProjectArchive");
            arq.setName("testProjectArchive-set");
            Assert.assertEquals(arq.getName(), "testProjectArchive-set");

            markArchivedResponse respErr = new markArchivedResponse(400, "err");
            Assert.assertEquals(400, respErr.statusCode);
            Assert.assertEquals("err", respErr.error);
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Error:" + e.getMessage());
        }
    }
}
