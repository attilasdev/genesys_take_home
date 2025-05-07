package com.take_home.tests;

import com.take_home.config.DriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.WebDriver;
import org.junit.jupiter.api.TestInfo;
import com.take_home.utils.LoggingUtils;

public class BaseTest {
    protected WebDriver driver;
    protected static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

    protected void logStep(String stepDescription) {
        logger.info("STEP: {}", stepDescription);
    }

    protected void logValidation(String validationDescription) {
        logger.info("VALIDATION: {}", validationDescription);
    }

    @BeforeEach
    public void setUp(TestInfo testInfo) {
        LoggingUtils.logTestStart(logger, testInfo.getDisplayName());
        driver = DriverManager.getDriver();
    }

    @AfterEach
    public void tearDown(TestInfo testInfo) {
        LoggingUtils.logTestEnd(logger, testInfo.getDisplayName());
        DriverManager.quitDriver();
    }
}
