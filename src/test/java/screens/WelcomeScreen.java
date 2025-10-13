package screens;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.id;

public class WelcomeScreen {
    
    private final SelenideElement mainTitle = $(id("org.wikipedia.alpha:id/primaryTextView"));
    private final SelenideElement skipOption = $(id("org.wikipedia.alpha:id/fragment_onboarding_skip_button"));
    private final SelenideElement nextStepButton = $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button"));
    private final SelenideElement languageManagementBtn = $(id("org.wikipedia.alpha:id/addLanguageButton"));

    public WelcomeScreen verifyWelcomeTitle() {
        mainTitle.shouldHave(text("The Free Encyclopedia\n…in over 300 languages"));
        return this;
    }

    public WelcomeScreen confirmSkipOptionVisible() {
        skipOption.shouldHave(text("Skip"));
        skipOption.shouldBe(visible);
        return this;
    }

    public DiscoveryScreen proceedToNextStep() {
        nextStepButton.shouldHave(text("Continue"));
        nextStepButton.click();
        return new DiscoveryScreen();
    }

    public WelcomeScreen checkLanguageSettingsButton() {
        languageManagementBtn.shouldHave(text("Add or edit languages"));
        return this;
    }
}