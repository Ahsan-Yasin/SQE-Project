package pages;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {
    private final WebDriver driver;

    private final By firstName = By.id("first-name");
    private final By lastName = By.id("last-name");
    private final By postalCode = By.id("postal-code");
    private final By continueBtn = By.id("continue");
    private final By finishBtn = By.id("finish");
    private final By completeHeader = By.cssSelector("h2.complete-header");

    public CheckoutPage() {
        this.driver = BaseTest.getDriver();
    }

    public void fillCustomer(String fn, String ln, String zip) {
        // wait for the first-name field to be visible before interacting
        var wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstName));
        driver.findElement(firstName).clear();
        driver.findElement(firstName).sendKeys(fn);
        driver.findElement(lastName).clear();
        driver.findElement(lastName).sendKeys(ln);
        driver.findElement(postalCode).clear();
        driver.findElement(postalCode).sendKeys(zip);
    }

    public void clickContinue() {
        var wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(continueBtn));
        driver.findElement(continueBtn).click();
        // wait until either the finish button or an error is visible so subsequent steps can proceed
        wait.until(d -> d.findElements(finishBtn).size() > 0 || d.findElements(By.cssSelector("h3[data-test='error']")).size() > 0);
    }

    public void clickFinish() {
        var wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(finishBtn));
        driver.findElement(finishBtn).click();
        // wait briefly for completion header to appear
        try {
            var wait2 = new WebDriverWait(driver, Duration.ofSeconds(8));
            wait2.until(ExpectedConditions.visibilityOfElementLocated(completeHeader));
        } catch (Exception ignored) {}
    }

    public String getCompleteHeader() {
        try {
            return driver.findElement(completeHeader).getText();
        } catch (Exception e) {
            return "";
        }
    }
}
