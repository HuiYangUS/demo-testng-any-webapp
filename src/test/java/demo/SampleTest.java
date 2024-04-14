package demo;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import utilities.ConfigReader;

public class SampleTest {

	@Test
	public void testGoogleSearch() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", ConfigReader.getTextValue("chromedriverBinPath"));
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get("http://www.google.com/");
		Thread.sleep(2000); // Let the user actually see something!
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.sendKeys("Wonder Woman");
		searchBox.submit();
		Thread.sleep(2000); // Let the user actually see something!
		driver.quit();
	}

}
