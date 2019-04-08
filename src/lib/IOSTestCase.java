package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;

public class IOSTestCase extends TestCase {

    protected AppiumDriver driver;

    private static String AppiumURL = "http://127.0.0.1:4723/wd/hub" ;

    @Override

    protected void setUp() throws Exception{

        super.setUp();
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName","iOS");
        capabilities.setCapability("deviceName","iPhone SE");
        capabilities.setCapability("platformVersion","11.4");
        capabilities.setCapability("orientation","PORTRAIT");
        capabilities.setCapability("app","/Users/tester/Documents/GitHub/JavaAppiumAutomation/apks/Wikipedia.app"); //Дом
        //capabilities.setCapability("app","D:\\GitHub\\JavaAppiumAutomation\\apks\\org.wikipedia.apk");  //Работа

        driver = new IOSDriver(new URL(AppiumURL),capabilities);

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

    protected void appBackground(int seconds){

        driver.runAppInBackground(Duration.ofMillis(seconds));

    }




}
