package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.MainConfig;
import config.MobileConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

public class BrowserstackDriver implements WebDriverProvider {

    private final MainConfig mainConfig = ConfigFactory.create(MainConfig.class, System.getProperties());
    private final MobileConfig mobileConfig = ConfigFactory.create(MobileConfig.class, System.getProperties());

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        MutableCapabilities caps = new MutableCapabilities();

        caps.setCapability("browserstack.user", mainConfig.userName());
        caps.setCapability("browserstack.key", mainConfig.accessKey());
        caps.setCapability("app", mainConfig.app());
        caps.setCapability("device", mobileConfig.device());
        caps.setCapability("os_version", mobileConfig.osVersion());

        caps.setCapability("project", "Wikipedia Android Tests");
        caps.setCapability("build", "browserstack-build-1");
        caps.setCapability("name", "Onboarding Tests");

        try {
            return new RemoteWebDriver(new URL(mainConfig.remoteUrl()), caps);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}