package com.example.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected static WebDriverWait wait;
    protected ExtentReports extentReports;
    protected ExtentTest extentTest;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        BasePage.wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // Default wait time
    }

    protected void waitForVisibility(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForClickability(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void waitForPresenceOfElement(String cssSelector) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssSelector)));
    }

    protected void waitForPresenceOfElement(By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public static WebElement getElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
