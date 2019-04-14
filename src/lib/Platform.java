package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class Platform {


    private static Platform instance;
    private Platform (){}

    public static Platform getInstance() {

        if (instance == null){
            instance = new Platform();
        }

        return instance;
    }

    private static final String PLATFORM_IOS = "ios";
    private static final String PLATFORM_ANDROID = "android";
    Object AppiumDriverLocalService;
    private static String AppiumURL = "http://127.0.0.1:4723/wd/hub" ;

    private DesiredCapabilities getAndroidDesiredCapabilities() {

            DesiredCapabilities capabilities = new DesiredCapabilities();
//объект для работы капы с апиум сервером 12.1


            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("deviceName", "AndroidTestDevice");
            capabilities.setCapability("platformVersion", "8.0");
//для апиум сервера 10
            //capabilities.setCapability("automationName", "Appium");
//Капа для апиум сервера 12.1
            capabilities.setCapability("automationName", AppiumDriverLocalService);
            capabilities.setCapability("appPackage", "org.wikipedia");
            capabilities.setCapability("appActivity", ".main.MainActivity");
            capabilities.setCapability("orientation", "PORTRAIT");
            capabilities.setCapability("app", "C:\\Users\\FBI\\IdeaProjects\\ApiumTraining\\JavaAppiumAutomation\\apks\\org.wikipedia.apk"); //Дом
            //capabilities.setCapability("app","D:\\GitHub\\JavaAppiumAutomation\\apks\\org.wikipedia.apk");  //Работа
            return capabilities;
    }


        private DesiredCapabilities getIosDesiredCapabilities() {

            DesiredCapabilities capabilities = new DesiredCapabilities();

            capabilities.setCapability("platformName", "iOS");
            capabilities.setCapability("deviceName", "iPhone SE");
            capabilities.setCapability("platformVersion", "11.4");
            capabilities.setCapability("orientation", "PORTRAIT");
            capabilities.setCapability("connectHardwareKeyboard", false);
            capabilities.setCapability("autoDismissAlerts","true");

            capabilities.setCapability("app", "/Users/tester/Documents/GitHub/JavaAppiumAutomation/apks/Wikipedia.app"); //Дом
            return capabilities;
        }
    private String getPlatformVar(){
    return System.getenv("PLATFORM");
    }
    private boolean isPlatform(String my_pltform){
    String platform = this.getPlatformVar();
    return my_pltform.equals(platform);
    }

    public boolean isAndroid(){
        return isPlatform(PLATFORM_ANDROID);
    }

    public boolean isIos(){
        return isPlatform(PLATFORM_IOS);
    }

    public AppiumDriver getDriver() throws Exception{
        URL URL = new URL(AppiumURL);
        if (this.isAndroid()){
            return new AndroidDriver(URL,getAndroidDesiredCapabilities());
        }else if(this.isIos()){
            return new IOSDriver(URL,getIosDesiredCapabilities());
        }else {
            throw new Exception("Cannot detect type of Driver. Platform value "+this.getPlatformVar());
        }
    }

}


