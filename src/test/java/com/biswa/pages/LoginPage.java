package com.biswa.pages;

import io.cucumber.java.PendingException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    WebDriver driver;

    private By usernameField = By.id("user-name");
    private By passwordField = By.cssSelector("#password");
    private By loginButton = By.className("submit-button");

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    public void enterUsername(String username) throws PendingException {
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) throws PendingException {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLogin() throws PendingException{
        driver.findElement(loginButton).click();
    }

}
