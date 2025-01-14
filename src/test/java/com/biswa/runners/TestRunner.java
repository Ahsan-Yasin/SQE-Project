package com.biswa.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.biswa.stepDefinitions",
        tags = "@login",
        plugin = {"pretty", "html:target/cucumber-reports.html"}
)
public class TestRunner  extends AbstractTestNGCucumberTests {
}
