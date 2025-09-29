package screens;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class OnboardingScreen {

    private final SelenideElement continueButton = $(By.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button"));
    private final SelenideElement doneButton = $(By.id("org.wikipedia.alpha:id/fragment_onboarding_done_button"));
    private final SelenideElement primaryText = $(By.id("org.wikipedia.alpha:id/primaryTextView"));
    private final SelenideElement secondaryText = $(By.id("org.wikipedia.alpha:id/secondaryTextView"));
    private final SelenideElement skipButton = $(By.id("org.wikipedia.alpha:id/fragment_onboarding_skip_button"));

    @Step("Tap 'Continue' button")
    public OnboardingScreen clickContinue() {
        continueButton.shouldBe(visible, enabled).click();
        return this;
    }

    @Step("Tap 'Get started' button")
    public void clickDone() {
        doneButton.shouldBe(visible, enabled).click();
    }

    @Step("Tap 'Skip' button")
    public void clickSkip() {
        skipButton.shouldBe(visible, enabled).click();
    }

    @Step("Verify screen content")
    public OnboardingScreen verifyScreenContent(String expectedPrimary, String expectedSecondary) {
        // Using correct texts with "smart" quotes
        primaryText.shouldBe(visible).shouldHave(text(expectedPrimary));
        secondaryText.shouldBe(visible).shouldHave(text(expectedSecondary));
        return this;
    }

    @Step("Verify screen content (partial match)")
    public OnboardingScreen verifyScreenContentPartial(String expectedPrimary, String expectedSecondary) {
        // Using partial text matching
        primaryText.shouldBe(visible).shouldHave(partialText(expectedPrimary));
        secondaryText.shouldBe(visible).shouldHave(partialText(expectedSecondary));
        return this;
    }

    @Step("Wait for onboarding screen to load")
    public OnboardingScreen waitForScreenToLoad() {
        primaryText.shouldBe(visible);
        return this;
    }

    @Step("Verify Continue button is visible")
    public OnboardingScreen verifyContinueButtonVisible() {
        continueButton.shouldBe(visible);
        return this;
    }

    @Step("Verify Done button is visible")
    public OnboardingScreen verifyDoneButtonVisible() {
        doneButton.shouldBe(visible);
        return this;
    }
}

