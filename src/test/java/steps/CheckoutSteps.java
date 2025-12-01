package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import pages.CartPage;
import pages.ProductsPage;
import pages.CheckoutPage;

public class CheckoutSteps {

    private final ProductsPage inventory = new ProductsPage();
    private final CartPage cart = new CartPage();
    private final CheckoutPage checkout = new CheckoutPage();

    @When("I proceed to checkout with first name {string}, last name {string}, postal code {string}")
    public void proceed_to_checkout(String firstName, String lastName, String postalCode) {
        inventory.goToCart();
        cart.clickCheckout();
        checkout.fillCustomer(firstName, lastName, postalCode);
        checkout.clickContinue();
    }

    @Then("I finish checkout")
    public void finish_checkout() {
        checkout.clickFinish();
        String text = checkout.getCompleteHeader();
        Assert.assertTrue(text.toLowerCase().contains("thank"));
    }

    @Then("I should see a checkout error")
    public void see_checkout_error() {
        var driver = base.BaseTest.getDriver();
        var wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(3));
        try {
            wait.until(d -> d.findElements(By.cssSelector("h3[data-test='error']")).size() > 0
                    || d.findElements(By.cssSelector(".error-message-container h3")).size() > 0);
        } catch (org.openqa.selenium.TimeoutException toe) {
            Assert.fail("Checkout error message not found after waiting — page may have changed layout or flow failed.");
            return;
        }

        String error = "";
        if (driver.findElements(By.cssSelector("h3[data-test='error']")).size() > 0) {
            error = driver.findElement(By.cssSelector("h3[data-test='error']")).getText();
        } else if (driver.findElements(By.cssSelector(".error-message-container h3")).size() > 0) {
            error = driver.findElement(By.cssSelector(".error-message-container h3")).getText();
        }

        Assert.assertTrue(error != null && !error.isEmpty());
    }
}
