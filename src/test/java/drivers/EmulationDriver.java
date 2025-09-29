package drivers;

import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.aeonbits.owner.ConfigFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import config.AppConfig;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class EmulationDriver implements WebDriverProvider {

    private static final AppConfig config = ConfigFactory.create(AppConfig.class, System.getProperties());

    @Override
    public WebDriver createDriver(Capabilities capabilities) {
        System.out.println("Creating emulation driver");

        UiAutomator2Options options = new UiAutomator2Options();

        String appPath = getAppPath();

        options.setPlatformName("Android")
                .setDeviceName(config.deviceName())
                .setPlatformVersion(config.platformVersion())
                .setApp(appPath)
                .setAppPackage(config.appPackage())
                .setAppActivity(config.appActivity())
                .setAutomationName("UiAutomator2")
                .setNoReset(false);

        try {
            return new AndroidDriver(new URL(config.remoteUrl()), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid remote URL: " + config.remoteUrl(), e);
        }
    }

    private String getAppPath() {
        String appVersion = "app-alpha-universal-release.apk";
        String appUrl = "https://github.com/wikimedia/apps-android-wikipedia/releases/download/latest/" + appVersion;
        String appPath = "src/test/resources/apps/" + appVersion;

        File app = new File(appPath);
        if (!app.exists()) {
            try {
                app.getParentFile().mkdirs();
                System.out.println("Downloading Wikipedia APK...");
                try (InputStream in = new URL(appUrl).openStream()) {
                    FileUtils.copyInputStreamToFile(in, app);
                }
                System.out.println("APK downloaded to: " + app.getAbsolutePath());
            } catch (IOException e) {
                throw new RuntimeException("Failed to download application", e);
            }
        }
        return app.getAbsolutePath();
    }
}