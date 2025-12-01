package runners;

import org.junit.jupiter.api.Test;

public class RunCucumberTest {

    @Test
    public void runFeatures() throws Exception {
        // Execute cucumber features using CLI runner — this guarantees features run under mvn test
        boolean includeFlaky = Boolean.parseBoolean(System.getProperty("includeFlaky", "false"));
        java.util.List<String> argvList = new java.util.ArrayList<>();
        argvList.add("-g"); argvList.add("steps");
        argvList.add("-g"); argvList.add("hooks");
        argvList.add("src/test/resources/features");
        if (!includeFlaky) {
            argvList.add("--tags"); argvList.add("not @flaky");
        }
        // Use 'progress' for clear ASCII pass/fail markers and 'summary' for a final human-readable summary
        argvList.add("--plugin"); argvList.add("progress");
        argvList.add("--plugin"); argvList.add("summary");
        argvList.add("--plugin"); argvList.add("json:target/cucumber.json");
        // Allure Cucumber 7 adapter plugin (adapter on classpath will create results in its default location)
        argvList.add("--plugin"); argvList.add("io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm");

        String[] argv = argvList.toArray(new String[0]);

        // io.cucumber.core.cli.Main#run returns exit code
        int exit = io.cucumber.core.cli.Main.run(argv, Thread.currentThread().getContextClassLoader());
        if (exit != 0) {
            throw new AssertionError("Cucumber CLI returned non-zero exit code: " + exit);
        }
    }
}
