import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.net.URL;
import java.util.List;

public class FirstTest {

    private AppiumDriver driver;

    @Before

    public void setUp() throws Exception{
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","8.0");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");

        capabilities.setCapability("app","C:\\Users\\FBI\\IdeaProjects\\ApiumTraining\\JavaAppiumAutomation\\apks\\org.wikipedia.apk"); //Дом
        //capabilities.setCapability("app","D:\\GitHub\\JavaAppiumAutomation\\apks\\org.wikipedia.apk");  //Работа


        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
    }

    @After

    public void tearDawn(){
        driver.quit();
    }



    @Test

    public void testSearchArticleInBackground(){
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Can not Find Search Wikipedia",
                15);

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "java",
                "Can not Find search input",
                5);

        waitForElementPresent(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Can not Find Search Wikipedia",
                5);

        driver.runAppInBackground(5);

        waitForElementPresent(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Can not Find Search Wikipedia after run app in background",
                5);



    }

    @Test

    public void testChangeScreenOrientationOnSearchResult(){

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Can not Find Search Wikipedia",
                15);

        String search_line = "Java";

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                search_line,
                "Can not Find search input",
                5);
        waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Can not Find Search Wikipedia topic searching by"+ search_line,
                15);

        String title_before_rotation = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find title of article",
                15
        );


        driver.rotate(ScreenOrientation.LANDSCAPE);

        String title_after_rotation = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find title of article",
                15
        );

        Assert.assertEquals(
                "Article title have been changed after screen rotation",
                title_before_rotation,
                title_after_rotation
        );

        driver.rotate(ScreenOrientation.PORTRAIT);

        String title_afterSecond_rotation = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find title of article",
                15
        );

        Assert.assertEquals(
                "Article title have been changed after screen rotation",
                title_before_rotation,
                title_afterSecond_rotation
        );





    }


    @Test

    public void testAmountOfEmptySearch(){
        waitForElementAndClick(
                        By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                        "Can not Find Search Wikipedia",
                        15);

        String search_line = "assdafdawfewfwefery";

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                search_line,
                "Can not Find search input",
                5);

String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
String empty_result_label = "//*[@text='No results found']";

        waitForElementPresent(
                By.xpath(empty_result_label),
                "Cannot find empty result after request"+search_line,
                15
        );

        assertElementNotPresent(
                By.xpath(search_result_locator),
                "We find some results"+ search_line
        );
    }


        @Test

    public void testAmountOfNotEmptySearch(){
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Can not Find Search Wikipedia",
                15);

String search_line = "Linkin Park discography";

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                search_line,
                "Can not Find search input",
                5);
String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";

        waitForElementPresent(
                By.xpath(search_result_locator),
                "Cannot find requst of"+search_line,
                15
        );

        int amount_of_search_result = getAmountOfElements(
                By.xpath(search_result_locator)
        );

        Assert.assertTrue(
                "We found some elements",
                amount_of_search_result>0
        );
    }

    @Test

    public void saveFirstArticleToMyList(){

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Can not Find Search Wikipedia",
                15);

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "java",
                "Can not Find search input",
                5);

        waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Can not Find Search Wikipedia",
                5);

        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Can not Find Article title",
                5);

        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Can not find menu",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Can not find element menu 'add'",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Con not find 'Got it'btn",
                5
        );

        waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "can not find to clear text field",
                5
        );
String name_of_folder = "Learning Programming";
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "can not find input text field",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Can not find element menu 'add'",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Can not find close button",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Can not find navigation btn to my list",
                10
        );

        waitForElementAndClick(
                By.xpath("//*[@text = '"+name_of_folder+"']"),
                "Can not find creatin folder",
                5
        );

        swipeElementToLeft(
                By.xpath("//*[@text='Java (programming language)']"),
                "Can not find saved article"
        );

        waitForElementNotPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "Article is not delete",
                5
        );



    }


    @Test

    public void testSwipeArticle(){

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Can not Find Search Wikipedia",
                15);

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "appium",
                "Can not Find search input",
                5);

        waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_title'][@text='Appium']"),
                "Can not Find Search Wikipedia",
                5);

        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Can not Find Article title",
                5);

        swipeUpToFindElement(
                By.xpath("//*[@text = 'View page in browser']"),
                "Dont find the element 'View page in browser' ",
                20
        );

    }

    @Test

    public void testCompareArticleTitle(){

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Can not Find Search Wikipedia",
                15);

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "java",
                "Can not Find search input",
                5);

        waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Can not Find Search Wikipedia",
                5);

        WebElement title_element = waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Can not Find Article title",
                5);

        String article_title = title_element.getAttribute("text");

        Assert.assertEquals(
                "Wrong text or not find text in state",
                "Java (programming language)",
                article_title
        );
    }

    @Test

    public void firstTest(){

        //Новые , компактные функции поиска , нажатия  и отправки текста
        //1й после
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Can not Find Search Wikipedia",
                15);

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "java",
                "Can not Find search input",
                5);

        waitForElementPresent(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Not find the text 'Object-oriented programming language' in 'java' topic"

        );
    }

    @Test

    public void testCancelSearch(){

        //нашли элемент и кликнули
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Can Not Find Serch Bar",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "java",
                "Can not Find search input",
                5
        );

        waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Search bar is not empty",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Can Not Find close btn on the Serch Bar",
                5
        );
        //ждём пока элемент исчезнет
        waitForElementNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "Can Find close btn on the Serch Bar",
                5
        );


    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSecond){
        WebDriverWait wait = new WebDriverWait(driver,timeoutInSecond);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));

    }

    //перегрузка метода (если таймаут не обязательно увеличивать , стандарт - 5сек)
    private WebElement waitForElementPresent(By by, String error_message){

            return waitForElementPresent(by,error_message,5);

    }
    //упрощаем написание тестов\ действие клик
    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSecond){

        WebElement element = waitForElementPresent(by,error_message,timeoutInSecond);
        element.click();
        return element;
    }
    //отправляем текст(вводим)
    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSecond){

        WebElement element = waitForElementPresent(by,error_message,timeoutInSecond);
        element.sendKeys(value);
        return element;
    }

    //Ожидание отсутствия элемента

    private boolean waitForElementNotPresent (By by, String error_message, long timeoutInSecond){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSecond);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }
//Отчистка элемента
    private WebElement waitForElementAndClear (By by, String error_message, long timeoutInSecond){
        WebElement element = waitForElementPresent(by,error_message,timeoutInSecond);
        element.clear();
        return element;
    }

    protected void swipeUp(int timeOfSwipe){
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width/2;
        int start_y =(int) (size.height*0.8);
        int end_y =(int) (size.height*0.2);
        action
                .press(x,start_y)
                .waitAction(timeOfSwipe)
                .moveTo(x,end_y)
                .release()
                .perform();
    }

    protected void swipeUpQuick(){
        swipeUp(200);
    }

    protected  void swipeUpToFindElement(By by, String error_message, int max_swipes){
       int already_swipes = 0;
        while (driver.findElements(by).size()==0){
            if(already_swipes>max_swipes){
                waitForElementPresent(by,"Cannot find element by swiping up. \n"+error_message,0);
                return;
            }

            swipeUpQuick();
            ++already_swipes;
        }
    }

    protected  void swipeElementToLeft(By by, String error_message){
        WebElement element = waitForElementPresent(
                by,
                error_message,
                10);
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y)/2;

        TouchAction action = new TouchAction(driver);
        action
                .press(right_x,middle_y)
                .waitAction(300)
                .moveTo(left_x,middle_y)
                .release()
                .perform();
    }

    private int getAmountOfElements(By by){
        List elements = driver.findElements(by);
        return elements.size();
    }

    private void assertElementNotPresent(By by,String error_message){

        int amount_of_elements = getAmountOfElements(by);

        if(amount_of_elements > 0){
            String default_message = "An elements '"+ by.toString() +"'supposed to not be present";
            throw new AssertionError(default_message + "" + error_message);
        }
    }

    private String waitForElementAndGetAttribute(By by, String attribute, String error_message, long timeOutinSeconds){
        WebElement element = waitForElementPresent(by,error_message,timeOutinSeconds);
        return element.getAttribute(attribute);
    }






}
