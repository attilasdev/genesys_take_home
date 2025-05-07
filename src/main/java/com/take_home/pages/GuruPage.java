package com.take_home.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import com.take_home.config.ConfigReader;

public class GuruPage extends BasePage{
    private static final Logger logger = LoggerFactory.getLogger(GuruPage.class);

    @FindBy(id = "a077aa5e")
    private WebElement iFrame;

    @FindBy(xpath = "//img")
    private WebElement iFrameImage;
    
    @FindBy(xpath = "//h3[contains(text(), 'Email Submission')]")
    private WebElement emailSubmissionSection;
    
    @FindBy(id = "philadelphia-field-email")
    private WebElement emailField;
    
    @FindBy(id = "philadelphia-field-submit")
    private WebElement submitButton;
    
    @FindBy(xpath = "//a[contains(text(),'Selenium') and contains(@class,'dropdown-toggle')]")
    private WebElement seleniumDropdown;
    
    @FindBy(xpath = "//a[contains(text(),'Tooltip')]")
    private WebElement tooltipOption;
    
    @FindBy(id = "download_now")
    private WebElement downloadNowButton;


    public void open() {
        String url = ConfigReader.getProperty("base.url.guru");
        logger.info("Opening URL: {}", url);
        driver.get(url);
    }

    public void switchToIFrame() {
        try {
            waitForElementVisible(iFrame);
            driver.switchTo().frame(iFrame);
            logger.info("Switched to iframe by ID");
        } catch (Exception e1) {
            try {
                waitForElementVisible(iFrame);
                driver.switchTo().frame(iFrame);
                logger.info("Switched to first iframe");
            } catch (Exception e2) {
                logger.error("Could not switch to iframe: " + e2.getMessage());
                throw e2;
            }
        }
    }

    public void clickIFrameImage() {
        try {
            Thread.sleep(500);
            
            if (iFrameImage.isDisplayed()) {
                click(iFrameImage);
                logger.info("Clicked iframe image using @FindBy locator");
                return;
            }
        } catch (Exception e) {
            try {
                WebElement img = driver.findElement(By.tagName("img"));
                click(img);
                logger.info("Clicked iframe image using direct locator");
            } catch (Exception e2) {
                logger.error("Failed to click image in iframe: " + e2.getMessage());
                throw e2;
            }
        } finally {
            driver.switchTo().defaultContent();
        }
    }

    public void waitForNewTabAndSwitch() {
        String originalHandle = driver.getWindowHandle();
        
        // Wait for new tab to open (up to 5 seconds)
        new WebDriverWait(driver, Duration.ofSeconds(5))
            .until(d -> d.getWindowHandles().size() > 1);
        
        // Switch to the new tab
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(originalHandle)) {
                driver.switchTo().window(handle);
                logger.info("Switched to new tab");
                return;
            }
        }
    }

    public void closeCurrentTabAndSwitchToMain() {
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        logger.info("Number of tabs open: {}", tabs.size());
        
        if (tabs.size() > 1) {
            driver.close(); // Close current tab
            driver.switchTo().window(tabs.get(0)); // Switch to main tab
            logger.info("Closed tab and switched to main window");
        } else {
            logger.info("Only one tab is open, no need to close or switch");
        }
    }

    public void switchToMainWindow() {
        driver.switchTo().defaultContent();
    }

    public void switchToMainTab() {
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));
    }


    public void submitEmail(String email) {
        scrollToElement(emailSubmissionSection);
        sendKeys(emailField, email);
        click(submitButton);
    }

    public String getAlertText() {
        wait.until(ExpectedConditions.alertIsPresent());
        String text = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        return text;
    }

    public void navigateToTooltipPage() {
        click(seleniumDropdown);
        click(tooltipOption);
    }

    public boolean isDownloadButtonPresent() {
        try {
            waitForElementVisible(downloadNowButton);
            return downloadNowButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }    
    }
}
