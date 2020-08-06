package br.com.selenium.suites;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.selenium.core.DriverFactory;
import br.com.selenium.test.ChoiceItem;
import br.com.selenium.test.FindItemByClick;
import br.com.selenium.test.ManageCart;

@RunWith(Suite.class)
@SuiteClasses({
	ChoiceItem.class,
	FindItemByClick.class,
	ManageCart.class
})

public class SuiteAllTests {

	@AfterClass
	public static void finalizar() {	
		DriverFactory.killDriver();
	}
	
}
