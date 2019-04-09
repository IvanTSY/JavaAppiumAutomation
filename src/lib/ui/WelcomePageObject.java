package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class WelcomePageObject  extends MainPageObject{

    private static final String
    STEP_LEARN_MORE_LINK = "id:Learn more about Wikipedia",
    STEP_NEW_WAY_LINK = "id:New ways to explore",
    STEP_ADD_OR_EDIT_LINK = "id:Add or edit preferred languages",
    STEP_LEARN_MORE_ABOUT_DATA_LINK = "id:Learn more about data collected",
    NEXT_LINK = "id:Next",
    GET_STARTED_BUTTON = "id:Get started";

    public WelcomePageObject(AppiumDriver driver) {
        super(driver);
    }



    public void waitForLearnMoreLink(){
        this.waitForElementPresent(STEP_LEARN_MORE_LINK,"Cannot find link 'more about Wikipedia' Wikipedia",10);
    }

    public void clickNextButton(){
        this.waitForElementAndClick(NEXT_LINK,"Cannot find and click 'Next button' Wikipedia",10);
    }

    public void clickGetStartedButton(){
        this.waitForElementAndClick(GET_STARTED_BUTTON,"Cannot find and click 'Get started' Wikipedia",10);
    }


    public void waitForNewWayToExploreText(){
        this.waitForElementPresent(STEP_NEW_WAY_LINK,"Cannot find link 'New ways to explore' Wikipedia",10);
    }

    public void waitForAddOrEditPreferredLangText(){
        this.waitForElementPresent(STEP_ADD_OR_EDIT_LINK,"Cannot find link 'Add or edit preferred languages' Wikipedia",10);
    }

    public void waitForLearnMoreAboutDataCollectedText(){
        this.waitForElementPresent(STEP_LEARN_MORE_ABOUT_DATA_LINK,"Cannot find link 'Learn more about data collected' Wikipedia",10);
    }


}
