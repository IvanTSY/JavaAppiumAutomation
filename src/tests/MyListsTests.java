package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;
import sun.applet.Main;

public class MyListsTests extends CoreTestCase {
    private static final String name_of_folder = "Learning programming";

    @Test
    public void testSaveFirstArticleToMyList()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();  //открытие поиска википедии
        SearchPageObject.typeSearchLine("Java"); //ввод текста в строку поиска
        SearchPageObject.clicByArticleWithSubstring("Object-oriented programming language");  //клик по статье найденной по ресурс ид и тексту

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();   // выбираем элемент из списка поиска по заголовку

        String article_title = ArticlePageObject.getArticleTitle();

        if(Platform.getInstance().isAndroid()){
            ArticlePageObject.addArticleToMyList(name_of_folder); // передаем имя папки в которую будет добавляться статья
        } else {
            ArticlePageObject.addArticlesToMySaved();  // сохранение статьи для ios
        }
        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);
        MyListsPageObject.closeIOSUnknownForm("Wikipedia");
                //написать метод для клика в правый верхний угол
        ArticlePageObject.closeArticle();  // находим элемент "Navigate up" и кликаем по нему ЗАКРЫВАЕМ

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickMyList();  // находим элемент "My lists" и кликаем по нему

//        ArticlePageObject.waitArticleTitlePresent(); // ждем появление элемента



        if (Platform.getInstance().isAndroid()){
            MyListsPageObject.openFolderByName(name_of_folder); // находим папку с названием "Learning programming" и кликаем по нему
        }
        MyListsPageObject.swipeByArticleToDelete(article_title);   // скролим элемент влево и сразу же идет проверка
    }

}
