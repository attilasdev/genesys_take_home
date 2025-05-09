package com.take_home.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.*;
import java.time.Duration;
import org.openqa.selenium.PageLoadStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ThreadLocalDriverManager {
    private static final Logger logger = LoggerFactory.getLogger(ThreadLocalDriverManager.class);
    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    private static ThreadLocal<PageLoadStrategy> strategyThreadLocal = 
        ThreadLocal.withInitial(() -> PageLoadStrategy.NORMAL);

    public static WebDriver getDriver() {
        if (driverThreadLocal.get() == null) {
            initializeDriver(ConfigReader.getProperty("browser", "chrome"));
        }
        return driverThreadLocal.get();
    }

    public static void initializeDriver(String browserType) {
        boolean headless = ConfigReader.getBooleanProperty("headless", false);
        logger.info("Initializing {} driver with headless: {}, page load strategy: {}.",
                browserType, headless, strategyThreadLocal.get());

        WebDriver driver;
        switch (browserType.toLowerCase()) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setPageLoadStrategy(strategyThreadLocal.get());
                if (headless) {
                    firefoxOptions.addArguments("--headless");
                }
                driver = new FirefoxDriver(firefoxOptions);
                break;

            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setPageLoadStrategy(strategyThreadLocal.get());
                if (headless) {
                    chromeOptions.addArguments("--headless");
                }
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                chromeOptions.addArguments("--window-size=1920,1080");
                chromeOptions.addArguments("--incognito");
                driver = new ChromeDriver(chromeOptions);
        }

        driver.manage().window().maximize();
        int implicitWait = ConfigReader.getIntProperty("implicit.wait", 10);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
        
        // Store in ThreadLocal
        driverThreadLocal.set(driver);
        logger.info("Driver initialized in thread: {}", Thread.currentThread().getId());
    }

    public static void quitDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            driver.quit();
            driverThreadLocal.remove();
            logger.info("Driver quit in thread: {}", Thread.currentThread().getId());
        }
    }
    
    public static void setPageLoadStrategy(PageLoadStrategy strategy) {
        logger.info("Setting page load strategy to {} in thread {}", 
            strategy, Thread.currentThread().getId());
        strategyThreadLocal.set(strategy);
        
        // Restart driver if already initialized
        if (driverThreadLocal.get() != null) {
            quitDriver();
        }
    }
}