package testng;

import static org.testng.Assert.*;

import org.testng.annotations.Test;

public class DemoUnitTest {

	static int simpleAddTwoNums(int num1, int num2) {
		return num1 + num2;
	}

	@Test
	void testSimpleAddTwoNums() {
		int expectedNum = 7;
		int actualNum = simpleAddTwoNums(3, 4);
		assertEquals(actualNum, expectedNum);
	}

}
