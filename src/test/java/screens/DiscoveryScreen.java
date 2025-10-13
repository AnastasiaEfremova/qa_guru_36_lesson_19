package screens;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.id;

public class DiscoveryScreen {
    
    private final SelenideElement mainTitle = $(id("org.wikipedia.alpha:id/primaryTextView"));
    private final SelenideElement skipOption = $(id("org.wikipedia.alpha:id/fragment_onboarding_skip_button"));
    private final SelenideElement nextStepButton = $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button"));

    public DiscoveryScreen verifyDiscoveryTitle() {
        mainTitle.shouldHave(text("New ways to explore"));
        return this;
    }

    public DiscoveryScreen confirmSkipOptionVisible() {
        skipOption.shouldHave(text("Skip"));
        skipOption.shouldBe(visible);
        return this;
    }

    public BookmarksScreen proceedToNextStep() {
        nextStepButton.shouldHave(text("Continue"));
        nextStepButton.click();
        return new BookmarksScreen();
    }
}