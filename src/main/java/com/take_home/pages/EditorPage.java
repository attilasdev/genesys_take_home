package com.take_home.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.take_home.config.ConfigReader;


public class EditorPage extends BasePage{
    @FindBy(xpath = "//button[contains(@data-cke-tooltip-text, 'Bold')]")
    private WebElement boldButton;

    @FindBy(xpath = "//button[contains(@data-cke-tooltip-text, 'Underline')]")
    private WebElement underlineButton;

    public WebElement getExactEditableArea() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (WebElement) js.executeScript(
            "return document.querySelector('.ck-editor__editable[contenteditable=true]');"
        );
    }

    public void open() {
        String url = ConfigReader.getProperty("base.url.editor");
        logger.info("Opening URL: {}", url);
        driver.get(url);
    }

    public void clickBoldButton() {
        waitForElementClickable(boldButton);
        click(boldButton);  
    }

    public void clickUnderlineButton() {
        waitForElementClickable(underlineButton);
        click(underlineButton);
    }

    public void typeText(String text) {
        WebElement exactEditor = getExactEditableArea();
        exactEditor.click();
        exactEditor.sendKeys(text);
    }

    public String getEditorContent() {
        return (String) ((JavascriptExecutor) driver).executeScript("return document.querySelector('.ck-editor__editable').innerHTML");
    }

}
