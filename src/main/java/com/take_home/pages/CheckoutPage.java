package com.take_home.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends BasePage {
    @FindBy(id = "first-name")
    private WebElement firstNameField;

    @FindBy(id = "last-name")
    private WebElement lastNameField;

    @FindBy(id = "postal-code")
    private WebElement postalCodeField;

    @FindBy(id = "continue")
    private WebElement continueButton;

    @FindBy(id = "finish")
    private WebElement finishButton;

    @FindBy(className = "complete-header")
    private WebElement confirmationMessage;

    public void fillCheckoutInfo(String firstName, String lastName, String postalCode) {
        sendKeys(firstNameField, firstName);
        sendKeys(lastNameField, lastName);
        sendKeys(postalCodeField, postalCode);
        click(continueButton);
    }


}
