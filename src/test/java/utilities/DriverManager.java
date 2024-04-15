package utilities;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

/**
 * This <DriverManager> class uses Selenium-Pie. Drivers are updated manually in
 * "drivers" folder under "resources" package.
 */
public class DriverManager {

	private static ThreadLocal<WebDriver> localDriver;

	private static String browser = ConfigReader.getTextValue(TestKeys.BROWSER_KEY);
	private static boolean headless = ConfigReader.getBooleanValue("headless");
	private static boolean isSet;
	private static int waitTime = 5;

	private DriverManager() {
		// WARN: Nothing should be written here.
	}

	private static void setupDriver() {
		if (System.getProperty(TestKeys.BROWSER_KEY) != null)
			browser = System.getProperty(TestKeys.BROWSER_KEY).toLowerCase();
		if (System.getProperty(TestKeys.HEADLESS_KEY) != null)
			headless = Boolean.valueOf(System.getProperty(TestKeys.HEADLESS_KEY).toLowerCase());
		isSet = true;
	}

	public static void setupDriver(String browser) {
		DriverManager.browser = browser;
	}

	public static synchronized WebDriver getDriver() {
		if (!isSet)
			setupDriver();
		if (localDriver == null)
			localDriver = new ThreadLocal<WebDriver>();
		if (localDriver.get() == null)
			localDriver.set(initDriver());
		return localDriver.get();
	}

	public static void reset() {
		// Local driver cannot be null
		if (localDriver != null && localDriver.get() != null) {
			localDriver.get().quit();
			localDriver.remove();
		}
		isSet = false;
	}

	private static void configDriver(WebDriver driver) {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
	}

	private static WebDriver initDriver() {
		WebDriver driver = null;
		switch (browser) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", ConfigReader.getTextValue("chromedriverBinPath"));
			ChromeOptions options = new ChromeOptions();
			setChromeOptions(options);
			driver = new ChromeDriver(options);
			break;
		case "firefox":
			System.setProperty("webdriver.gecko.driver", ConfigReader.getTextValue("geckodriverBinPath"));
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			setFirefoxOptions(firefoxOptions);
			driver = new FirefoxDriver(firefoxOptions);
			break;
		case "edge":
			System.setProperty("webdriver.edge.driver", ConfigReader.getTextValue("edgedriverBinPath"));
			EdgeOptions edgeOptions = new EdgeOptions();
			setEdgeOptions(edgeOptions);
			driver = new EdgeDriver(edgeOptions);
			break;
		default:
			throw new RuntimeException("No such browser in the system.");
		}
		configDriver(driver);
		return driver;
	}

	/**
	 * Set specific conditions of <Chrome> for this application
	 */
	private static void setChromeOptions(ChromeOptions chromeOptions) {
		if (headless)
			chromeOptions.addArguments("--headless");
	}

	/**
	 * Set specific conditions of <Edge> for this application
	 */
	private static void setEdgeOptions(EdgeOptions edgeOptions) {
		Map<String, Object> capabilities = new HashMap<>(); // Create capabilities
		Map<String, Object> prefs = new HashMap<String, Object>(); // Create prefs
		prefs.put("user_experience_metrics.personalization_data_consent_enabled", true); // Turn off personal prompt
		capabilities.put("prefs", prefs);
		edgeOptions.setCapability("ms:edgeOptions", capabilities);
	}

	/**
	 * Set specific conditions of <Firefox> for this application
	 */
	private static void setFirefoxOptions(FirefoxOptions firefoxOptions) {
		firefoxOptions.addPreference("geo.enabled", false); // Turn off geographical locator
	}

}
