package com.example.runners;

import org.junit.Test;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features", 
    glue = "com.example.steps",
    plugin = {"pretty", "html:target/cucumber-reports.html", "json:target/cucumber-reports/cucumber.json"},
    monochrome = true
)
public class CucumberRunnerTest {

    @Test
    public void testSimple() {
   }
}
