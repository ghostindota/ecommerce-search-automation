package com.example.pageobjects;

import com.example.utils.WebElements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchResultsPage extends BasePage {

    public SearchResultsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // Checks if there are search results displayed
    public boolean hasResults() {
        List<WebElement> searchResults = driver.findElements(WebElements.SEARCH_RESULTS);
        return !searchResults.isEmpty();
    }

    // Checks if the no results message is displayed for a given search term
    public boolean hasNoResultsMessage(String searchTerm) {
        try {
            WebElement noResultsMessage = driver.findElement(WebElements.NO_RESULTS_MESSAGE);
            waitForVisibility(noResultsMessage);
            return noResultsMessage.isDisplayed()
                    && noResultsMessage.getText().contains("No results for " + searchTerm);
        } catch (Exception e) {
            return false;
        }
    }

    // Checks if there are search suggestions displayed
    public boolean hasSearchSuggestions() {
        try {
            List<WebElement> searchSuggestions = driver.findElements(WebElements.HISTORY_CONTAINER);
            waitForVisibility(searchSuggestions.get(0));
            return !searchSuggestions.isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    // Checks if the search history is displayed
    public boolean hasSearchHistory() {
        try {
            WebElement searchHistory = driver.findElement(WebElements.SEARCH_HISTORY);
            waitForVisibility(searchHistory);
            return searchHistory.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void navigateToPage(int pageNumber) {
        WebElement pageLink = getElement(WebElements.getPageLink(pageNumber));
        pageLink.click();
    }

    public void navigateToNextPage() {
        WebElement nextPageLink = getElement(WebElements.NEXT_PAGE_LINK);
        nextPageLink.click();
    }

    // Validates if all search results are within the specified price range
    public boolean areAllResultsWithinPriceRange(String minPrice, String maxPrice) {
        try {
            List<WebElement> results = driver.findElements(WebElements.SEARCH_RESULTS);
            double min = Double.parseDouble(minPrice);
            double max = Double.parseDouble(maxPrice);

            for (WebElement result : results) {
                try {
                    String priceText = result.findElement(WebElements.PRICE_WHOLE).getText().replace(",", "");
                    double price = Double.parseDouble(priceText);
                    if (price < min || price > max) {
                        return false;
                    }
                } catch (Exception e) {
                    continue; // skip results without a price
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
