package ui.demo;

import static org.testng.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import utilities.ConfigReader;
import utilities.TestKeys;

public class DemoEdgeTest {

	private static boolean headless = ConfigReader.getBooleanValue(TestKeys.HEADLESS_KEY);

	private static WebDriver driver;

	@Test(dependsOnMethods = "isWindowsTest")
	public void testGoogleSearch() throws InterruptedException {
		System.setProperty("webdriver.edge.driver", ConfigReader.getTextValue("edgedriverBinPath"));
		EdgeOptions edgeOptions = new EdgeOptions();

		// Create prefs
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("user_experience_metrics.personalization_data_consent_enabled", true); // Turn off personal prompt

		// Create args
		List<String> args = new ArrayList<String>();
		if (headless)
			args.add("--headless"); // Run headless mode

		Map<String, Object> desiredCapabilities = new HashMap<>();
		desiredCapabilities.put("prefs", prefs);
		desiredCapabilities.put("args", args);
		edgeOptions.setCapability("ms:edgeOptions", desiredCapabilities);
		driver = new EdgeDriver(edgeOptions);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		driver.navigate().to(SearchData.url);
		Thread.sleep(5000); // Let the user actually see something!
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.sendKeys(SearchData.searchTerm);
		Thread.sleep(2000); // Let the user actually see something!
		searchBox.submit();
		Thread.sleep(5000); // Let the user actually see something!
	}

	@Test
	public void isWindowsTest() {
		assertTrue(System.getProperty("os.name").toLowerCase().contains("windows"));
	}

	@AfterMethod
	public void afterTest() {
		if (driver != null)
			driver.quit();
	}

}
