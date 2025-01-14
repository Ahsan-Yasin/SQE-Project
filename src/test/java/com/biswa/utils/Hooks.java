package com.biswa.utils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Hooks {

    public static WebDriver driver;

    @Before
    public void setUp(DataTable dataTable) throws Exception{
        ExtentReportManager.setExtent();

    }

    @After
    public void tearDown() throws Exception{
        if(driver != null){
            driver.quit();
        }
        ExtentReportManager.flush();
    }


}
