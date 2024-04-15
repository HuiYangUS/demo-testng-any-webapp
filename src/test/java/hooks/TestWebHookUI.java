package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import utilities.ConfigReader;
import utilities.DataManager;
import utilities.DriverManager;
import utilities.PageManager;
import utilities.TestKeys;
import utilities.WebJobs;

public class TestWebHookUI {

	@Before(order = 1, value = "@chrome")
	public void useChrome() {
		DriverManager.setupDriver("chrome");
	}

	@Before(order = 1, value = "@firefox")
	public void useFirefox() {
		DriverManager.setupDriver("firefox");
	}

	@Before(order = 2, value = "@ui or @web or @e2e")
	public void setUp() {
		DriverManager.getDriver();
		PageManager.getInstance();
		DataManager.getInstance();
	}

	@After(order = 2, value = "@ui or @web or @e2e")
	public void tearDown(Scenario scenario) {
		WebJobs jobs = new WebJobs(DriverManager.getDriver());
		if (ConfigReader.getBooleanValue("screenshot") && scenario.isFailed())
			jobs.savesScreenshot();
		DriverManager.reset();
		PageManager.reset();
		DataManager.reset();
		System.clearProperty(TestKeys.BROWSER_KEY);
	}

}
