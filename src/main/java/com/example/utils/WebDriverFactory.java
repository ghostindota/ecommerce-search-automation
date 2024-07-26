package com.example.utils;

import com.example.config.Config;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import java.util.Properties;

public class WebDriverFactory {
    private static WebDriver driver;
    private static Properties properties = new Config().getProperties();

    public static WebDriver getDriver() {
        String browser = properties.getProperty("browser");
        // We can use WebDriverManager to automatically setup our chromeDriver but
        // becuase my system has some issues, I have implemented it manually.
        if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver",
                    "C:\\Users\\moham\\OneDrive\\Desktop\\chromedriver-win64\\chromedriver.exe");
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }
        return driver;
    }
}
