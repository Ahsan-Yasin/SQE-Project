package pages;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class InventoryPage {
    private final WebDriver driver;

    private final By inventoryTitle = By.cssSelector("span.title");
    private final By productItems = By.cssSelector("div.inventory_item");
    private final By productName = By.cssSelector("div.inventory_item_name");
    private final By addToCartButton = By.cssSelector("button.btn_inventory");
    private final By shoppingCart = By.cssSelector("a.shopping_cart_link");

    public InventoryPage() {
        this.driver = BaseTest.getDriver();
    }

    public String getTitle() {
        return driver.findElement(inventoryTitle).getText();
    }

    public int productCount() {
        List<WebElement> items = driver.findElements(productItems);
        return items.size();
    }

    public void addFirstProductToCart() {
        WebElement btn = driver.findElement(addToCartButton);
        btn.click();
    }

    public void addProductToCartByName(String name) {
        List<WebElement> items = driver.findElements(productItems);
        for (WebElement i : items) {
            if (i.findElement(productName).getText().equalsIgnoreCase(name)) {
                i.findElement(addToCartButton).click();
                return;
            }
        }
        throw new RuntimeException("Product not found: " + name);
    }

    public void goToCart() {
        driver.findElement(shoppingCart).click();
    }
}
