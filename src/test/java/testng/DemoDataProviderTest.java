package testng;

import static org.testng.Assert.*;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DemoDataProviderTest {

	@DataProvider(name = "Demo Data")
	public Object[][] demoTestData() {
		return new Object[][] { { "Jack", 37, false }, { "Mary", 60, true } };
	}

	@Test(dataProvider = "Demo Data")
	public void verifyDemoData(String name, Integer age, Boolean isPassed) {
		assertEquals((boolean) isPassed, (age >= 60));
		System.out.println("Name: " + name);
	}

}
