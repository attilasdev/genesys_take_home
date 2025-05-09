package com.take_home.tests;

import com.take_home.pages.LoginPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import java.util.Random;

@DisplayName("Retry Functionality Demo")
public class RetryDemoTest extends BaseTest {

    private static int attemptCounter = 0;
    private static final Random random = new Random();

    @Test
    @Retry(2)
    @DisplayName("Demonstrates retry on flaky element")
    public void retryOnFlakyElement() {
        attemptCounter++;

        logStep("Opening login page");
        LoginPage loginPage = new LoginPage();
        loginPage.open();

        logStep("Attempting to use a flaky element (attempt " + attemptCounter + ")");
        if (attemptCounter == 1) {
            takeScreenshot("before-failure");
            logger.info("Simulating a StaleElementReferenceException on first attempt");
            throw new StaleElementReferenceException("Simulated stale element exception");
        }

        loginPage.login("standard_user", "secret_sauce");
        takeScreenshot("successful-retry");

        logger.info("Test succeeded on attempt {}", attemptCounter);
        attemptCounter = 0;
    }

    @Test
    @Retry(3)
    @DisplayName("Demonstrates multiple retry attempts")
    public void multipleRetryTest() {
        attemptCounter++;

        logStep("Starting potentially flaky test (attempt " + attemptCounter + ")");
        takeScreenshot("retry-attempt-" + attemptCounter);

        if (attemptCounter < 3 && random.nextInt(10) > 3) {
            String[] exceptions = {
                    "Element not found",
                    "Element not clickable",
                    "Timeout waiting for element",
                    "Network connection error"
            };
            String errorMsg = exceptions[random.nextInt(exceptions.length)];

            logger.info("Simulating random failure: {}", errorMsg);
            if (random.nextBoolean()) {
                throw new NoSuchElementException(errorMsg);
            } else {
                throw new RuntimeException(errorMsg);
            }
        }

        logStep("Test step completed successfully after " + attemptCounter + " attempts");
        takeScreenshot("successful-completion");

        attemptCounter = 0;
    }
}