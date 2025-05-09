package com.take_home.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.take_home.config.ConfigReader;


public class LoginPage extends BasePage {
    @FindBy(id = "user-name")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(css = "h3[data-test='error']")
    private WebElement errorMessage;

    public void login(String username, String password) {
        sendKeys(usernameField, username);
        sendKeys(passwordField, password);
        click(loginButton);
    }

    public void clickLoginButton() {
        click(loginButton);
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }

    public void open() {
        String url = ConfigReader.getProperty("base.url.sauce");
        logger.info("Opening URL: {}", url);
        driver.get(url);
    }

    public WebElement getUsernameField() {
        return usernameField;
    }
}   
