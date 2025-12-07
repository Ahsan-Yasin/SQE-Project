package com.biswa.utils;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.io.File;

public class Hooks {

    public static WebDriver driver;

    @Before
    public void setUp() throws Exception{
        ExtentReportManager.setExtent();

        // initialize browser
        String browser = System.getProperty("browser", "chrome");
        if(browser.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @After
    public void tearDown(Scenario scenario) throws Exception{
        try{
            if(scenario != null && scenario.isFailed() && driver instanceof TakesScreenshot){
                byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
                attachScreenshot(screenshot);
                String name = "screenshot_" + System.currentTimeMillis() + ".png";
                File dir = new File("target/allure-results");
                dir.mkdirs();
                File file = new File(dir, name);
                java.nio.file.Files.write(file.toPath(), screenshot);
            }
        }catch(Exception e){
            // ignore screenshot failures
        }

        if(driver != null){
            driver.quit();
        }
        ExtentReportManager.flush();
    }

    @Attachment(value = "Screenshot on failure", type = "image/png")
    public byte[] attachScreenshot(byte[] screenshot){
        return screenshot;
    }


}

