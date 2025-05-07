package com.take_home.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.*;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class DriverManager {
    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null ) {
            initializeDriver("chrome");
        }
        return driver;
    }

    public static void initializeDriver(String browserType) {

        String driversPath = new File("src/main/resources/drivers").getAbsolutePath();

        switch (browserType.toLowerCase()) {
            case "firefox":
                System.setProperty("webdriver.gecko.driver", driversPath + "/geckodriver");
                driver = new FirefoxDriver();
                break;
            case "chrome":
                System.setProperty("webdriver.chrome.driver", driversPath + "/chromedriver");
                driver = new ChromeDriver();
                break;
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}

