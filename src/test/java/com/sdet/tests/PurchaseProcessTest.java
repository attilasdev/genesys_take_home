package com.sdet.tests;

import com.take_home.models.Credentials;
import com.take_home.pages.CartPage;
import com.take_home.pages.CheckoutPage;
import com.take_home.pages.InventoryPage;
import com.take_home.pages.LoginPage;
import com.take_home.utils.JsonUtils;
import org.junit.jupiter.api.Test;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PurchaseProcessTest extends BaseTest {
    
    @Test
    public void testPurchaseProcess() throws IOException {
        String filePath = getClass().getClassLoader().getResource("credentials.json").getPath();
        Credentials credentials = JsonUtils.readJsonFile(filePath, Credentials.class);

        LoginPage loginPage = new LoginPage();
        loginPage.open();
        loginPage.login(credentials.getUsername(), credentials.getPassword());

        InventoryPage inventoryPage = new InventoryPage();
        inventoryPage.addItemToCart("Sauce Labs Backpack");
        inventoryPage.addItemToCart("Sauce Labs Fleece Jacket");

        
    }
}
