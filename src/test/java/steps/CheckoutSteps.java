package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import pages.CartPage;
import pages.InventoryPage;

public class CheckoutSteps {

    private final InventoryPage inventory = new InventoryPage();
    private final CartPage cart = new CartPage();

    @When("I proceed to checkout with first name {string}, last name {string}, postal code {string}")
    public void proceed_to_checkout(String firstName, String lastName, String postalCode) {
        inventory.goToCart();
        cart.clickCheckout();
        var driver = base.BaseTest.getDriver();
        driver.findElement(By.id("first-name")).sendKeys(firstName);
        driver.findElement(By.id("last-name")).sendKeys(lastName);
        driver.findElement(By.id("postal-code")).sendKeys(postalCode);
        driver.findElement(By.id("continue")).click();
    }

    @Then("I finish checkout")
    public void finish_checkout() {
        var driver = base.BaseTest.getDriver();
        driver.findElement(By.id("finish")).click();
        String text = driver.findElement(By.cssSelector("h2.complete-header")).getText();
        Assert.assertTrue(text.toLowerCase().contains("thank"));
    }

    @Then("I should see a checkout error")
    public void see_checkout_error() {
        var driver = base.BaseTest.getDriver();
        String error = driver.findElement(By.cssSelector("h3[data-test='error']")).getText();
        Assert.assertTrue(error != null && !error.isEmpty());
    }
}
