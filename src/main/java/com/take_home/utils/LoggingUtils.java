package com.take_home.utils;

import org.slf4j.Logger;

public class LoggingUtils {
    public static void logTestStart(Logger logger, String testName) {
        logger.info("==== STARTING TEST: {} ====", testName);
    }
    
    public static void logTestEnd(Logger logger, String testName) {
        logger.info("==== COMPLETED TEST: {} ====", testName);
    }
    
    public static void logStep(Logger logger, String stepDescription) {
        logger.info("STEP: {}", stepDescription);
    }
    
    public static void logValidation(Logger logger, String validationDescription) {
        logger.info("VALIDATION: {}", validationDescription);
    }
}