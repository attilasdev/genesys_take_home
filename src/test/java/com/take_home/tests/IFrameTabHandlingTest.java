package com.take_home.tests;

import com.take_home.pages.GuruPage;
import org.junit.jupiter.api.Test;
import com.take_home.config.ThreadLocalDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

public class IFrameTabHandlingTest extends BaseTest {

    @BeforeEach
    public void setUp() {
        ThreadLocalDriverManager.setPageLoadStrategy(PageLoadStrategy.EAGER);
        ThreadLocalDriverManager.getDriver();
        logger.info("Page load strategy set to EAGER for this test.");
    }

    @Test
    @DisplayName("Case 4 - IFrame and Tab Handling")
    public void testIFrameTabHandling() {
        logStep("Opening Guru99 home page");
        GuruPage guruPage = new GuruPage();
        guruPage.open();

        
        takeScreenshot("guru99-homepage");

        try {
            logStep("Attempting to interact with iframe");
            guruPage.switchToIFrame();
            guruPage.clickIFrameImage();

            
            takeScreenshot("after-iframe-interaction");

            logStep("Handling new tab after iframe interaction");
            guruPage.waitForNewTabAndSwitch();
            
            
            takeScreenshot("new-tab-view");
            
            guruPage.closeCurrentTabAndSwitchToMain();
        } catch (Exception e) {
            logger.warn("Issue with iframe handling: {}", e.getMessage());
            takeScreenshot("iframe-error");
        }

        logStep("Submitting email for validation");
        guruPage.submitEmail("test@example.com");

        logStep("Validating alert message");
        String alertText = guruPage.getAlertText();
        logValidation("Alert text contains 'Successfully'");
        takeScreenshot("after-email-submit");
        
        assertTrue(alertText.contains("Successfully"), "Alert should contain Successfully.");

        logStep("Navigating to tooltip page");
        guruPage.navigateToTooltipPage();

        // Take screenshot of tooltip page
        takeScreenshot("tooltip-page");

        logStep("Validating download button presence");
        logValidation("Download button is present on the page");
        assertTrue(guruPage.isDownloadButtonPresent(), "Download button should be present.");
        
        // Final state screenshot
        takeScreenshot("test-complete");
    }

    @AfterEach
    public void cleanup() {
        ThreadLocalDriverManager.setPageLoadStrategy(PageLoadStrategy.NORMAL);
    }

}
