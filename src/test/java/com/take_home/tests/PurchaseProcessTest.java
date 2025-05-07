package com.take_home.tests;

import com.take_home.models.Credentials;
import com.take_home.pages.CartPage;
import com.take_home.pages.CheckoutPage;
import com.take_home.pages.InventoryPage;
import com.take_home.pages.LoginPage;
import com.take_home.utils.JsonUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PurchaseProcessTest extends BaseTest {

    @Test
    @DisplayName("Case 1 - Automate Purchase Process")
    public void testPurchaseProcess() throws IOException {
        logStep("Reading credentials from JSON file");
        String filePath = getClass().getClassLoader().getResource("credentials.json").getPath();
        Credentials credentials = JsonUtils.readJsonFile(filePath, Credentials.class);

        logStep("Opening login page and logging in");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        loginPage.login(credentials.getUsername(), credentials.getPassword());

        logStep("Adding items to cart");
        InventoryPage inventoryPage = new InventoryPage();
        inventoryPage.addItemToCart("Sauce Labs Backpack");
        inventoryPage.addItemToCart("Sauce Labs Fleece Jacket");

        logStep("Validating cart item count");
        logValidation("Cart contains exactly 2 items");
        assertEquals(2, inventoryPage.getCartItemCount(), "Cart should contain 2 items.");

        logStep("Proceeding to checkout");
        inventoryPage.goToCart();
        CartPage cartPage = new CartPage();
        cartPage.proceedToCheckout();

        logStep("Completing checkout process");
        CheckoutPage checkoutPage = new CheckoutPage();
        checkoutPage.fillCheckoutInfo("firstName", "lastName", "1234");
        checkoutPage.clickFinish();

        logStep("Validating order confirmation");
        String confirmationMsg = checkoutPage.getConfirmationMessage();
        logValidation("Order confirmation message is correct");
        assertEquals("Thank you for your order!", confirmationMsg, "Order confirmation message should match.");
    }
}
