package pages;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    private final WebDriver driver;

    private final By cartItems = By.cssSelector("div.cart_item");
    private final By removeButtons = By.cssSelector("button.cart_button");
    private final By checkoutButton = By.id("checkout");

    public CartPage() {
        this.driver = BaseTest.getDriver();
    }

    public int countItems() {
        return driver.findElements(cartItems).size();
    }

    public void removeAllItems() {
        driver.findElements(removeButtons).forEach(b -> b.click());
    }

    public void clickCheckout() {
        driver.findElement(checkoutButton).click();
    }
}
