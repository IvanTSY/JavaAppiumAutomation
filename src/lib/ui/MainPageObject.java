package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import lib.Platform;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;

public class MainPageObject {

    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver){
        this.driver = driver;
    }


    public WebElement waitForElementPresent(String locator, String error_message, long timeoutInSecond){
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver,timeoutInSecond);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));

    }

    //перегрузка метода (если таймаут не обязательно увеличивать , стандарт - 5сек)
    public WebElement waitForElementPresent(String locator, String error_message){

        return waitForElementPresent(locator,error_message,5);

    }
    //упрощаем написание тестов\ действие клик
    public WebElement waitForElementAndClick(String locator, String error_message, long timeoutInSecond){

        WebElement element = waitForElementPresent(locator,error_message,timeoutInSecond);
        element.click();
        return element;
    }
    //отправляем текст(вводим)
    public WebElement waitForElementAndSendKeys(String locator, String value, String error_message, long timeoutInSecond){

        WebElement element = waitForElementPresent(locator,error_message,timeoutInSecond);
        element.sendKeys(value);
        return element;
    }

    //Ожидание отсутствия элемента

    public boolean waitForElementNotPresent (String locator, String error_message, long timeoutInSecond){

        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSecond);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }
    //Отчистка элемента
    public WebElement waitForElementAndClear (String locator, String error_message, long timeoutInSecond){
        WebElement element = waitForElementPresent(locator,error_message,timeoutInSecond);
        element.clear();
        return element;
    }

    public void swipeUp(int timeOfSwipe){

        Dimension size = driver.manage().window().getSize();
        int x = size.width/2;
        int start_y =(int) (size.height*0.8);
        int end_y =(int) (size.height*0.2);
        TouchAction action = new TouchAction(driver);
        action
                .press(PointOption.point(x,start_y))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(timeOfSwipe)))
                .moveTo(PointOption.point(x,end_y))
                .release()
                .perform();
    }

    public void swipeUpQuick(){
        swipeUp(200);
    }



    public  void swipeUpToFindElement(String locator, String error_message, int max_swipes){
        By by = this.getLocatorByString(locator);
        int already_swipes = 0;
        while (driver.findElements(by).size()==0){
            if(already_swipes>max_swipes){
                waitForElementPresent(locator,"Cannot find element by swiping up. \n"+error_message,0);
                return;
            }

            swipeUpQuick();
            ++already_swipes;
        }
    }


    public void clickElementToTheRightUpperCorner(String locator, String error_message)  //клик по кнопке удалить статью для ios
    {
        WebElement element = this.waitForElementPresent(locator + "/..", error_message); // "/.." переходим на элемент выше
        int right_x = element.getLocation().getX();  // вычисляем правую точку
        int upper_y = element.getLocation().getY(); // вычисляем вверх
        int lower_y = upper_y + element.getSize().getHeight(); // вычисляем низ
        int middle_y = (upper_y + lower_y) / 2; // вычисляем середину
        int width = element.getSize().getWidth(); // узнаем ширину элемента

        int point_to_click_x = (right_x + width) - 3; // находим правый верзний угол
        int point_to_click_y = middle_y;

        TouchAction action = new TouchAction(driver);
        action.tap(PointOption.point(point_to_click_x, point_to_click_y)).perform();
    }

    public void swipeElementToLeft(String locator, String error_message) // скрол элемента влево по оси Х
    {
        WebElement element = waitForElementPresent( // находим элемент на странице
                locator,
                error_message,
                10);
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY(); //
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action.press(PointOption.point(right_x,middle_y));
        action.waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)));

        if (Platform.getInstance().isAndroid()){
            action.moveTo(PointOption.point(left_x,middle_y));
        } else {
            int offset_x = ( -1 * element.getSize().getWidth());
            action.moveTo(PointOption.point(offset_x, 0));
        }
        action.release();
        action.perform();
    }

    public boolean isElementLocatedOnTheScreen(String locator)
    {
        int element_location_by_y = this.waitForElementPresent(locator, "Cannot find element by locator",10).getLocation().getY();
        int screen_size_by_y = driver.manage().window().getSize().getHeight();
        return element_location_by_y < screen_size_by_y;
    }

    public void swipeUpTillElementAppear(String locator, String error_message, int max_swipes)
    {
        int already_swiped = 0;

        while (!this.isElementLocatedOnTheScreen(locator))
        {
            if(already_swiped > max_swipes){
                Assert.assertTrue(error_message, this.isElementLocatedOnTheScreen(locator));
            }

            swipeUpQuick();
            ++already_swiped;
        }
    }

    public int getAmountOfElements(String locator){

        By by = this.getLocatorByString(locator);
        List elements = driver.findElements(by);
        return elements.size();
    }

    public void assertElementNotPresent(String locator,String error_message){

        int amount_of_elements = getAmountOfElements(locator);

        if(amount_of_elements > 0){
            String default_message = "An elements '"+ locator +"'supposed to not be present";
            throw new AssertionError(default_message + "" + error_message);
        }
    }

    public String waitForElementAndGetAttribute(String locator, String attribute, String error_message, long timeOutinSeconds){
        WebElement element = waitForElementPresent(locator,error_message,timeOutinSeconds);
        return element.getAttribute(attribute);
    }

    //метод для авто определения типов локатора

    private By getLocatorByString(String locator_with_type){
        String[] exploded_locator = locator_with_type.split(Pattern.quote(":"),2);
        String by_type = exploded_locator[0];
        String locator = exploded_locator[1];
        if(by_type.equals("xpath")){
            return By.xpath(locator);
        }else if(by_type.equals("id")){
            return By.id(locator);
        }else {
                throw new IllegalArgumentException("Cannot get type of locator. Locator:"+locator_with_type);
        }
    }

}
