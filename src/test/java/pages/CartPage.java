package pages;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

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
        // Attempt to remove all items reliably with retry + JS fallback for clicks
        var wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        long start = System.currentTimeMillis();
        while (driver.findElements(cartItems).size() > 0 && (System.currentTimeMillis() - start) < 15000) {
            var removes = driver.findElements(removeButtons);
            if (!removes.isEmpty()) {
                try {
                    wait.until(ExpectedConditions.elementToBeClickable(removes.get(0))).click();
                } catch (Exception ex) {
                    // fallback to JS click if normal click fails (overlay/intercept)
                    try {
                        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", removes.get(0));
                    } catch (Exception ignored) {}
                }
            }
            try { Thread.sleep(300); } catch (InterruptedException ignored) {}
        }
        // Final wait for cart to be empty
        long endWait = System.currentTimeMillis() + 2000;
        while (driver.findElements(cartItems).size() > 0 && System.currentTimeMillis() < endWait) {
            try { Thread.sleep(200); } catch (InterruptedException ignored) {}
        }
    }

    public void clickCheckout() {
        // wait for the checkout button to be present and clickable
        var wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));
        driver.findElement(checkoutButton).click();
        // wait for the checkout page to load (first-name field) so next steps can interact reliably
        try {
            var wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait2.until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOfElementLocated(By.id("first-name")),
                    ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".checkout_info"))
            ));
        } catch (Exception ignored) {}
    }
}
