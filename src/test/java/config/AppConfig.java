package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:main.properties"
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