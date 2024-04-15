package step_definitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utilities.DriverManager;
import utilities.TestUtils;

public class DemoGoogleSearchSteps {

	private static String url = "http://www.google.com/";
	private WebDriver driver = DriverManager.getDriver();

	@Given("user is on [Google] home page")
	public void user_is_on_google_home_page() {
		driver.navigate().to(url);
		TestUtils.pause(5);
	}

	@When("user enters {string} in the search box and then submits")
	public void user_enters_in_search_box_and_then_submits(String searchTerm) {
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.sendKeys(searchTerm);
		TestUtils.pause(2);
		searchBox.submit();
	}

	@Then("user sees a list of search results")
	public void user_sees_a_list_of_search_results() {
		TestUtils.pause(2);
	}

}
