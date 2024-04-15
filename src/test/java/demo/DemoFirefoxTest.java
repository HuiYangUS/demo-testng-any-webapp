package demo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import utilities.ConfigReader;

public class DemoFirefoxTest {

	private static WebDriver driver;

	@Test
	public void testGoogleSearch() throws InterruptedException {
		System.setProperty("webdriver.gecko.driver", ConfigReader.getTextValue("geckodriverBinPath"));
		driver = new FirefoxDriver();
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

	@AfterMethod
	public void afterTest() {
		driver.quit();
	}

}
