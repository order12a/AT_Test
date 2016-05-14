import org.testng.annotations.DataProvider;

public class TestBaseData extends TestBase {
    @DataProvider(name = "TEST_DATA")
    public Object[][] createData() {
        String[] tasks = new String[]{"task1", "task2", "task3", "task4"};
        return new Object[][] {
                { tasks, "task2", "task4"}
        };
    }
}
