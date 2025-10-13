package screens;

import com.codeborne.selenide.ElementsCollection;
import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;
import static io.appium.java_client.AppiumBy.id;

public class LanguageSettingsScreen {
    
    private final ElementsCollection availableLanguages = $$(id("org.wikipedia.alpha:id/languagesList"));
    private final ElementsCollection languageTitles = $$(id("org.wikipedia.alpha:id/option_label"));

    public LanguageSettingsScreen validateLanguagesCount(int expectedCount) {
        availableLanguages.shouldHave(size(expectedCount));
        return this;
    }

    public LanguageSettingsScreen searchForLanguage(String languageName) {
        languageTitles.findBy(text(languageName)).shouldBe(visible);
        return this;
    }
}