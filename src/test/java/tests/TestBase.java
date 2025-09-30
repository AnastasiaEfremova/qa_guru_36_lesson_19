package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import drivers.BrowserstackDriver;
import drivers.EmulationDriver;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class TestBase {


    private static final String DEVICE_HOST = System.getProperty("deviceHost", "emulator");

    @BeforeAll
    static void beforeAll() {
        System.setProperty("platform", System.getProperty("platform", "android"));

        if (DEVICE_HOST.equals("browserstack")) {
            Configuration.browser = BrowserstackDriver.class.getName();
        } else if (DEVICE_HOST.equals("emulator")) {
            Configuration.browser = EmulationDriver.class.getName();
        } else {
            throw new RuntimeException("No Driver was found for " + DEVICE_HOST);
        }

        Configuration.browserSize = null;
        Configuration.timeout = 30000;
    }

    @BeforeEach
    void beforeEach() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        open();
    }

    @AfterEach
    void addAttachments() {
        if (DEVICE_HOST.equals("emulator")) {
            Attach.screenshotAs("Last screenshot");
        } else if (DEVICE_HOST.equals("browserstack")) {
            String sessionId = Selenide.sessionId().toString();
            Attach.addVideo(sessionId);
        } else {
            throw new RuntimeException("Unknown deviceHost");
        }
        Attach.pageSource();
        closeWebDriver();
    }
}