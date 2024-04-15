package test_runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = { "src/test/resources/features" }, glue = { "hooks", "step_definitions", }, plugin = {
		"pretty", "html:target/cucumber-reports/cucumber.html" }, tags = "@ui and not @firefox")
public class TestRunner extends AbstractTestNGCucumberTests {

}
