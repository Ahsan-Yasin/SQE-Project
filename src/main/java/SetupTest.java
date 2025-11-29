import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import redis.clients.jedis.Jedis;
import io.qameta.allure.Allure;

import java.io.ByteArrayInputStream;

public class SetupTest {


    public void testAllSetup() {
        // 1. ChromeDriver
        try {
            WebDriverManager.chromedriver().setup();
            WebDriver driver = new ChromeDriver();
            driver.get("https://www.saucedemo.com");
            System.out.println("WebDriver Test: Page title = " + driver.getTitle());
            driver.quit();
        } catch (Exception e) {
            System.out.println("WebDriver Test Failed: " + e.getMessage());
        }

        // 2. Redis
        try {
            Jedis jedis = new Jedis("localhost", 6379);
            jedis.set("test_key", "hello_redis");
            String value = jedis.get("test_key");
            System.out.println("Redis Test: Value = " + value);
            jedis.close();
        } catch (Exception e) {
            System.out.println("Redis Test Failed: " + e.getMessage());
        }

        // 3. Allure
        try {
            Allure.addAttachment("Allure Test Attachment",
                    new ByteArrayInputStream("Allure is working!".getBytes()));
            System.out.println("Allure Test: Attachment added. Check allure-results folder.");
        } catch (Exception e) {
            System.out.println("Allure Test Failed: " + e.getMessage());
        }

        // 4. Git
        try {
            ProcessBuilder pb = new ProcessBuilder("git", "rev-parse", "HEAD");
            pb.redirectErrorStream(true);
            Process process = pb.start();
            java.util.Scanner s = new java.util.Scanner(process.getInputStream()).useDelimiter("\\A");
            String output = s.hasNext() ? s.next() : "";
            System.out.println("Git Test: Last commit hash = " + output);
        } catch (Exception e) {
            System.out.println("Git Test Failed: " + e.getMessage());
        }

        System.out.println("✅ All setup tests completed!");
    }

    public static void main(String[] args) {
        SetupTest test = new SetupTest();
        test.testAllSetup();
    }

}
