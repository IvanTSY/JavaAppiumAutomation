package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;

public class IosMyListsPageObject extends MyListsPageObject
{
    static {
        WEB_FORM = "xpath://XCUIElementTypeApplication[@name='Wikipedia']";// продолжение /XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]
        ARTICLE_BY_TITLE_TPL = "xpath://XCUIElementTypeLink[contains(@name='{TITLE}')]";

    }

    public IosMyListsPageObject(AppiumDriver driver)
    {
        super(driver);
    }
}