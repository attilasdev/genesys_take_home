package com.take_home.tests;

import com.take_home.pages.InventoryPage;
import com.take_home.pages.LoginPage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ErrorMessagesTest extends BaseTest {

    @Test
    @DisplayName("Case 2 - Verify error messages and footer text")
    public void testErrorMessagesAndFooter() {
        logStep("Opening login page");
        LoginPage loginPage = new LoginPage();
        loginPage.open();

        logStep("Clicking login button without entering credentials");
        loginPage.clickLoginButton();

        logStep("Validating error message");
        String errorMsg = loginPage.getErrorMessage();
        logValidation("Error message is displayed correctly");
        assertEquals("Epic sadface: Username is required", errorMsg, "Error Message should match.");

        logStep("Logging in with valid credentials");
        loginPage.login("standard_user", "secret_sauce");

        logStep("Checking footer text");
        InventoryPage inventoryPage = new InventoryPage();
        String footerText = inventoryPage.getFooterText();

        logValidation("Footer contains required information");
        assertTrue(footerText.contains("2025"), "Footer should contain the years 2025.");
        assertTrue(footerText.contains("Terms of Service"), "Footer should contain Terms of Service.");
    }
}
