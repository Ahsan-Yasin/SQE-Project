package hooks;

import base.BaseTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Hooks {

    // store per-thread scenario UUIDs and timestamps so we can write result files
    private static final ThreadLocal<Map<String, String>> SCENARIO_UUID = ThreadLocal.withInitial(HashMap::new);
    private static final ThreadLocal<Map<String, Long>> SCENARIO_START = ThreadLocal.withInitial(HashMap::new);

    @Before
    public void beforeScenario(Scenario scenario) {
        BaseTest.createDriver();
        String key = scenario.getName() + ":" + Thread.currentThread().getId();
        String uuid = UUID.randomUUID().toString();
        SCENARIO_UUID.get().put(key, uuid);
        SCENARIO_START.get().put(key, System.currentTimeMillis());
    }

    @After
    public void afterScenario(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                byte[] screenshot = ((TakesScreenshot) BaseTest.getDriver()).getScreenshotAs(OutputType.BYTES);
                Allure.addAttachment("screenshot-" + scenario.getName(), new ByteArrayInputStream(screenshot));
                // persist screenshot into allure-results directory so report can reference it
                try {
                    String key = scenario.getName() + ":" + Thread.currentThread().getId();
                    String uuid = SCENARIO_UUID.get().getOrDefault(key, UUID.randomUUID().toString());
                    File outDir = new File(System.getProperty("allure.results.directory", "target/allure-results"));
                    outDir.mkdirs();
                    String pngName = uuid + "-screenshot.png";
                    File png = new File(outDir, pngName);
                    try (FileOutputStream fos = new FileOutputStream(png)) {
                        fos.write(screenshot);
                    }
                } catch (Exception ignored) { }
            }
            // create a minimal Allure result JSON file for scenario (ensures the report is not empty)
            try {
                String key = scenario.getName() + ":" + Thread.currentThread().getId();
                String uuid = SCENARIO_UUID.get().getOrDefault(key, UUID.randomUUID().toString());
                long stop = System.currentTimeMillis();
                long start = SCENARIO_START.get().getOrDefault(key, stop);
                String status = scenario.isFailed() ? "failed" : "passed";
                File outDir = new File(System.getProperty("allure.results.directory", "target/allure-results"));
                outDir.mkdirs();
                File result = new File(outDir, uuid + "-result.json");
                try (PrintWriter pw = new PrintWriter(result, StandardCharsets.UTF_8)) {
                    pw.println("{");
                    pw.println("  \"uuid\": \"" + uuid + "\",");
                    pw.println("  \"historyId\": \"" + uuid + "\",");
                    pw.println("  \"name\": \"" + escapeJson(scenario.getName()) + "\",");
                    pw.println("  \"status\": \"" + status + "\",");
                    pw.println("  \"stage\": \"finished\",");
                    pw.println("  \"start\": " + start + ",");
                    pw.println("  \"stop\": " + stop + ",");
                    String pngName = uuid + "-screenshot.png";
                    pw.println("  \"attachments\": [");
                    pw.println("    {\"name\": \"screenshot\", \"source\": \"" + pngName + "\", \"type\": \"image/png\"}");
                    pw.println("  ]");
                    pw.println("}");
                }
            } catch (Exception ignored) { }
        } catch (Exception e) {
            // ignore
        } finally {
            BaseTest.quitDriver();
        }
    }

    private static String escapeJson(String s) {
        return s == null ? "" : s.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}
