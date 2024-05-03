package ui.lab;

import static org.testng.Assert.*;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import ui.base.BaseWebTest;
import utilities.TestUtils;

public class SampleTest extends BaseWebTest {

	private static final String URL = "http://www.google.com/";
	private static final String SEARCH_TERM = "Selenium";

	@Test(groups = { "ui", "web" })
	public void testGoogleSearch() {
		driver.get(URL);
		TestUtils.pause(2); // Let the user actually see something!
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.sendKeys(SEARCH_TERM);
		TestUtils.pause(2); // Let the user actually see something!
		searchBox.submit();
		TestUtils.pause(2); // Let the user actually see something!
		List<WebElement> searchResultLinks = wait.until(
				ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//div/span/a[@jsname and @data-ved]"), 9));
		String searchLink = "https://www.selenium.dev/";
		boolean found = false;
		for (WebElement link : searchResultLinks) {
			if (link.getAttribute("href").equals(searchLink)) {
				found = true;
				break;
			}
		}
		assertTrue(found, "Expected link was not found.");
	}

}
