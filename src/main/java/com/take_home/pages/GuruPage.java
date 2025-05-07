package com.take_home.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.By;


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
        driver.get("https://demo.guru99.com/test/guru99home/");
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

    public void switchToMainWindow() {
        driver.switchTo().defaultContent();
    }

    public void switchToMainTab() {
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));
    }

    public void closeCurrentTabAndSwitchToMain() {
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        if (tabs.size() > 1) {
            driver.close();
            driver.switchTo().window(tabs.get(0));
        }  
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
