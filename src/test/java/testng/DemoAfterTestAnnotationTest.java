package testng;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class DemoAfterTestAnnotationTest {

	@Test
	void thisTest() {
		System.out.println("This is a test.");
	}

	@Test
	void thatTest() {
		System.out.println("This is a test.");
	}

	@AfterTest
	void afterTest() {
		// This executes after all test methods
		System.err.println("End");
	}

}
