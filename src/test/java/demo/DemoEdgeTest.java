package demo;

import static org.testng.Assert.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import utilities.ConfigReader;

public class DemoEdgeTest {

	private static WebDriver driver;

	@Test(dependsOnMethods = "isWindowsTest")
	public void testGoogleSearch() throws InterruptedException {
		System.setProperty("webdriver.edge.driver", ConfigReader.getTextValue("edgedriverBinPath"));
		Map<String, Object> capabilities = new HashMap<>(); // Create capabilities
		Map<String, Object> prefs = new HashMap<String, Object>(); // Create prefs
		prefs.put("user_experience_metrics.personalization_data_consent_enabled", true); // Turn off personal prompt
		capabilities.put("prefs", prefs);
		EdgeOptions edgeOptions = new EdgeOptions();
		edgeOptions.setCapability("ms:edgeOptions", capabilities);
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
