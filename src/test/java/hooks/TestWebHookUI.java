package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import utilities.ConfigReader;
import utilities.DataManager;
import utilities.DriverManager;
import utilities.PageManager;
import utilities.WebJobs;

public class TestWebHookUI {

	@Before(order = 1, value = "@chrome")
	public void useChrome() {
		setupChrome();
	}

	@Before(order = 1, value = "@firefox")
	public void useFirefox() {
		System.setProperty("browser", "firefox");
	}

	private static void setupChrome() {
		System.setProperty("browser", "chrome");
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
	}

}