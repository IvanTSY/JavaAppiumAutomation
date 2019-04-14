package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFatory;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {

    @Test

    public void testSaveFirstArticleToMyList(){


        SearchPageObject SearchPageObject = SearchPageObjectFatory.get(driver);

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

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
