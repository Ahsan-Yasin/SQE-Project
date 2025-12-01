package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import utils.RedisClient;
import utils.ConfigReader;

public class RedisSteps {

    private String value;
    private String expected;

    @Given("a redis server at {string}:{int} and a key {string} with value {string}")
    public void redis_set_get(String host, int port, String key, String val) {
        boolean enabled = Boolean.parseBoolean(ConfigReader.get("tests.redis.enabled", "false"));
        if (!enabled) {
            System.out.println("[REDIS STEPS] Skipping Redis steps because tests.redis.enabled=false");
            value = null; expected = null; return;
        }
        RedisClient client = new RedisClient(host, port);
        client.set(key, val);
        value = client.get(key);
        expected = val;
    }

    @Then("I should be able to read the value back from redis")
    public void verify_redis() {
        boolean enabled = Boolean.parseBoolean(ConfigReader.get("tests.redis.enabled", "false"));
        if (!enabled) {
            System.out.println("[REDIS STEPS] Redis tests are disabled — skipping verification");
            return;
        }
        Assert.assertNotNull("Redis returned null for key", value);
        Assert.assertEquals("Redis stored value does not match expected", expected, value);
    }
}
