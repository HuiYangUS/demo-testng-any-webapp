package test_runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = { "src/test/resources/features" }, glue = {
        "step_definitions", }, plugin = { "pretty" }, tags = "@boba and @tea")
public class TestRunner extends AbstractTestNGCucumberTests {

}
