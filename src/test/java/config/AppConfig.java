package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "file:src/test/resources/${deviceHost}.properties",
        "file:src/test/resources/local.properties"
})
public interface AppConfig extends Config {

    @Key("remote.url")
    String remoteUrl();

    @Key("app.package")
    String appPackage();

    @Key("app.activity")
    String appActivity();

    @Key("device.name")
    String deviceName();

    @Key("platform.version")
    String platformVersion();
}