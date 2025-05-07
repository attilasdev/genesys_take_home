package com.take_home.tests;

import com.take_home.config.DriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.WebDriver;
import org.junit.jupiter.api.TestInfo;


public class BaseTest {
    protected WebDriver driver;
    protected static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

    @BeforeEach
    public void setUp(TestInfo testInfo) {
        logger.info("==== STARTING TEST: {} ====", testInfo.getDisplayName());
        DriverManager.getDriver();
    }

    @AfterEach
    public void tearDown(TestInfo testInfo) {
        logger.info("==== COMPLETED TEST: {} ====", testInfo.getDisplayName());
        DriverManager.quitDriver();
    }
}
