package taskManager;

import org.junit.Assert;
import org.junit.Test;
import taskManager.model.Admin;
import taskManager.model.Task;

import java.util.ArrayList;
import java.util.List;

public class extraModelsTest extends LambdaTest {
    @Test
    public void testAExtraModels() {
        try {
            Admin adminTest1 = new Admin("userAdmin1");
            Assert.assertEquals("userAdmin1", adminTest1.name);

            Admin adminTest2 = new Admin("userAdmin2", "adminIDtest");
            Assert.assertEquals("userAdmin2", adminTest2.name);
            Assert.assertEquals("adminIDtest", adminTest2.idAdmin);

            List<String> assigneesTest = new ArrayList<>();
            assigneesTest.add("teammate1");
            assigneesTest.add("teammate2");

            Task taskTest = new Task("taskID", "projID", "parentID", "taskName", assigneesTest, 1, 1);
            Assert.assertEquals("taskID", taskTest.idTask);
            Assert.assertEquals("projID", taskTest.idProject);
            Assert.assertEquals("parentID", taskTest.idParent);
            Assert.assertEquals("taskName", taskTest.name);
            Assert.assertEquals(assigneesTest, taskTest.assignees);
            Assert.assertEquals(1, taskTest.isComplete);
            Assert.assertEquals(1, taskTest.sequence);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error:" + e.getMessage());
        }
    }
}
