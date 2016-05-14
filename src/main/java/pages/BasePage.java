package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.$;

public class BasePage {

    protected int WAIT_TIMEOUT = 15000;
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected SelenideElement logo = $("#header>h1");

    public BasePage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    public boolean ensurePageLoaded(){
        return logo.waitUntil(Condition.visible, WAIT_TIMEOUT).isDisplayed();
    }
}
