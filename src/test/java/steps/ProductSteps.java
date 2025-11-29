package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.CartPage;
import pages.InventoryPage;

public class ProductSteps {

    private final InventoryPage inventory = new InventoryPage();
    private final CartPage cart = new CartPage();

    @Given("I am on the inventory page")
    public void i_am_on_inventory() {
        // rely on previous login
        Assert.assertEquals("Products", inventory.getTitle());
    }

    @When("I add the first product to the cart")
    public void add_first_product() {
        inventory.addFirstProductToCart();
    }

    @When("I add product {string} to the cart")
    public void add_product_by_name(String name) {
        inventory.addProductToCartByName(name);
    }

    @Then("the cart should contain {int} item(s)")
    public void cart_should_contain(int expected) {
        inventory.goToCart();
        Assert.assertEquals(expected, cart.countItems());
    }

    @When("I remove all items from the cart")
    public void remove_all() {
        inventory.goToCart();
        cart.removeAllItems();
    }

}
