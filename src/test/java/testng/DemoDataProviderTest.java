package testng;

import static org.testng.Assert.*;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DemoDataProviderTest {

	/**
	 * Method to be tested
	 */
	@DataProvider(name = "Demo Data")
	public Object[][] demoTestData() {
		return new Object[][] { { "Jack", 37, false }, { "Mary", 60, true } };
	}

	/**
	 * Actual test
	 */
	@Test(dataProvider = "Demo Data")
	public void verifyDemoData(String name, Integer grade, Boolean isPassed) {
		assertEquals((boolean) isPassed, (grade >= 60));
		System.out.println("Name: " + name);
	}

}
