package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "classpath:main.properties"
})
public interface MainConfig extends Config {

    @Key("userName")
    String userName();

    @Key("accessKey")
    String accessKey();

    @Key("remoteUrl")
    String remoteUrl();

    @Key("app")
    String app();

    @Key("app.browserstack")
    String appBrowserstack();

    @Key("device")
    String device();

    @Key("osVersion")
    String osVersion();


}