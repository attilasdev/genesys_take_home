package com.take_home.tests;

import com.take_home.config.ConfigReader;
import com.take_home.pages.LoginPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Screenshot Feature Demo")
public class ScreenshotDemoTest extends BaseTest {

    @Test
    @DisplayName("Demonstrates manual and automatic screenshots")
    public void screenshotDemo() {
        logStep("Opening login page");
        driver.get(ConfigReader.getProperty("app.url"));
        
        takeScreenshot("initial-page");
        
        logStep("Taking a screenshot before element interaction");
        takeScreenshot("before-interaction");
        
        logStep("Demonstrating element interaction with screenshot");
        LoginPage loginPage = new LoginPage();
        WebElement usernameField = loginPage.getUsernameField();
        usernameField.sendKeys("standard_user");

        takeScreenshot("after-username-entry");
        

        if (System.getProperty("DEMO_FAILURE") != null) {
            logStep("Demonstrating automatic screenshot on failure");
            fail("This is an intentional failure to demonstrate automatic screenshots");
        }
        
        logStep("Test completed successfully");
    }
    
    @Test
    @DisplayName("Screenshot Demo With Page Navigation")
    public void screenshotWithNavigationDemo() {
        logStep("Opening inventory page directly");
        driver.get(ConfigReader.getProperty("app.url") + "/inventory.html");
        

        takeScreenshot("inventory-page");
        
        logStep("Interacting with inventory items");
        WebElement addToCartBtn = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        addToCartBtn.click();
        

        takeScreenshot("after-add-to-cart");
        
        logStep("Navigating to cart");
        WebElement cartIcon = driver.findElement(By.className("shopping_cart_link"));
        cartIcon.click();
        

        takeScreenshot("cart-page");
        
        logStep("Demonstrating test validation");
        WebElement cartItem = driver.findElement(By.className("cart_item"));
        assertTrue(cartItem.isDisplayed(), "Cart item should be visible");
    }
} 