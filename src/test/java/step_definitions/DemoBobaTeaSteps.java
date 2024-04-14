package step_definitions;

import static org.testng.Assert.*;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Map;

public class DemoBobaTeaSteps {

	private static String favTeaName;

	@Given("I want to drink {string} tea")
	public void i_want_to_drink_tea(String teaName) {
		favTeaName = teaName;
	}

	@When("I go to visit my favorite tea shop")
	public void i_go_to_visit_my_favorite_tea_shop(DataTable dataTable) {
		Map<String, String> data = dataTable.asMap();
		int num = Integer.parseInt(data.get(favTeaName));
		assertTrue(num > 0, String.format("The tea shop ran out of %s tea.", favTeaName));
	}

	@Then("I drink my favorite tea")
	public void i_drink_my_favorite_tea() {
		System.out.printf("Yummy! %s tea is the best.%n", favTeaName);
	}

}
