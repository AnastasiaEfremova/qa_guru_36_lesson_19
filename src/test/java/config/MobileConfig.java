package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "classpath:android.properties"
})
public interface MobileConfig extends Config {

    @Key("device")
    String device();

    @Key("osVersion")
    String osVersion();
}