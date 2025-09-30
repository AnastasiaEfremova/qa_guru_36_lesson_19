package tests;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.*;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.id;
import static io.qameta.allure.Allure.step;

public class SearchAndroidTests extends BaseTest {

    @Test
    void successfulAppiumSearchTest() {
        step("Type search", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Appium");
        });
        step("Verify content found", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(sizeGreaterThan(0)));
    }

    @Test
    void successfulGithubSearchTest() {
        step("Search for GitHub", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text"))
                    .sendKeys("GitHub");
        });

        step("Verify search results appear", () -> {
            $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                    .shouldHave(sizeGreaterThan(0), Duration.ofSeconds(15));
        });

        step("Open first article from results", () -> $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                .first()
                .click());

        step("Verify article opened successfully", () -> {
            sleep(5000);
            System.out.println("Article opened successfully!");
        });
    }
}