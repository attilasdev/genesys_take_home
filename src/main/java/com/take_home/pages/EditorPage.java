package com.take_home.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class EditorPage {
    @FindBy(css = ".ql-editor")
    private WebElement editor;

    @FindBy(css = ".ql-bold")
    private WebElement boldButton;

    @FindBy(css = ".ql-underline")
    private WebElement underlineButton;
    
}
