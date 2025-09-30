package helpers;

import config.MainConfig;
import org.aeonbits.owner.ConfigFactory;

import static io.restassured.RestAssured.given;

public class Browserstack {
    private static final MainConfig mainConfig = ConfigFactory.create(MainConfig.class, System.getProperties());

    public static String videoUrl(String sessionId) {
        String url = String.format("https://api.browserstack.com/app-automate/sessions/%s.json", sessionId);

        return given()
                .auth().basic(mainConfig.userName(), mainConfig.accessKey())
                .get(url)
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().path("automation_session.video_url");
    }
}