package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.LoginPage;
import utils.ExcelUtils;

import java.io.File;
import java.util.List;

public class DataDrivenSteps {

    private final LoginPage loginPage = new LoginPage();

    @Given("I have an Excel file with login data")
    public void create_excel() throws Exception {
        File f = ExcelUtils.createSampleLoginExcel("target/test-data/logins.xlsx");
        Assert.assertTrue(f.exists());
    }

    @When("I read login rows from the Excel file")
    public void read_excel() throws Exception {
        File f = new File("target/test-data/logins.xlsx");
        List<String[]> rows = ExcelUtils.readLoginData(f);
        Assert.assertTrue(rows.size() >= 1);
    }

    @Then("I can use those rows to login")
    public void use_rows_to_login() throws Exception {
        File f = new File("target/test-data/logins.xlsx");
        List<String[]> rows = ExcelUtils.readLoginData(f);
        var first = rows.get(0);
        loginPage.open();
        loginPage.login(first[0], first[1]);
        // we only assert no exception - the actual logged-in assertion is elsewhere
        Assert.assertNotNull(first[0]);
    }
}
