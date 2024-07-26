package com.example.pageobjects;

import com.example.config.Config;
import com.example.utils.WebElements;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {

    private Config config;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        config = new Config();
    }

    public void enterSearchTerm(String searchTerm) {
        WebElement searchBox = getElement(WebElements.SEARCH_BOX);
        searchBox.clear();
        searchBox.sendKeys(searchTerm);
    }

    public void submitSearch() {
        WebElement searchButton = getElement(WebElements.SEARCH_BUTTON);
        searchButton.click();
    }

    public String getBaseUrl() {
        return config.getProperty("baseUrl");
    }

    public boolean isHomePage() {
        WebElement homeLogo = getElement(WebElements.HOME_LOGO);
        return homeLogo.isDisplayed();
    }

    public void selectCategory(String category) {
        WebElement categoryDropdown = getElement(WebElements.CATEGORY_DROPDOWN);
        categoryDropdown.click();
        WebElement categoryOption = driver.findElement(WebElements.getCategoryOption(category));
        categoryOption.click();
    }

    public boolean hasSearchHistoryItem(String searchTerm) {
        WebElement searchBox = getElement(WebElements.SEARCH_BOX);
        searchBox.click();
        WebElement historyContainer = getElement(WebElements.HISTORY_CONTAINER);
        List<WebElement> historyItems = historyContainer.findElements(By.tagName("span"));
        for (WebElement item : historyItems) {
            if (item.getText().contains(searchTerm)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasSearchSuggestions() {
        try {
            List<WebElement> searchSuggestions = driver.findElements(WebElements.SEARCH_SUGGESTIONS);
            waitForVisibility(searchSuggestions.get(0));
            return !searchSuggestions.isEmpty();
        } catch (Exception e) {
            return false;
        }
    }
}
