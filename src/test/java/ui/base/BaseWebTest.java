package ui.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import utilities.ConfigReader;
import utilities.DriverManager;

public class BaseWebTest {

	protected static WebDriver driver;
	protected static WebDriverWait wait;

	/**
	 * {@code @Optional(%String)} is needed to avoid using 'testng.xml'
	 */
	@Parameters({ "browser" })
	@BeforeMethod(groups = { "ui", "web", "e2e" })
	public void setUp(@Optional("default") String browser) {
		if (!browser.equalsIgnoreCase("default"))
			DriverManager.getInstance().setupDriver(browser);
		else
			System.out.println("Using default browser driver:");
		driver = DriverManager.getInstance().getDriver();
		wait = new WebDriverWait(driver, ConfigReader.getLongValue("timeOut"));
		System.out.println("Web Test starts>");
	}

	@AfterMethod(groups = { "ui", "web", "e2e" })
	public void tearDown() {
		if (driver != null)
			driver.quit();
	}

	@AfterMethod
	public void tearDownPartII() {
		System.err.println("Test completed.");
	}

}
