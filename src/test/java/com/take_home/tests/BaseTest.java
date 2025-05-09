package com.take_home.tests;

import com.take_home.config.ThreadLocalDriverManager;
import com.take_home.utils.LoggingUtils;
import com.take_home.utils.ScreenshotUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ExtendWith({ScreenshotExtension.class, RetryExtension.class})
public class BaseTest {
    protected WebDriver driver;
    protected static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

    protected void logStep(String stepDescription) {
        logger.info("STEP: {}", stepDescription);
    }

    protected void logValidation(String validationDescription) {
        logger.info("VALIDATION: {}", validationDescription);
    }
    

    protected String takeScreenshot(String screenshotName) {
        return ScreenshotUtils.takeScreenshot(driver, screenshotName);
    }

    @BeforeEach
    public void setUp(TestInfo testInfo) {
        LoggingUtils.logTestStart(logger, testInfo.getDisplayName());
        driver = ThreadLocalDriverManager.getDriver();
    }

    @AfterEach
    public void tearDown(TestInfo testInfo) {
        LoggingUtils.logTestEnd(logger, testInfo.getDisplayName());
        ThreadLocalDriverManager.quitDriver();
    }
}
