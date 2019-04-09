package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;

public class CoreTestCase extends TestCase {

    private static final String PLATFORM_IOS = "ios";
    private static final String PLATFORM_ANDROID = "android";

    protected Platform Platform;

    protected AppiumDriver driver;

    @Override

    protected void setUp() throws Exception{

        super.setUp();
        this.Platform = new Platform();
        driver = this.Platform.getDriver();

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

}


