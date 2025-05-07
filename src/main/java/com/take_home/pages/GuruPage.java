package com.take_home.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.ArrayList;
import java.util.List;

public class GuruPage extends BasePage{
    @FindBy(id = "a077aa5e")
    private WebElement iFrame;

    @FindBy(xpath = "//iframe[@id='a077aa5e']//img")
    private WebElement iFrameImage;
    
    @FindBy(xpath = "//h3[contains(text(), 'Email Submission')]")
    private WebElement emailSubmissionSection;
    
    @FindBy(name = "emailid")
    private WebElement emailField;
    
    @FindBy(name = "btnLogin")
    private WebElement submitButton;
    
    @FindBy(xpath = "//div[@class='nav navbar-nav navbar-right']//a[text()='Selenium']")
    private WebElement seleniumDropdown;
    
    @FindBy(xpath = "//a[contains(text(),'Tooltip')]")
    private WebElement tooltipOption;
    
    @FindBy(xpath = "//a[contains(text(),'Download now')]")
    private WebElement downloadNowButton;


    public void open() {
        driver.get("https://demo.guru99.com/test/guru99home/");
    }

    public void switchToIFrame() {
        driver.switchTo().frame(iFrame);
    }

    public void clickIFrameImage() {
        click(iFrameImage);
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
        driver.close();
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
        driver.switchTo().alert().getText();
        return text;
    }

    public void navigateToTooltipPage() {
        click(seleniumDropdown);
        click(tooltipOption);
    }

    public boolean isDownloadButtonVisible() {
        try {
            return downloadNowButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }    
    }
}
