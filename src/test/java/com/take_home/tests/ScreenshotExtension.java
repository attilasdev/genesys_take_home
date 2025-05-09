package com.take_home.tests;

import com.take_home.config.ThreadLocalDriverManager;
import com.take_home.utils.ScreenshotUtils;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScreenshotExtension implements TestExecutionExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(ScreenshotExtension.class);

    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
        logger.info("Test failed: {}. Taking screenshot...", context.getDisplayName());
        
        String screenshotPath = ScreenshotUtils.takeScreenshot(
                ThreadLocalDriverManager.getDriver(), 
                context.getTestMethod().orElseThrow().getName());
        
        if (screenshotPath != null) {
            logger.info("Screenshot saved for failed test at: {}", screenshotPath);
        } else {
            logger.warn("Failed to capture screenshot for test: {}", context.getDisplayName());
        }
        
        
        throw throwable;
    }
} 