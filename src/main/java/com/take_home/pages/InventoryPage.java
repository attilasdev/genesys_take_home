package com.take_home.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InventoryPage extends BasePage {
    @FindBy(id = "shopping_cart_container")
    private WebElement cartBadge;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartLink;

    @FindBy(css = ".footer")
    private WebElement footer;

    public void addItemToCart(String itemName) {
        String xpath = String.format("//div[text()='%s']/ancestor::div[@class='inventory_item']//button", itemName);
        WebElement addButton = driver.findElement(By.xpath(xpath));
        click(addButton);
    }

    public void goToCart() {
        click(cartLink);
    }

    public int getCartItemCount() {
        return Integer.parseInt(getText(cartBadge));
    }

    public String getFooterText() {
        scrollToElement(footer);
        return getText(footer);
    }
}
