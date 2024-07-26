package com.example.utils;

import org.openqa.selenium.By;

public class WebElements {
    // Home Page Locators
    public static final By SEARCH_BOX = By.xpath("//input[@id='twotabsearchtextbox']");
    public static final By SEARCH_BUTTON = By.id("nav-search-submit-button");
    public static final By HOME_LOGO = By.cssSelector("#nav-logo a");
    public static final By CATEGORY_DROPDOWN = By.id("nav-search-dropdown-card");
    public static final By HISTORY_CONTAINER = By.className("left-pane-results-container");

    // Search Results Page Locators
    public static final By SEARCH_RESULTS = By.cssSelector(".s-search-results .s-result-item");
    public static final By NO_RESULTS_MESSAGE = By.xpath("//span[contains(text(),'No results for')]");
    public static final By SEARCH_SUGGESTIONS = By.cssSelector(".s-suggestion");
    public static final By SEARCH_HISTORY = By.id("search-history");
    public static final By MIN_PRICE_INPUT = By.xpath(
            "//input[@id='p_36/range-slider_slider-item_lower-bound-slider']");
    public static final By MAX_PRICE_INPUT = By.xpath(
            "//input[@id='p_36/range-slider_slider-item_upper-bound-slider']");
    public static final By PRICE_GO_BUTTON = By.cssSelector(".a-button-input");
    public static final By SORT_DROPDOWN = By.id("s-result-sort-select");
    public static final By PRICE_WHOLE = By.cssSelector(".a-price-whole");

    public static By getCategoryOption(String category) {
        return By.xpath("//option[contains(text(), '" + category + "')]");
    }

    public static By getPageLink(int pageNumber) {
        return By.xpath("//a[@aria-label='Go to page " + pageNumber + "']");
    }

    public static final By NEXT_PAGE_LINK = By.xpath(
            "//a[@class='s-pagination-item s-pagination-next s-pagination-button s-pagination-separator']");
}
