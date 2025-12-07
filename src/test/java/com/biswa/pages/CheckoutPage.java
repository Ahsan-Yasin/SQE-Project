package com.biswa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CheckoutPage {

    WebDriver driver;

    private By firstName = By.id("first-name");
    private By lastName = By.id("last-name");
    private By postalCode = By.id("postal-code");
    private By continueButton = By.id("continue");
    private By finishButton = By.xpath("//button[contains(@class, 'finish')]");
    private By completeHeader = By.xpath("//h2[contains(@class, 'complete-header')]");

    public CheckoutPage(WebDriver driver){
        this.driver = driver;
    }

    public void fillInformation(String fName, String lName, String zip){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(firstName)).sendKeys(fName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(lastName)).sendKeys(lName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(postalCode)).sendKeys(zip);
        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
    }

    public void finish(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(finishButton)).click();
    }

    public boolean isComplete(){
        return driver.findElements(completeHeader).size() > 0;
    }

}
