package hooks;

import base.BaseTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.ByteArrayInputStream;

public class Hooks {

    @Before
    public void beforeScenario() {
        BaseTest.createDriver();
    }

    @After
    public void afterScenario(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                byte[] screenshot = ((TakesScreenshot) BaseTest.getDriver()).getScreenshotAs(OutputType.BYTES);
                Allure.addAttachment("screenshot-" + scenario.getName(), new ByteArrayInputStream(screenshot));
            }
        } catch (Exception e) {
            // ignore
        } finally {
            BaseTest.quitDriver();
        }
    }
}
