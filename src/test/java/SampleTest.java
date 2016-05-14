import org.testng.annotations.Test;

public class SampleTest extends TestBaseData {

    /**
     * According to test scenario no assertions were added to this test, only actions
     */

    @Test(dataProvider = "TEST_DATA")
    public void testTodosMVC(String[] tasks, String taskToDelete, String taskToComplete){
        pageManager.basePage.ensurePageLoaded();
        pageManager.todosPage.createMultipleTasks(tasks);
        pageManager.todosPage.deleteTargetTask(taskToDelete);
        pageManager.todosPage.markTargetTaskCompleted(taskToComplete);
        pageManager.todosPage.clearCompleted();
        pageManager.todosPage.markAllTasksCompleted();
        pageManager.todosPage.clearCompleted();
    }
}
