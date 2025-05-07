package com.take_home.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


    public class CartPage extends BasePage {
        @FindBy(id = "checkout") 
        private WebElement checkoutButton;

        public void proceedToCheckout() {
            click(checkoutButton);
        }
    
    }

