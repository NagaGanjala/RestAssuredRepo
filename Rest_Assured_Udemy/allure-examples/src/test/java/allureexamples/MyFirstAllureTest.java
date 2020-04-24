package allureexamples;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;

@Epic("All tools for calculation")
@Feature("I just Pass")
public class MyFirstAllureTest {
	
	@Story("My first allure test")
	@DisplayName("My first allure test")
	@Test
	public void tc1() {
		
		assertTrue(true);
	}

}
