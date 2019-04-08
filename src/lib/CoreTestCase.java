package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;

public class CoreTestCase extends TestCase {

    private static final String PLATFORM_IOS = "ios";
    private static final String PLATFORM_ANDROID = "android";

    private static  final String DRIVER_IOS = "iOSDriver";
    private static  final String DRIVER_ANDROID = "androidDriver";

    protected AppiumDriver driver;

    private static String AppiumURL = "http://127.0.0.1:4723/wd/hub" ;

    @Override

    protected void setUp() throws Exception{

        super.setUp();
        DesiredCapabilities capabilities = this.getCapabilitiesByPlatformEnv();
        driver = this.getDriverByEnv(capabilities);



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

    private DesiredCapabilities getCapabilitiesByPlatformEnv() throws Exception {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        String platform = System.getenv("PLATFORM");

        if (platform.equals(PLATFORM_ANDROID)) {
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("deviceName", "AndroidTestDevice");
            capabilities.setCapability("platformVersion", "8.0");
            //capabilities.setCapability("automationName","Appium");
            capabilities.setCapability("appPackage", "org.wikipedia");
            capabilities.setCapability("appActivity", ".main.MainActivity");
            capabilities.setCapability("orientation", "PORTRAIT");

            capabilities.setCapability("app", "C:\\Users\\FBI\\IdeaProjects\\ApiumTraining\\JavaAppiumAutomation\\apks\\org.wikipedia.apk"); //Дом
            //capabilities.setCapability("app","D:\\GitHub\\JavaAppiumAutomation\\apks\\org.wikipedia.apk");  //Работа


        } else if (platform.equals(PLATFORM_IOS)) {
            capabilities.setCapability("platformName", "iOS");
            capabilities.setCapability("deviceName", "iPhone SE");
            capabilities.setCapability("platformVersion", "11.4");
            capabilities.setCapability("orientation", "PORTRAIT");

            capabilities.setCapability("app", "/Users/tester/Documents/GitHub/JavaAppiumAutomation/apks/Wikipedia.app"); //Дом


        } else {
            throw new Exception("Cannot get run platform from env variables. Platform value is" + platform);
        }
        return capabilities;
    }

    private AppiumDriver getDriverByEnv(DesiredCapabilities capabilities)throws  Exception{

        String driver = System.getenv("DRIVER");

        if(driver.equals(DRIVER_ANDROID)){

            this.driver = new AndroidDriver(new URL(AppiumURL), capabilities);

        }  else if(driver.equals(DRIVER_IOS)){

            this.driver = new IOSDriver(new URL(AppiumURL), capabilities);

        }  else{

            throw  new Exception("Cannot get driver from env. Driver value is"+ this.driver);
        }

        return  this.driver;

    }





}


