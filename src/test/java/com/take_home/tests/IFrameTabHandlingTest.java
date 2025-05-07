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
    }
    
    @Test
    public void testIFrameTabHandling() {
        GuruPage guruPage = new GuruPage();
        guruPage.open();

        guruPage.switchToIFrame();
        guruPage.clickIFrameImage();
        guruPage.switchToMainWindow();
        guruPage.switchToMainTab();

        guruPage.submitEmail("test@example.com");

        String alertText = guruPage.getAlertText();
        assertTrue(alertText.contains("Successfully"), "Alert should contain Successfully");

        guruPage.navigateToTooltipPage();

        assertTrue(guruPage.isDownloadButtonPresent(), "Download button should be present");   
    }

    @AfterEach
    public void cleanup() {
        DriverManager.setPageLoadStrategy(PageLoadStrategy.NORMAL);
    }
    
    
}
