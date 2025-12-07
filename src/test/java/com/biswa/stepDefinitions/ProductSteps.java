package com.biswa.stepDefinitions;

import com.biswa.pages.CartPage;
import com.biswa.pages.CheckoutPage;
import com.biswa.pages.InventoryPage;
import com.biswa.pages.LoginPage;
import com.biswa.utils.Hooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;

public class ProductSteps {

    private InventoryPage inventoryPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    @Given("User is logged in with {string} and {string}")
    public void userIsLoggedIn(String user, String pass){
        if(Hooks.driver == null){
            throw new RuntimeException("Driver not initialized");
        }
        Hooks.driver.get("https://www.saucedemo.com/");
        LoginPage loginPage = new LoginPage(Hooks.driver);
        loginPage.enterUsername(user);
        loginPage.enterPassword(pass);
        loginPage.clickLogin();
        inventoryPage = new InventoryPage(Hooks.driver);
        Assert.assertTrue(inventoryPage.isOnInventoryPage());
    }

    @When("User adds a product to cart")
    public void addProductToCart(){
        inventoryPage.addFirstProductToCart();
    }

    @And("User opens cart")
    public void openCart(){
        inventoryPage.openCart();
        cartPage = new CartPage(Hooks.driver);
    }

    @Then("Cart has items")
    public void cartHasItems(){
        Assert.assertTrue(cartPage.hasItems());
    }

    @When("User proceeds to checkout with {string},{string},{string}")
    public void checkoutInfo(String f, String l, String zip){
        cartPage.clickCheckout();
        checkoutPage = new CheckoutPage(Hooks.driver);
        checkoutPage.fillInformation(f,l,zip);
    }

    @And("User finishes order")
    public void finishOrder(){
        checkoutPage.finish();
    }

    @Then("Order is completed")
    public void orderCompleted(){
        Assert.assertTrue(checkoutPage.isComplete());
    }

    @Then("Verify page title contains {string}")
    public void verifyTitleContains(String text){
        String title = Hooks.driver.getTitle();
        Assert.assertTrue(title.contains(text));
    }

}
