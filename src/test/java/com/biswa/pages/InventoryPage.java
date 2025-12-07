package com.biswa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class InventoryPage {

    WebDriver driver;
    private By firstAddToCartButton = By.cssSelector(".inventory_item:first-of-type button.btn_inventory");
    private By cartIcon = By.cssSelector("a.shopping_cart_link");

    public InventoryPage(WebDriver driver){
        this.driver = driver;
    }

    public boolean isOnInventoryPage(){
        return driver.getCurrentUrl().contains("inventory");
    }

    public void addFirstProductToCart(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(firstAddToCartButton)).click();
    }

    public void openCart(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(cartIcon)).click();
    }

}
