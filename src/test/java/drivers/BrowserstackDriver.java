package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.BrowserstackConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

public class BrowserstackDriver implements WebDriverProvider {

    private final BrowserstackConfig browserstackConfig = ConfigFactory.create(BrowserstackConfig.class, System.getProperties());

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        MutableCapabilities caps = new MutableCapabilities();

        caps.setCapability("appium:deviceName", browserstackConfig.device());
        caps.setCapability("appium:platformVersion", browserstackConfig.osVersion());
        caps.setCapability("platformName", "Android");
        caps.setCapability("appium:app", browserstackConfig.app());
        caps.setCapability("appium:automationName", "UiAutomator2");

        MutableCapabilities bstackOptions = new MutableCapabilities();
        bstackOptions.setCapability("userName", browserstackConfig.userName());
        bstackOptions.setCapability("accessKey", browserstackConfig.accessKey());
        bstackOptions.setCapability("projectName", "Wikipedia Android Tests");
        bstackOptions.setCapability("buildName", "browserstack-build-1");
        bstackOptions.setCapability("sessionName", "Onboarding Tests");
        bstackOptions.setCapability("appiumVersion", "2.19.0");

        caps.setCapability("bstack:options", bstackOptions);

        try {
            return new RemoteWebDriver(new URL(browserstackConfig.remoteUrl()), caps);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Malformed remote URL", e);
        }
    }
}
