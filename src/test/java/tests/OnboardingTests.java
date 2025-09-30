package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import screens.OnboardingScreen;


@DisplayName("Wikipedia Onboarding Tests")
public class OnboardingTests extends BaseTest {

    private final OnboardingScreen onboarding = new OnboardingScreen();

    @Test
    @DisplayName("Complete onboarding flow with exact text verification")
    void completeOnboardingExactText() {

        onboarding.waitForScreenToLoad()
                .verifyScreenContent(
                        "The Free Encyclopedia\n…in over 300 languages",
                        "We’ve found the following on your device:"
                )
                .verifyContinueButtonVisible()
                .clickContinue();

        onboarding.waitForScreenToLoad()
                .verifyScreenContent(
                        "New ways to explore",
                        "Dive down the Wikipedia rabbit hole with a constantly updating Explore feed. Customize the feed to your interests – whether it’s learning about historical events On this day, or rolling the dice with Random."
                )
                .verifyContinueButtonVisible()
                .clickContinue();

        onboarding.waitForScreenToLoad()
                .verifyScreenContent(
                        "Reading lists with sync",
                        "You can make reading lists from articles you want to read later, even when you’re offline. Login to your Wikipedia account to sync your reading lists. Join Wikipedia"
                )
                .verifyContinueButtonVisible()
                .clickContinue();

        onboarding.waitForScreenToLoad()
                .verifyScreenContent(
                        "Data & Privacy",
                        "We believe that you should not have to provide personal information to participate in the free knowledge movement. Usage data collected for this app is anonymous. Learn more about our privacy policy and terms of use."
                )
                .verifyDoneButtonVisible()
                .clickDone();
    }

    @Test
    @DisplayName("Complete onboarding flow with partial text verification")
    void completeOnboardingPartialText() {

        onboarding.waitForScreenToLoad()
                .verifyScreenContentPartial(
                        "The Free Encyclopedia",
                        "found the following on your device"
                )
                .clickContinue();

        onboarding.waitForScreenToLoad()
                .verifyScreenContentPartial(
                        "New ways to explore",
                        "Wikipedia rabbit hole"
                )
                .clickContinue();

        onboarding.waitForScreenToLoad()
                .verifyScreenContentPartial(
                        "Reading lists",
                        "read later"
                )
                .clickContinue();

        onboarding.waitForScreenToLoad()
                .verifyScreenContentPartial(
                        "Data & Privacy",
                        "personal information"
                )
                .clickDone();
    }

    @Test
    @DisplayName("Skip onboarding flow")
    void skipOnboarding() {
        onboarding.waitForScreenToLoad()
                .clickSkip();
    }

    @Test
    @DisplayName("Simple onboarding flow")
    void simpleOnboarding() {
        onboarding.waitForScreenToLoad().clickContinue();
        onboarding.waitForScreenToLoad().clickContinue();
        onboarding.waitForScreenToLoad().clickContinue();
        onboarding.waitForScreenToLoad().clickDone();
    }
}