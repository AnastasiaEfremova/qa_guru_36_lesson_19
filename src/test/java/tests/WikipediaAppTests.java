package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import screens.OnboardingScreen;

import static io.qameta.allure.Allure.step;

public class WikipediaAppTests extends TestBase {

    OnboardingScreen welcomeFlow = new OnboardingScreen();

    @Test
    @DisplayName("Complete onboarding process validation")
    void completeOnboardingValidation() {
        step("Verify welcome screen", () -> {
            welcomeFlow.verifyWelcomeTitle()
                    .validateLanguagesCount(1)
                    .searchForLanguage("English")
                    .checkLanguageSettingsButton()
                    .confirmSkipOptionVisible()
                    .proceedToNextStep();
        });

        step("Verify discovery features screen", () -> {
            welcomeFlow.verifyDiscoveryTitle()
                    .confirmSkipOptionVisible()
                    .proceedToNextStep();
        });

        step("Verify bookmarks screen", () -> {
            welcomeFlow.verifyBookmarksTitle()
                    .confirmSkipOptionVisible()
                    .proceedToNextStep();
        });

        step("Verify privacy screen", () -> {
            welcomeFlow.verifyPrivacyTitle()
                    .verifyStartButtonPresent();
        });
    }

    @Test
    @DisplayName("Language settings validation")
    void languageSettingsValidation() {
        step("Check initial language settings", () -> {
            welcomeFlow.verifyWelcomeTitle()
                    .validateLanguagesCount(1)
                    .searchForLanguage("English")
                    .checkLanguageSettingsButton();
        });

        step("Verify language addition capability", () -> {
            welcomeFlow.checkLanguageSettingsButton();
        });
    }
}