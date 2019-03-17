import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class FirstTest {

    private AppiumDriver driver;

    @Before

    public void setUp() throws Exception{
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","6.0");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","C:\\Users\\FBI\\IdeaProjects\\ApiumTraining\\JavaAppiumAutomation\\apks\\org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
    }

    @After

    public void tearDawn(){
        driver.quit();
    }


//    @Test
//
//    public void firstTest(){
//
//        //Новые , компактные функции поиска , нажатия  и отправки текста
//        //1й после
//        waitForElementByXPATHAndClick(
//                "//*[contains(@text,'Search Wikipedia')]",
//                "Can not Find Search Wikipedia",
//                5);
//
//        waitForElementByXPATHAndSendKeys(
//                "//*[contains(@text,'Search…')]",
//                "java",
//                "Can not Find search input",
//                5);
//
//
//        //1й до
//        //WebElement element_to_init_search = driver.findElementByXPath("//*[contains(@text,'Search Wikipedia')]");
//        //element_to_init_search.click();
//
//        //2й до
//        //WebElement element_to_search_enter = waitForElementByXpath(
//        //        "//*[contains(@text,'Search…')]",
//        //        "Can not Find search input"
//        //);
//        //element_to_search_enter.sendKeys("java");
//
//
//        waitForElementPressentByXpath(
//                "//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']",
//                "Not find the text 'Object-oriented programming language' in 'java' topic"
//
//        );
//    }

    @Test

    public void testCancelSearch(){
        waitForElementByIdAndClick(
                "org.wikipedia:id/search_container",
                "Can Not Find Serch Bar",
                5
        );

        waitForElementByIdAndClick(
                "org.wikipedia:id/search_close_btn",
                "Can Not Find close btn on the Serch Bar",
                5
        );
    }

    private WebElement waitForElementPresentByXpath(String xpath, String error_message, long timeoutInSecond){
        WebDriverWait wait = new WebDriverWait(driver,timeoutInSecond);
        wait.withMessage(error_message + "\n");
        By by = By.xpath(xpath);
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));

    }

    //перегрузка метода (если таймаут не обязательно увеличивать , стандарт - 5сек)
    private WebElement waitForElementPresentByXpath(String xpath, String error_message){

        return waitForElementPresentByXpath(xpath,error_message,5);

    }
    //упрощаем написание тестов\ действие клик
    private WebElement waitForElementByXPATHAndClick(String xpath, String error_message, long timeoutInSecond){

        WebElement element = waitForElementPresentByXpath(xpath,error_message,timeoutInSecond);
        element.click();
        return element;
    }
    //отправляем текст
    private WebElement waitForElementByXPATHAndSendKeys(String xpath, String value, String error_message, long timeoutInSecond){

        WebElement element = waitForElementPresentByXpath(xpath,error_message,timeoutInSecond);
        element.sendKeys(value);
        return element;
    }



    //Методы для поиска по ID
    private WebElement waitForElementPresentById(String id, String error_message, long timeoutInSecond){
        WebDriverWait wait = new WebDriverWait(driver,timeoutInSecond);
        wait.withMessage(error_message + "\n");
        By by = By.id(id);
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));

    }

    //упрощаем написание тестов\ действие клик
    private WebElement waitForElementByIdAndClick(String id, String error_message, long timeoutInSecond){

        WebElement element = waitForElementPresentById(id, error_message, timeoutInSecond);
        element.click();
        return element;
    }




}
