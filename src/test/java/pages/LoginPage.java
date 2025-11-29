package pages;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final WebDriver driver;
    private final String url = "https://www.saucedemo.com";

    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorContainer = By.cssSelector("h3[data-test='error']");

    public LoginPage() {
        this.driver = BaseTest.getDriver();
    }

    public void open() {
        driver.get(url);
    }

    public void login(String username, String password) {
        driver.findElement(usernameField).clear();
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    public String getErrorText() {
        try {
            return driver.findElement(errorContainer).getText();
        } catch (Exception e) {
            return "";
        }
    }
}
