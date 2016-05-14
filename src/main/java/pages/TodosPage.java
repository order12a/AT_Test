package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebDriver;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class TodosPage extends BasePage {
    public TodosPage(WebDriver driver) {
        super(driver);
    }

    protected SelenideElement todoInput = $("#new-todo");
    protected List<SelenideElement> taskNamesField = $$("ul>li>.view>label");
    protected List<SelenideElement> completeTaskCheckboxex = $$(".toggle");
    private List<SelenideElement> taskHolders = $$(".view");
    protected SelenideElement clearCompletedButton = $("#clear-completed");

    public void createTask(String taskName){
        todoInput.clear();
        todoInput.sendKeys(taskName);
        todoInput.waitUntil(Condition.enabled, WAIT_TIMEOUT);
        $("header>input").pressEnter();
        isTaskCreated(taskName);
    }

    public void createMultipleTasks(String[] tasks){
        for (String task:tasks){
            createTask(task);
        }
    }

    public boolean isTaskCreated(String taskName){
        for (SelenideElement element:taskNamesField){
            if (element.getText().equalsIgnoreCase(taskName)){
                return true;
            }
        }
        return false;
    }

    public void deleteTargetTask(String taskName){
        for (SelenideElement element: taskHolders){
            if(element.find("label").getText().equals(taskName)){
                element.hover().find(".destroy").click();
            }
        }
    }

    public void markTargetTaskCompleted(int taskNumber){
        $(".toggle", taskNumber).setSelected(true);
    }

    public void markTargetTaskCompleted(String taskName){
        for (SelenideElement element: taskHolders){
            if(element.find("label").getText().equals(taskName)){
                element.find(".toggle").click();
            }
        }
    }

    public void markAllTasksCompleted(){
        for (SelenideElement element: completeTaskCheckboxex){
            if(!element.isSelected()){
                element.setSelected(true);
            }
        }
    }

    public void clearCompleted(){
        clearCompletedButton.click();
    }
}
