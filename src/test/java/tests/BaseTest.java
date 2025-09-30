package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class BaseTest {

    //private static final String DEVICE_HOST = System.getProperty("deviceHost", "emulator");
    private static final String DEVICE_HOST = System.getProperty("deviceHost", "browserstack");

    @BeforeAll
    static void setup() {

        if ("browserstack".equals(DEVICE_HOST)) {
            System.out.println("Configuring for BROWSERSTACK");
            Configuration.browser = drivers.BrowserstackDriver.class.getName();
        } else {
            System.out.println("Configuring for EMULATOR");
            Configuration.browser = drivers.EmulationDriver.class.getName();
        }

        Configuration.timeout = 30000;
        Configuration.browserSize = null;
    }

    @BeforeEach
    void openApp() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        open();
    }

    @AfterEach
    void tearDown() {

        if (DEVICE_HOST.equals("emulator")) {
            Attach.screenshotAs("Last screenshot");
        } else if (DEVICE_HOST.equals("browserstack")) {
            String sessionId = Selenide.sessionId().toString();
            Attach.addVideo(sessionId);
        } else {
            throw new RuntimeException("Unknown deviceHost: " + DEVICE_HOST);
        }
        Attach.pageSource();
        closeWebDriver();
    }
}