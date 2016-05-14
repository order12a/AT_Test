import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import config.EnvironmentConfig;
import org.openqa.selenium.WebDriverException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.PageManager;
import ru.qatools.properties.PropertyLoader;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.refresh;
import static com.codeborne.selenide.WebDriverRunner.url;

public class TestBase {
    protected static String baseUrl;
    protected PageManager pageManager;

    @BeforeClass(alwaysRun = true)
    public void setUp(){
        EnvironmentConfig config = PropertyLoader.newInstance().populate(EnvironmentConfig.class);
        baseUrl = config.getBaseUrl();
        if (baseUrl.isEmpty() || baseUrl == null || baseUrl.contains("localhost")){
            baseUrl = "https://todomvc4tasj.herokuapp.com";
        }
        System.out.println("Base URL: " + baseUrl);
        Configuration.browser = "chrome";
        Configuration.browserSize = "1600x1200";
        Configuration.reopenBrowserOnFail = true;
        open(baseUrl);
        pageManager = new PageManager();
    }

    @BeforeMethod(alwaysRun = true)
    public void init(){
        if(WebDriverRunner.getWebDriver() != null && WebDriverRunner.getWebDriver().getWindowHandles().size() > 0){
            try {
                WebDriverRunner.clearBrowserCache();
            }catch (WebDriverException e){
                url();
                e.printStackTrace();
            }
        }
        refresh();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown(){
        if (WebDriverRunner.hasWebDriverStarted()){
            WebDriverRunner.closeWebDriver();
        }
    }

    public void sleep(int seconds){
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
