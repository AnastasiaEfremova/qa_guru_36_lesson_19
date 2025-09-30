package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import screens.OnboardingScreen;

import static io.qameta.allure.Allure.step;

public class WikipediaAppTests extends TestBase {
    OnboardingScreen welcomeFlow = new OnboardingScreen();

    @Test
    @DisplayName("Полная проверка процесса онбординга")
    void completeOnboardingValidation() {
        step("Проверка экрана приветствия", () -> {
            welcomeFlow.verifyWelcomeTitle()
                    .validateLanguagesCount(1)
                    .searchForLanguage("English")
                    .checkLanguageSettingsButton()
                    .confirmSkipOptionVisible()
                    .proceedToNextStep();
        });

        step("Проверка экрана возможностей", () -> {
            welcomeFlow.verifyDiscoveryTitle()
                    .confirmSkipOptionVisible()
                    .proceedToNextStep();
        });

        step("Проверка экрана закладок", () -> {
            welcomeFlow.verifyBookmarksTitle()
                    .confirmSkipOptionVisible()
                    .proceedToNextStep();
        });

        step("Проверка экрана конфиденциальности", () -> {
            welcomeFlow.verifyPrivacyTitle()
                    .verifyStartButtonPresent();
        });
    }

    @Test
    @DisplayName("Валидация языковых настроек")
    void languageSettingsValidation() {
        step("Проверяем начальные языковые настройки", () -> {
            welcomeFlow.verifyWelcomeTitle()
                    .validateLanguagesCount(1)
                    .searchForLanguage("English")
                    .checkLanguageSettingsButton();
        });

        step("Проверяем возможность добавления языков", () -> {
            welcomeFlow.checkLanguageSettingsButton();
        });
    }
}