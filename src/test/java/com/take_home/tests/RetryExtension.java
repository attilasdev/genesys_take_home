package com.take_home.tests;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class RetryExtension implements TestExecutionExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(RetryExtension.class);
    private static final int MAX_RETRIES = 2; // Max number of retries

    private final Map<String, Integer> retryCounters = new HashMap<>();

    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
        Method testMethod = context.getRequiredTestMethod();
        String testId = getTestId(context);

        Retry retry = testMethod.getAnnotation(Retry.class);
        if (retry == null) {

            throw throwable;
        }

        int maxRetries = retry.value() > 0 ? retry.value() : MAX_RETRIES;

        int currentRetry = retryCounters.getOrDefault(testId, 0);

        if (currentRetry < maxRetries) {

            currentRetry++;
            retryCounters.put(testId, currentRetry);

            logger.warn("Test '{}' failed on attempt {}/{}. Retrying...",
                    context.getDisplayName(), currentRetry, maxRetries);
            logger.warn("Failure reason: {}", throwable.getMessage());

            return;
        }

        logger.error("Test '{}' failed after {} retries", context.getDisplayName(), maxRetries);
        retryCounters.remove(testId);
        throw throwable;
    }

    private String getTestId(ExtensionContext context) {
        return context.getRequiredTestClass().getName() + "#" + context.getRequiredTestMethod().getName();
    }
}