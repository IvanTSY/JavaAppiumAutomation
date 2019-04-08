package tests.iOS;

import lib.CoreTestCase;
import lib.ui.WelcomePageObject;
import org.junit.Test;

public class GetStartedTest extends CoreTestCase {

    @Test

    public void testPassThroughWelcome(){
        WelcomePageObject WelcomPage = new WelcomePageObject(driver);

        WelcomPage.waitForLearnMoreLink();
        WelcomPage.clickNextButton();

        WelcomPage.waitForNewWayToExploreText();
        WelcomPage.clickNextButton();

        WelcomPage.waitForAddOrEditPreferredLangText();
        WelcomPage.clickNextButton();

        WelcomPage.waitForLearnMoreAboutDataCollectedText();
        WelcomPage.clickGetStartedButton();


    }
}
