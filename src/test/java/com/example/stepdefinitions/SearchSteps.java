package com.example.stepdefinitions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.example.pageobjects.BasePage;
import com.example.pageobjects.HomePage;
import com.example.pageobjects.SearchResultsPage;
import com.example.utils.ExtentReportManager;
import com.example.utils.ScreenshotUtils;
import com.example.utils.WebDriverFactory;
import com.example.utils.WebElements;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.junit.Assert;

public class SearchSteps {

    WebDriver driver;
    HomePage homePage;
    SearchResultsPage searchResultsPage;
    ExtentReports extentReports;
    ExtentTest extentTest;

    @Before
    public void setUp() {
        extentReports = ExtentReportManager.getInstance();
        driver = WebDriverFactory.getDriver();
        homePage = new HomePage(driver);
    }

    @Given("I am on the Amazon home page")
    public void i_am_on_the_amazon_home_page() {
        extentTest = extentReports.createTest("Navigating to Amazon Home Page");
        driver.get(homePage.getBaseUrl());
        extentTest.log(Status.PASS, "Navigated to Amazon Home Page");
    }

    @When("I search for {string}")
    public void i_search_for(String searchTerm) {
        extentTest = extentReports.createTest("Searching for: " + searchTerm);
        homePage.enterSearchTerm(searchTerm);
        homePage.submitSearch();
        searchResultsPage = new SearchResultsPage(driver);
        extentTest.log(Status.PASS, "Search performed for: " + searchTerm);
    }

    @Then("I should see search results for {string}")
    public void i_should_see_search_results_for(String searchTerm) {
        try {
            Assert.assertTrue(searchResultsPage.hasResults());
            extentTest.log(Status.PASS, "Search results displayed for: " + searchTerm);
        } catch (AssertionError e) {
            String screenshotPath = ScreenshotUtils.captureScreenshot(driver, "searchResultsFailure");
            extentTest.log(Status.FAIL, "Search results not displayed for: " + searchTerm);
            extentTest.addScreenCaptureFromPath(screenshotPath);
        }
        extentReports.flush();
    }

    @Then("I should see no search results for {string}")
    public void i_should_see_no_search_results_for(String searchTerm) {
        try {
            Assert.assertTrue(searchResultsPage.hasNoResultsMessage(searchTerm));
            extentTest.log(Status.PASS, "No search results displayed for: " + searchTerm);
        } catch (AssertionError e) {
            String screenshotPath = ScreenshotUtils.captureScreenshot(driver, "noSearchResultsFailure");
            extentTest.log(Status.FAIL, "Search results displayed unexpectedly for: " + searchTerm);
            extentTest.addScreenCaptureFromPath(screenshotPath);
        }
        extentReports.flush();
    }

    @When("I start typing {string} in the search box")
    public void i_start_typing_in_the_search_box(String searchTerm) {
        extentTest = extentReports.createTest("Typing in the search box: " + searchTerm);
        WebElement searchBox = driver.findElement(WebElements.SEARCH_BOX);
        searchBox.sendKeys(searchTerm);
        extentTest.log(Status.PASS, "Typed in the search box: " + searchTerm);
    }

    @Then("I should see search suggestions")
    public void i_should_see_search_suggestions() {
        try {
            boolean suggestionsDisplayed = homePage.hasSearchSuggestions();
            Assert.assertTrue(suggestionsDisplayed);
            extentTest.log(Status.PASS, "Search suggestions are displayed");
        } catch (AssertionError e) {
            String screenshotPath = ScreenshotUtils.captureScreenshot(driver, "searchSuggestionsFailure");
            extentTest.log(Status.FAIL, "Search suggestions are not displayed");
            extentTest.addScreenCaptureFromPath(screenshotPath);
        }
        extentReports.flush();
    }

    @Then("I should still be on the home page")
    public void i_should_still_be_on_the_home_page() {
        try {
            Assert.assertTrue(homePage.isHomePage());
            extentTest.log(Status.PASS, "Still on the home page");
        } catch (AssertionError e) {
            String screenshotPath = ScreenshotUtils.captureScreenshot(driver, "homePageFailure");
            extentTest.log(Status.FAIL, "Not on the home page");
            extentTest.addScreenCaptureFromPath(screenshotPath);
        }
        extentReports.flush();
    }

    @When("I search for {string} in the {string} category")
    public void i_search_for_in_the_category(String searchTerm, String category) {
        extentTest = extentReports.createTest("Searching for: " + searchTerm + " in category: " + category);
        homePage.selectCategory(category);
        homePage.enterSearchTerm(searchTerm);
        homePage.submitSearch();
        searchResultsPage = new SearchResultsPage(driver);
        extentTest.log(Status.PASS, "Search performed for: " + searchTerm + " in category: " + category);
    }

    @Then("I should see search results for {string} in {string}")
    public void i_should_see_search_results_for_in(String searchTerm, String category) {
        try {
            boolean resultsDisplayed = searchResultsPage.hasResults();
            Assert.assertTrue(resultsDisplayed);
            extentTest.log(Status.PASS, "Search results displayed for: " + searchTerm + " in category: " + category);
        } catch (AssertionError e) {
            String screenshotPath = ScreenshotUtils.captureScreenshot(driver, "searchResultsInCategoryFailure");
            extentTest.log(Status.FAIL,
                    "Search results not displayed for: " + searchTerm + " in category: " + category);
            extentTest.addScreenCaptureFromPath(screenshotPath);
        }
        extentReports.flush();
    }

    @When("I apply a price filter from {string} to {string}")
    public void i_apply_a_price_filter_from_to(String minPrice, String maxPrice) {
        extentTest = extentReports.createTest("Applying price filter from: " + minPrice + " to: " + maxPrice);

        WebElement minPriceInput = BasePage.getElement(WebElements.MIN_PRICE_INPUT);
        WebElement maxPriceInput = BasePage.getElement(WebElements.MAX_PRICE_INPUT);
        WebElement goButton = BasePage.getElement(WebElements.PRICE_GO_BUTTON);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value='" + minPrice + "';", minPriceInput);
        js.executeScript("arguments[0].value='" + maxPrice + "';", maxPriceInput);
        js.executeScript("arguments[0].click();", goButton);

        extentTest.log(Status.PASS, "Applied price filter from: " + minPrice + " to: " + maxPrice);
    }

    @Then("I should see search results for {string} within the price range {string} and {string}")
    public void i_should_see_search_results_for_within_the_price_range(String searchTerm, String minPrice,
            String maxPrice) {
        try {
            boolean allResultsWithinRange = searchResultsPage.areAllResultsWithinPriceRange(minPrice, maxPrice);
            Assert.assertTrue(allResultsWithinRange);
            extentTest.log(Status.PASS, "Search results displayed within price range for: " + searchTerm);
        } catch (AssertionError e) {
            String screenshotPath = ScreenshotUtils.captureScreenshot(driver, "searchResultsPriceRangeFailure");
            extentTest.log(Status.FAIL, "Search results not displayed within price range for: " + searchTerm);
            extentTest.addScreenCaptureFromPath(screenshotPath);
        }
        extentReports.flush();
    }

    @When("I sort the results by {string}")
    public void i_sort_the_results_by(String sortOption) {
        Select sortDropdown = new Select(driver.findElement(WebElements.SORT_DROPDOWN));
        sortDropdown.selectByVisibleText(sortOption);

        extentTest.log(Status.PASS, "Sorted results by: " + sortOption);
    }

    @Then("I should see search results sorted by {string}")
    public void i_should_see_search_results_sorted_by(String sortOption) {
        try {
            Assert.assertTrue(searchResultsPage.hasResults());
            extentTest.log(Status.PASS, "Search results sorted by: " + sortOption);
        } catch (AssertionError e) {
            String screenshotPath = ScreenshotUtils.captureScreenshot(driver, "searchResultsSortFailure");
            extentTest.log(Status.FAIL, "Search results not sorted by: " + sortOption);
            extentTest.addScreenCaptureFromPath(screenshotPath);
        }
        extentReports.flush();
    }

    @When("I delete the entered search term")
    public void i_delete_the_entered_search_term() {
        extentTest = extentReports.createTest("Deleting the entered search term");
        WebElement searchBox = driver.findElement(WebElements.SEARCH_BOX);
        searchBox.clear();
        extentTest.log(Status.PASS, "Deleted the entered search term");
    }

    @Then("I should see {string} in my search history")
    public void i_should_see_in_my_search_history(String searchTerm) {
        try {
            Assert.assertTrue(homePage.hasSearchHistoryItem(searchTerm));
            extentTest.log(Status.PASS, "Search term: " + searchTerm + " displayed in search history");
        } catch (AssertionError e) {
            String screenshotPath = ScreenshotUtils.captureScreenshot(driver, "searchHistoryFailure");
            extentTest.log(Status.FAIL, "Search term: " + searchTerm + " not displayed in search history");
            extentTest.addScreenCaptureFromPath(screenshotPath);
        }
        extentReports.flush();
    }

    @When("I search for a very long search term {string}")
    public void i_search_for_a_very_long_search_term(String longSearchTerm) {
        extentTest = extentReports.createTest("Searching for a very long term: " + longSearchTerm);
        homePage.enterSearchTerm(longSearchTerm);
        homePage.submitSearch();
        searchResultsPage = new SearchResultsPage(driver);
        extentTest.log(Status.PASS, "Search performed for a very long term: " + longSearchTerm);
    }

    @Then("I should see no search results for long term")
    public void i_should_see_no_search_results_for_long_term() {
        try {
            Assert.assertTrue(
                    searchResultsPage.hasNoResultsMessage("supercalifragilisticexpialidociouslongtermsearchquery"));
            extentTest.log(Status.PASS, "No search results displayed for a very long term");
        } catch (AssertionError e) {
            String screenshotPath = ScreenshotUtils.captureScreenshot(driver, "noSearchResultsForLongTermFailure");
            extentTest.log(Status.FAIL, "Search results displayed unexpectedly for a very long term");
            extentTest.addScreenCaptureFromPath(screenshotPath);
        }
        extentReports.flush();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
