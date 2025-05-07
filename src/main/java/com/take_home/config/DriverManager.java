package com.take_home.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.*;
import java.io.File;
import java.util.concurrent.TimeUnit;
import java.time.Duration;

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
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--headless");
                driver = new FirefoxDriver(firefoxOptions);
                break;
            case "chrome":
                System.setProperty("webdriver.chrome.driver", driversPath + "/chromedriver");
                ChromeOptions chromeOptions = new ChromeOptions();  
               // chromeOptions.addArguments("--headless");
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                chromeOptions.addArguments("--window-size=1920,1080");
                chromeOptions.addArguments("--incognito");
                driver = new ChromeDriver(chromeOptions);
                break;
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}

