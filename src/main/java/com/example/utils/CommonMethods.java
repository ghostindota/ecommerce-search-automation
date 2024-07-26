package com.example.utils;

import org.openqa.selenium.WebElement;

public class CommonMethods {

    public void enterText(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    public void clickElement(WebElement element) {
        element.click();
    }
}
