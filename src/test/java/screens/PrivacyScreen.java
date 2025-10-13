package screens;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.id;

public class PrivacyScreen {
    
    private final SelenideElement mainTitle = $(id("org.wikipedia.alpha:id/primaryTextView"));
    private final SelenideElement beginButton = $(id("org.wikipedia.alpha:id/fragment_onboarding_done_button"));

    public PrivacyScreen verifyPrivacyTitle() {
        mainTitle.shouldHave(text("Data & Privacy"));
        return this;
    }

    public PrivacyScreen verifyStartButtonPresent() {
        beginButton.shouldHave(text("Get started"));
        return this;
    }
}