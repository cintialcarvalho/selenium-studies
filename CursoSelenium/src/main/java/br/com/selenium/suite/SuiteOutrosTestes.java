package br.com.selenium.suite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.selenium.test.TesteAjax;
import br.com.selenium.test.TesteAlertRefatorado;
import br.com.selenium.test.TestePrime;
import br.com.selenium.test.TesteSincronismo;

@RunWith(Suite.class)
@SuiteClasses({
	TesteAjax.class,
	TesteAlertRefatorado.class,
	TestePrime.class,
	TesteSincronismo.class
})

public class SuiteOutrosTestes {

}