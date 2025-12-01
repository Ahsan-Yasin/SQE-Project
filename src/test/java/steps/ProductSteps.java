package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.CartPage;
import pages.ProductsPage;
import utils.DBConnector;
import utils.ConfigReader;

public class ProductSteps {

    private final ProductsPage inventory = new ProductsPage();
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

    @When("I add product from database index {int} to the cart")
    public void add_product_from_db(int index) {
        String url = ConfigReader.get("db.url", "jdbc:mysql://127.0.0.1:3306/demo");
        String user = ConfigReader.get("db.user", "root");
        String pass = ConfigReader.get("db.password", "");
        DBConnector db = new DBConnector(url, user, pass);
        var products = db.readProducts();
        if (products.isEmpty()) {
            throw new RuntimeException("No products available in DB to add");
        }
        if (index < 0 || index >= products.size()) index = 0;
        inventory.addProductToCartByName(products.get(index));
    }

    // Escape parentheses so cucumber expression parser treats "(s)" as literal
    @Then("the cart should contain {int} item\\(s)")
    public void cart_should_contain(int expected) {
        inventory.goToCart();
        // give the page a moment to render cart changes and retry reads for a short period
        int attempts = 0;
        int actual = cart.countItems();
        while (attempts < 5 && actual != expected) {
            try {
                Thread.sleep(250);
            } catch (InterruptedException ignored) {
            }
            actual = cart.countItems();
            attempts++;
        }
        Assert.assertEquals(expected, actual);
    }

    @When("I remove all items from the cart")
    public void remove_all() {
        inventory.goToCart();
        cart.removeAllItems();
    }
 
}
