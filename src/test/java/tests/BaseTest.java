package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.*;

public class BaseTest {

    @BeforeAll
    static void setup() {
        String deviceHost = System.getProperty("deviceHost", "emulation");
        System.out.println("Running tests with deviceHost: " + deviceHost);

        if ("browserstack".equals(deviceHost)) {
            Configuration.browser = drivers.BrowserstackDriver.class.getName();
        } else {
            Configuration.browser = drivers.EmulationDriver.class.getName();
        }

        Configuration.timeout = 15000;
        Configuration.browserSize = null;
    }

    @BeforeEach
    void openApp() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        open();
    }

    @AfterEach
    void tearDown() {
        Selenide.closeWebDriver();
    }
}