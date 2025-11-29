package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class UISteps {

    @When("I check the page title contains {string}")
    public void page_title_contains(String expected) {
        var driver = base.BaseTest.getDriver();
        String title = driver.getTitle();
        Assert.assertTrue(title.toLowerCase().contains(expected.toLowerCase()));
    }

    @Then("all images should have valid src attributes")
    public void all_images_valid() {
        var driver = base.BaseTest.getDriver();
        var imgs = driver.findElements(org.openqa.selenium.By.tagName("img"));
        boolean ok = true;
        for (var i : imgs) {
            var src = i.getAttribute("src");
            if (src == null || src.trim().isEmpty()) {
                ok = false;
                break;
            }
        }
        Assert.assertTrue(ok);
    }
}
