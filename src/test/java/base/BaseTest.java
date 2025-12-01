package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import utils.ConfigReader;

public class BaseTest {

    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return DRIVER.get();
    }

    public static void createDriver() {
        WebDriverManager.chromedriver().setup();
        String browser = ConfigReader.get("browser", "chrome-headless");
        ChromeOptions options = new ChromeOptions();
        if (browser != null && browser.toLowerCase().contains("headless")) {
            // use new headless mode where supported
            options.addArguments("--headless=new");
        }
        // default safety options
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        if (!browser.toLowerCase().contains("headless")) {
            // allow maximize for headed runs
            options.addArguments("--start-maximized");
        }
        DRIVER.set(new ChromeDriver(options));
    }

    public static void quitDriver() {
        WebDriver driver = DRIVER.get();
        if (driver != null) {
            driver.quit();
            DRIVER.remove();
        }
    }
}
