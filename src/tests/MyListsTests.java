package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {

    @Test

    public void testSaveFirstArticleToMyList(){


        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);

        NavigationUI NavigationUI = new NavigationUI(driver);

        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clicByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject.waitForTitleElement();
        String title_article = ArticlePageObject.getArticleTitle();
        String name_of_folder = "Learning Programming";
        ArticlePageObject.addArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();
        NavigationUI.clickMyList();
        MyListsPageObject.openFolderByName(name_of_folder);
        MyListsPageObject.swipeByArticleToDelete(title_article);
    }

}
