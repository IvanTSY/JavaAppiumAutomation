package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.WebElement;

abstract public class ArticlePageObject extends MainPageObject {

    protected static String
            TITLE ,
            FOOTER_ELEMENT ,
            OPTIONS_BUTTON ,
            OPTIONS_ADD_TO_MY_LIST_BUTTON ,
            ADD_TO_MY_LIST_OVERLAY ,
            MY_LIST_NAME_INPUT ,
            MY_LIST_OK_BUTTON ,
            CLOSE_ARTICLE_BUTTON ;

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }



    public WebElement waitForTitleElement(){
        return waitForElementPresent(TITLE,"Cannot find article title on page",15);
    }



    public String getArticleTitle(){
        WebElement title_element = waitForTitleElement();
        if(Platform.getInstance().isAndroid()){
            return title_element.getAttribute("text");
        }else{
            return title_element.getAttribute("name");
        }

    }

    public void swipeToFooter(){

        if(Platform.getInstance().isAndroid()){
        this.swipeUpToFindElement(
                FOOTER_ELEMENT,
                "Cannot find element to end article",
                40);
        }else{
            this.swipeUpTillElementAppear(
                    FOOTER_ELEMENT,
                    "Cannot find element to end article IOS",
                    40);
        }
    }

    public void addArticleToMyList(String name_of_folder){
        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Can not find menu",
                5
        );

        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Can not find element menu 'add'",
                5
        );

        this.waitForElementAndClick(
                ADD_TO_MY_LIST_OVERLAY,
                "Con not find 'Got it'btn",
                5
        );

        this.waitForElementAndClear(
                MY_LIST_NAME_INPUT,
                "can not find to clear text field",
                5
        );

        this.waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT,
                name_of_folder,
                "can not find input text field",
                5
        );

        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,
                "Can not find element menu 'add'",
                5
        );

    }

    public void closeArticle(){
        this.waitForElementAndClick(
                CLOSE_ARTICLE_BUTTON,
                "Can not find close button",
                5
        );
    }
    public void addArticlesToMySaved() // добавление статьи в IOS
    {
        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Cannot find option to add article to reading list", 5);
    }




}
