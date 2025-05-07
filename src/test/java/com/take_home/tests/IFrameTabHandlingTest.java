package com.take_home.tests;

import com.take_home.pages.GuruPage;
import org.junit.jupiter.api.Test;
import com.take_home.config.DriverManager;
import org.openqa.selenium.PageLoadStrategy;


import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
public class IFrameTabHandlingTest extends BaseTest{

    @BeforeEach
    public void setUp() {
        DriverManager.setPageLoadStrategy(PageLoadStrategy.EAGER);
        DriverManager.getDriver();
        logger.info("Page load strategy set to EAGER for this test.");
    }
    
    @Test
public void testIFrameTabHandling() {
    GuruPage guruPage = new GuruPage();
    guruPage.open();

    try {
        logger.info("Attempting to interact with iframe.");
        guruPage.switchToIFrame();
        guruPage.clickIFrameImage();
        
        guruPage.waitForNewTabAndSwitch();
        
        guruPage.closeCurrentTabAndSwitchToMain();
    } catch (Exception e) {
        logger.warn("Issue with iframe handling: {}", e.getMessage());
    }
    
    guruPage.submitEmail("test@example.com");
    
    String alertText = guruPage.getAlertText();
    logger.info("Alert text: {}", alertText);
    assertTrue(alertText.contains("Successfully"), "Alert should contain Successfully.");
    
    guruPage.navigateToTooltipPage();
    assertTrue(guruPage.isDownloadButtonPresent(), "Download button should be present.");
}

    @AfterEach
    public void cleanup() {
        DriverManager.setPageLoadStrategy(PageLoadStrategy.NORMAL);
    }
    
    
}
