package pages;

import com.codeborne.selenide.WebDriverRunner;

public class PageManager {

    public BasePage basePage;
    public TodosPage todosPage;

    public PageManager(){
        basePage = new BasePage(WebDriverRunner.getWebDriver());
        todosPage = new TodosPage(WebDriverRunner.getWebDriver());
    }
}
