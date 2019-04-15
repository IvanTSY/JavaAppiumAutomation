package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.By;

abstract public class MyListsPageObject extends MainPageObject {

    protected static String
        FOLDER_BY_NAME_TPL ,
        ARTICLE_BY_TITLE_TPL,
            WEB_FORM;

    private static String getFolderXpathByName(String name_of_folder) // заменяет значение константы FOLDER_BY_NAME_TPL на значение из name_of_folder
    {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getSavedArticleXpathByTitle(String article_title){
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }
//TODO тут передаем имя в икспас убрал ВЕБФОРМ СТРИНГ
    private static String getWebFormToClose(String WEBFORM){
        return MyListsPageObject.WEB_FORM;
    }

    public MyListsPageObject(AppiumDriver driver) {
        super(driver);
    }



    public void openFolderByName(String name_of_folder){

        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(
                folder_name_xpath,
                "Can not find folder by name"+name_of_folder,
                5
        );
    }


    public void waitForArticleToAppearByTitle(String article_title){
        String article_xpath = getFolderXpathByName(article_title);
        this.waitForElementPresent(article_xpath, "Cannot find saved article by title",15);
    }

    public void waitForArticleToDisappearByTitle(String article_title){
        String article_xpath = getFolderXpathByName(article_title);
        this.waitForElementNotPresent(article_xpath, "Saved article still present with title",15);
    }

    public void swipeByArticleToDelete(String article_title){

        this.waitForArticleToAppearByTitle(article_title);  // убеждаемся что статья присутствует
        String article_xpath = getFolderXpathByName(article_title);
        this.swipeElementToLeft(
                article_xpath,
                "Cannot find saved article"
        );

        if (Platform.getInstance().isIos()){
            this.clickElementToTheRightUpperCorner(article_xpath, "Cannot find saved article");
        }

        this.waitForArticleToDisappearByTitle(article_title); //  убеждаемся что статья удалилась
    }

    public void closeIOSUnknownForm(){
        String webviev = getWebFormToClose(WEB_FORM);
        this.waitForElementPresent(WEB_FORM,"not see web v iew",5);
        this.clickElementToTheRightUpperCornerForWEBFORM(
                webviev,"Not find web element");
    }


}





