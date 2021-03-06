package lib;

import io.appium.java_client.AppiumDriver;
import junit.framework.TestCase;
import lib.ui.WelcomePageObject;
import org.openqa.selenium.ScreenOrientation;

import java.time.Duration;

public class CoreTestCase extends TestCase {




    protected AppiumDriver driver;

    @Override

    protected void setUp() throws Exception{

        super.setUp();
        driver = Platform.getInstance().getDriver();
        this.skipWelcomePageForIOSApp();


    }

    @Override

    protected void tearDown() throws Exception{
        driver.quit();
        super.tearDown();
    }
    protected void rotateScreenPortrait(){
        driver.rotate(ScreenOrientation.PORTRAIT);
    }
    protected void rotateScreenLandscape(){
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }
    protected void appBackground(int seconds){ driver.runAppInBackground(Duration.ofMillis(seconds));

    }

    private void skipWelcomePageForIOSApp(){
        if(Platform.getInstance().isIos()){
            WelcomePageObject WelcomePageObject = new WelcomePageObject(driver);
            WelcomePageObject.clickSkip();
        }
    }

}


