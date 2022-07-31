package utils;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import utils.pages.ResultPage;

import java.time.Duration;
import static com.codeborne.selenide.Selenide.*;

public class TestInitializer {

    @BeforeAll
    public static void initBrowser(){
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "none";
        Configuration.headless = false;
    }

    @BeforeEach
    public void openSite(){
        open("https://www.oojo.com/result/DFW-NYC/2022-08-15");
        ResultPage.insiderWindowExitBtn.should(Condition.visible, Duration.ofSeconds(30)).click();
    }

    @AfterEach
    public void exit(){
        closeWebDriver();
        clearBrowserCookies();
    }
}