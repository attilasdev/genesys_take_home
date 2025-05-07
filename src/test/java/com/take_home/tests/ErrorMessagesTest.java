package com.take_home.tests;

import com.take_home.pages.InventoryPage;
import com.take_home.pages.LoginPage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ErrorMessagesTest extends BaseTest{
    
    @Test
    public void testErrorMessagesAndFooter() {

        LoginPage loginPage = new LoginPage();
        loginPage.open();

        loginPage.clickLoginButton();

        String errorMsg = loginPage.getErrorMessage();
        assertEquals("Epic sadface: Username is required", errorMsg, "Error Message should match.");
        
        loginPage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage();
        String footerText =  inventoryPage.getFooterText();

        assertTrue(footerText.contains("2025"), "Footer should contain the years 2025.");
        assertTrue(footerText.contains("Terms of Service"), "Footer should contain Terms of Service.");
    }
}
