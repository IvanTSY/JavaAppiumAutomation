package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;

public class IosMyListsPageObject extends MyListsPageObject
{
    static {
        WEBFORM = "xpath;//XCUIElementTypeApplication[@name='{WEB}]";
        ARTICLE_BY_TITLE_TPL = "xpath://XCUIElementTypeLink[contains(@name='{TITLE}')]";

    }

    public IosMyListsPageObject(AppiumDriver driver)
    {
        super(driver);
    }
}