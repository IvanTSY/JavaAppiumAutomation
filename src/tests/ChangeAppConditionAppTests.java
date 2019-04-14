package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFatory;
import org.junit.Test;

public class ChangeAppConditionAppTests extends CoreTestCase {

    @Test

    public void testChangeScreenOrientationOnSearchResult() {


        SearchPageObject SearchPageObject = SearchPageObjectFatory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clicByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

        String title_before_rotation = ArticlePageObject.getArticleTitle();
        this.rotateScreenLandscape();
        String title_after_rotation = ArticlePageObject.getArticleTitle();

        assertEquals(
                "Article title have been changed after screen rotation",
                title_before_rotation,
                title_after_rotation
        );

        this.rotateScreenPortrait();
        String title_after_Second_rotation = ArticlePageObject.getArticleTitle();

        assertEquals(
                "Article title have been changed after screen rotation",
                title_before_rotation,
                title_after_Second_rotation
        );
    }

    @Test

    public void testSearchArticleInBackground(){

        SearchPageObject SearchPageObject = SearchPageObjectFatory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");

        this.appBackground(5);
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }

}
