package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import lib.Platform;
import lib.ui.SearchPageObject;
import lib.ui.android.AndroidSearchPageObject;
import lib.ui.ios.IosSearchPageObject;

public class SearchPageObjectFatory {

    public static SearchPageObject get(AppiumDriver driver){
        if(Platform.getInstance().isAndroid()){
            return new AndroidSearchPageObject(driver);
        }else {
            return new IosSearchPageObject(driver);
        }
    }
}
