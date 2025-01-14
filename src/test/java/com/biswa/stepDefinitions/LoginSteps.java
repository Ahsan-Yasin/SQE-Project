package com.biswa.stepDefinitions;

import com.biswa.pages.LoginPage;
import com.biswa.utils.ExtentReportManager;
import com.biswa.utils.Hooks;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;


public class LoginSteps {

    private WebDriver driver = new ChromeDriver();
    private LoginPage loginPage;



    @Given("Navigate to Login Page")
    public void navigateToLoginPage() throws PendingException {
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);


    }

    @When("User enters username {string} and password {string}")
    public void enterCredentials(String username, String password) throws PendingException{
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);

    }

    @And("User clicks login")
    public void clickLogin() throws PendingException {
        loginPage.clickLogin();

    }

    @Then("User sees product catalog page")
    public void userSeesProductCatalogPage() throws PendingException {
        String current = driver.getCurrentUrl();
        String expected = "https://www.saucedemo.com/inventory.html";
        Assert.assertEquals(expected, current);
        driver.quit();
    }


}
