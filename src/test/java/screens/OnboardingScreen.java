package screens;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.appium.java_client.AppiumBy.id;

public class OnboardingScreen {

    private final SelenideElement mainTitle = $(id("org.wikipedia.alpha:id/primaryTextView"));
    private final SelenideElement skipOption = $(id("org.wikipedia.alpha:id/fragment_onboarding_skip_button"));
    private final SelenideElement nextStepButton = $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button"));
    private final SelenideElement beginButton = $(id("org.wikipedia.alpha:id/fragment_onboarding_done_button"));
    private final SelenideElement languageManagementBtn = $(id("org.wikipedia.alpha:id/addLanguageButton"));

    private final ElementsCollection availableLanguages = $$(id("org.wikipedia.alpha:id/languagesList"));
    private final ElementsCollection languageTitles = $$(id("org.wikipedia.alpha:id/option_label"));


    public OnboardingScreen verifyWelcomeTitle() {
        mainTitle.shouldHave(text("The Free Encyclopedia\n…in over 300 languages"));
        return this;
    }

    public OnboardingScreen verifyDiscoveryTitle() {
        mainTitle.shouldHave(text("New ways to explore"));
        return this;
    }

    public OnboardingScreen verifyBookmarksTitle() {
        mainTitle.shouldHave(text("Reading lists with sync"));
        return this;
    }

    public OnboardingScreen verifyPrivacyTitle() {
        mainTitle.shouldHave(text("Data & Privacy"));
        return this;
    }

    public OnboardingScreen confirmSkipOptionVisible() {
        skipOption.shouldHave(text("Skip"));
        skipOption.shouldBe(visible);
        return this;
    }

    public OnboardingScreen proceedToNextStep() {
        nextStepButton.shouldHave(text("Continue"));
        nextStepButton.click();
        return this;
    }

    public OnboardingScreen verifyStartButtonPresent() {
        beginButton.shouldHave(text("Get started"));
        return this;
    }

    public OnboardingScreen validateLanguagesCount(int expectedCount) {
        availableLanguages.shouldHave(size(expectedCount));
        return this;
    }

    public OnboardingScreen searchForLanguage(String languageName) {
        languageTitles.findBy(text(languageName)).shouldBe(visible);
        return this;
    }

    public OnboardingScreen checkLanguageSettingsButton() {
        languageManagementBtn.shouldHave(text("Add or edit languages"));
        return this;
    }

    public OnboardingScreen verifyAllElementsInteractive() {
        mainTitle.shouldBe(visible);
        nextStepButton.shouldBe(enabled);
        return this;
    }
}