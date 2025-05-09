package com.take_home.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtils {
    private static final Logger logger = LoggerFactory.getLogger(ScreenshotUtils.class);
    private static final String SCREENSHOT_DIR = "screenshots";

    static {
        
        try {
            Path screenshotPath = Paths.get(SCREENSHOT_DIR);
            if (!Files.exists(screenshotPath)) {
                Files.createDirectories(screenshotPath);
                logger.info("Created screenshots directory at: {}", screenshotPath.toAbsolutePath());
            }
        } catch (IOException e) {
            logger.error("Failed to create screenshots directory", e);
        }
    }

    
    public static String takeScreenshot(WebDriver driver, String testName) {
        if (driver == null) {
            logger.error("WebDriver is null, cannot take screenshot");
            return null;
        }
        
        try {
           
            String timestamp = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());
            String filename = String.format("%s/%s_%s.png", SCREENSHOT_DIR, testName, timestamp);
            
           
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.copy(screenshot.toPath(), Paths.get(filename));
            
            logger.info("Screenshot saved to: {}", filename);
            return filename;
        } catch (Exception e) {
            logger.error("Failed to take screenshot", e);
            return null;
        }
    }
} 