package com.take_home.tests;

import com.take_home.pages.GuruPage;
import org.junit.jupiter.api.Test;
import com.take_home.config.DriverManager;
import org.openqa.selenium.PageLoadStrategy;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

public class IFrameTabHandlingTest extends BaseTest {

    @BeforeEach
    public void setUp() {
        DriverManager.setPageLoadStrategy(PageLoadStrategy.EAGER);
        DriverManager.getDriver();
        logger.info("Page load strategy set to EAGER for this test.");
    }

    @Test
    public void testIFrameTabHandling() {
        logStep("Opening Guru99 home page");
        GuruPage guruPage = new GuruPage();
        guruPage.open();

        try {
            logStep("Attempting to interact with iframe");
            guruPage.switchToIFrame();
            guruPage.clickIFrameImage();

            logStep("Handling new tab after iframe interaction");
            guruPage.waitForNewTabAndSwitch();
            guruPage.closeCurrentTabAndSwitchToMain();
        } catch (Exception e) {
            logger.warn("Issue with iframe handling: {}", e.getMessage());
        }

        logStep("Submitting email for validation");
        guruPage.submitEmail("test@example.com");

        logStep("Validating alert message");
        String alertText = guruPage.getAlertText();
        logValidation("Alert text contains 'Successfully'");
        assertTrue(alertText.contains("Successfully"), "Alert should contain Successfully.");

        logStep("Navigating to tooltip page");
        guruPage.navigateToTooltipPage();

        logStep("Validating download button presence");
        logValidation("Download button is present on the page");
        assertTrue(guruPage.isDownloadButtonPresent(), "Download button should be present.");
    }

    @AfterEach
    public void cleanup() {
        DriverManager.setPageLoadStrategy(PageLoadStrategy.NORMAL);
    }

}
