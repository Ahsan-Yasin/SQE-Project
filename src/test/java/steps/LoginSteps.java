package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.InventoryPage;
import pages.LoginPage;

public class LoginSteps {

    private final LoginPage loginPage = new LoginPage();
    private final InventoryPage inventoryPage = new InventoryPage();

    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        loginPage.open();
    }

    @When("I login with username {string} and password {string}")
    public void i_login_with(String username, String password) {
        loginPage.login(username, password);
    }

    @Then("I should be logged in")
    public void i_should_be_logged_in() {
        Assert.assertEquals("Products", inventoryPage.getTitle());
    }

    @Then("I should see a login error message")
    public void i_should_see_error() {
        String err = loginPage.getErrorText();
        Assert.assertTrue(err != null && !err.isEmpty());
    }

    @When("I logout")
    public void i_logout() throws InterruptedException {
        // open menu and click logout
        // perform logout by clicking menu
        try {
            var d = inventoryPage;
            inventoryPage.getTitle();
            var wd = Class.forName("base.BaseTest").getMethod("getDriver").invoke(null);
            var js = wd.getClass(); // used only to avoid unused var
        } catch (Exception ignored) {
        }
        // we will perform click using raw driver since we did not add a separate header page
        try {
            var driverObj = Class.forName("base.BaseTest").getMethod("getDriver").invoke(null);
            var webDriver = (org.openqa.selenium.WebDriver) driverObj;
            webDriver.findElement(org.openqa.selenium.By.id("react-burger-menu-btn")).click();
            Thread.sleep(500);
            webDriver.findElement(org.openqa.selenium.By.id("logout_sidebar_link")).click();
        } catch (Exception e) {
            throw new RuntimeException("Logout failed: " + e.getMessage());
        }
    }

}
