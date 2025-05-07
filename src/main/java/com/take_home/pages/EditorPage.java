package com.take_home.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class EditorPage extends BasePage{
    @FindBy(css = ".ql-editor")
    private WebElement editor;

    @FindBy(css = ".ql-bold")
    private WebElement boldButton;

    @FindBy(css = ".ql-underline")
    private WebElement underlineButton;

    public void open() {
        driver.get("https://onlinehtmleditor.dev");
    }

    public void clickBoldButton() {
        click(boldButton);
    }

    public void clickUnderlineButton() {
        clickUnderlineButton();
    }

    public void typeText(String text) {
        click(editor);
        sendKeys(editor, text);
    }

    public String getEditorContent() {
        return (String) ((JavascriptExecutor) driver).executeScript("return document.querySelector('.ql-editor').innerHTML");
    }
}
