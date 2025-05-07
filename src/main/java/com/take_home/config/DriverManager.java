package com.take_home.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.*;
import java.io.File;
import java.time.Duration;
import org.openqa.selenium.PageLoadStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DriverManager {
    private static final Logger logger = LoggerFactory.getLogger(DriverManager.class);
    private static PageLoadStrategy PAGE_LOAD_STRATEGY = PageLoadStrategy.NORMAL;
    private static WebDriver driver;

    static {
        String strategyName = ConfigReader.getProperty("page.load.strategy", "normal");
        try {
            PAGE_LOAD_STRATEGY = PageLoadStrategy.valueOf(strategyName);
            logger.info("Page load strategy set to {}.", PAGE_LOAD_STRATEGY);
        } catch (IllegalArgumentException e) {
            logger.warn("Invalid page load strategy: {}, using default: {}.", strategyName);
            PAGE_LOAD_STRATEGY = PageLoadStrategy.NORMAL;
        }
    }

    public static WebDriver getDriver() {
        if (driver == null ) {
            initializeDriver(ConfigReader.getProperty("browser", "chrome"));
        }
        return driver;
    }

    public static void initializeDriver(String browserType) {
        String driversPath = new File("src/main/resources/drivers").getAbsolutePath();
        boolean headless = ConfigReader.getBooleanProperty("headless", false);
        logger.info("Initializing {} driver with headless: {}, page load strategy: {}.", browserType, headless, PAGE_LOAD_STRATEGY);

        switch (browserType.toLowerCase()) {
            case "firefox":
                System.setProperty("webdriver.gecko.driver", driversPath + "/geckodriver");
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (headless) {
                    firefoxOptions.addArguments("--headless");
                }
                firefoxOptions.setPageLoadStrategy(PAGE_LOAD_STRATEGY);
                driver = new FirefoxDriver(firefoxOptions);
                break;
            case "chrome":
                System.setProperty("webdriver.chrome.driver", driversPath + "/chromedriver");
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setPageLoadStrategy(PAGE_LOAD_STRATEGY);
                if (headless) {
                    chromeOptions.addArguments("--headless");
                }
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                chromeOptions.addArguments("--window-size=1920,1080");
                chromeOptions.addArguments("--incognito");
                driver = new ChromeDriver(chromeOptions);
                break;
            default:
                logger.error("Unsupported browser type: {}", browserType);
                throw new IllegalArgumentException("Unsupported browser type: " + browserType);
        }
        driver.manage().window().maximize();

        int implicitWait = ConfigReader.getIntProperty("implicit.wait", 10);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
        logger.info("Driver initialized with implicit wait of {} seconds.", implicitWait);
        
        
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
            logger.info("Driver quit and reset to null");
        }
    }
    public static void setPageLoadStrategy(PageLoadStrategy strategy) {
        logger.info("Setting page load strategy from {} to {}", PAGE_LOAD_STRATEGY, strategy);
        PAGE_LOAD_STRATEGY = strategy;

        if (driver != null) {
            quitDriver();
        }
    }
}

