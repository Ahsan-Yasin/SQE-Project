package pages;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductsPage {
    private final WebDriver driver;

    private final By inventoryTitle = By.cssSelector("span.title");
    private final By productItems = By.cssSelector("div.inventory_item");
    private final By productName = By.cssSelector("div.inventory_item_name");
    private final By addToCartButton = By.cssSelector("button.btn_inventory");
    private final By shoppingCart = By.cssSelector("a.shopping_cart_link");
    private final By cartBadge = By.cssSelector("span.shopping_cart_badge");

    public ProductsPage() {
        this.driver = BaseTest.getDriver();
    }

    public String getTitle() {
        return driver.findElement(inventoryTitle).getText();
    }

    public int productCount() {
        List<WebElement> items = driver.findElements(productItems);
        return items.size();
    }

    private int getCartBadgeCount() {
        try {
            var el = driver.findElement(cartBadge);
            String t = el.getText().trim();
            return Integer.parseInt(t);
        } catch (Exception e) {
            return 0;
        }
    }

    public void addFirstProductToCart() {
        // wait for first product's add button to be clickable then click
        var wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(5));
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(addToCartButton));
        var btn = driver.findElement(addToCartButton);
        try {
            btn.click();
        } catch (Exception e) {
            // fallback: try JS click
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        }
        // brief wait to let UI update
        try { Thread.sleep(200); } catch (InterruptedException ignored) {}
    }

    public void addProductToCartByName(String name) {
        // wait for items to be present
        var wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(7));
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions.presenceOfAllElementsLocatedBy(productItems));
        List<WebElement> items = driver.findElements(productItems);
        String normalized = name == null ? "" : name.trim().toLowerCase();
        for (WebElement i : items) {
            String text = i.findElement(productName).getText();
            if (text != null && text.trim().toLowerCase().contains(normalized)) {
                WebElement btn = i.findElement(addToCartButton);
                // wait until clickable then click
                wait.until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(btn));
                try {
                    btn.click();
                } catch (Exception e) {
                    ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
                }

                // give the UI a moment to update and check cart badge
                int attempts = 0;
                while (attempts < 6 && getCartBadgeCount() == 0) {
                    try { Thread.sleep(200); } catch (InterruptedException ignored) {}
                    attempts++;
                }
                return;
            }
        }
        throw new RuntimeException("Product not found: " + name);
    }

    public void goToCart() {
        driver.findElement(shoppingCart).click();
    }
}
