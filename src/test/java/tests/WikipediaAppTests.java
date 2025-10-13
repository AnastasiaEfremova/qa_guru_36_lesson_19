package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import screens.WelcomeScreen;
import screens.DiscoveryScreen;
import screens.BookmarksScreen;
import screens.PrivacyScreen;
import screens.LanguageSettingsScreen;

import static io.qameta.allure.Allure.step;

public class WikipediaAppTests extends TestBase {

    @Test
    @DisplayName("Complete onboarding process validation")
    void completeOnboardingValidation() {
        step("Verify welcome screen", () -> {
            DiscoveryScreen discoveryScreen = new WelcomeScreen()
                    .verifyWelcomeTitle()
                    .confirmSkipOptionVisible()
                    .proceedToNextStep();

            step("Verify discovery features screen", () -> {
                BookmarksScreen bookmarksScreen = discoveryScreen
                        .verifyDiscoveryTitle()
                        .confirmSkipOptionVisible()
                        .proceedToNextStep();

                step("Verify bookmarks screen", () -> {
                    PrivacyScreen privacyScreen = bookmarksScreen
                            .verifyBookmarksTitle()
                            .confirmSkipOptionVisible()
                            .proceedToNextStep();

                    step("Verify privacy screen", () -> {
                        privacyScreen
                                .verifyPrivacyTitle()
                                .verifyStartButtonPresent();
                    });
                });
            });
        });
    }

    @Test
    @DisplayName("Language settings validation")
    void languageSettingsValidation() {
        step("Check initial language settings", () -> {
            WelcomeScreen welcomeScreen = new WelcomeScreen();
            LanguageSettingsScreen languageSettings = new LanguageSettingsScreen();

            welcomeScreen.verifyWelcomeTitle();
            languageSettings
                    .validateLanguagesCount(1)
                    .searchForLanguage("English");
            welcomeScreen.checkLanguageSettingsButton();
        });
    }
}