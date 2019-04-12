package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFatory;
import org.junit.Test;

public class SearchTests extends CoreTestCase {

    @Test

    public void testSearch(){

        SearchPageObject SearchPageObject = SearchPageObjectFatory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test

    public void testCancelSearch(){

        //нашли элемент и кликнули
        SearchPageObject SearchPageObject = SearchPageObjectFatory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();

    }

    @Test

    public void testAmountOfEmptySearch(){

        SearchPageObject SearchPageObject = SearchPageObjectFatory.get(driver);

        SearchPageObject.initSearchInput();
        String search_line = "assdafdawfewfwefery";

        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();

    }


    @Test

    public void testAmountOfNotEmptySearch(){

        SearchPageObject SearchPageObject = SearchPageObjectFatory.get(driver);

        String search_line = "Linkin Park discography";

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_result = SearchPageObject.getAmountOfFoundArticle();

        assertTrue(
                "We found some elements",
                amount_of_search_result>0
        );
    }

}
