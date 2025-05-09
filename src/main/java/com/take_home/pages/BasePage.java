package com.take_home.pages;

import com.take_home.config.ThreadLocalDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected static final Logger logger = LoggerFactory.getLogger(BasePage.class);

    public BasePage() {
        this.driver = ThreadLocalDriverManager.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    protected void waitForElementVisible(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            logger.debug("Element is visible: {}", element);
        } catch (Exception e) {
            logger.error("Element not visible: {}", element, e);
            throw e;
        }
    }
    
    protected void waitForElementClickable(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            logger.debug("Element is clickable: {}", element);
        } catch (Exception e) {
            logger.error("Element not clickable: {}", element, e);
            throw e;
        }
    }

    protected void click(WebElement element) {
        try {
            waitForElementVisible(element);
            element.click();
            logger.debug("Clicked element: {}", element);
        } catch (Exception e) {
            logger.error("Failed to click element: {}", element, e);
            throw e;
        }
    }

    protected void sendKeys(WebElement element, String text) {
        try {
            waitForElementVisible(element);
            element.clear();
            element.sendKeys(text);
            logger.debug("Entered text '{}' into element: {}", text, element);
        } catch (Exception e) {
            logger.error("Failed to enter text '{}' into element: {}", text, element, e);
            throw e;
        }
    }

    protected String getText(WebElement element) {
        try {
            waitForElementVisible(element);
            String text = element.getText();
            logger.debug("Got text '{}' from element: {}", text, element);
            return text;
        } catch (Exception e) {
            logger.error("Failed to get text from element: {}", element, e);
            throw e;
        }
    }

    protected void scrollToElement(WebElement element) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", element);
            logger.debug("Scrolled to element: {}", element);
        } catch (Exception e) {
            logger.error("Failed to scroll to element: {}", element, e);
            throw e;
        }
    }
}