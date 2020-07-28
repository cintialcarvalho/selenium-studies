package br.com.selenium.suite;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.selenium.core.DriverFactory;
import br.com.selenium.test.TesteCadastrarRefatorada;
import br.com.selenium.test.TesteRegrasCadastro;

@RunWith(Suite.class)
@SuiteClasses({
	//TestarRegrasFormularioRefatorado.class,
	//TesteAlertRefatorado.class,
	TesteCadastrarRefatorada.class, // está refatorando usando extends para classe BaseTest, entao so vai abrir um browser
	//TesteCampoTreinamentoRefatorado.class,
	//TesteFramesRefatorado.class,
	TesteRegrasCadastro.class // está refatorando usando extends para classe BaseTest, entao so vai abrir um browser
})

public class SuiteRegrasCampoTreinamento {

	@AfterClass
	public static void killAll() { // tem q ser statico pq eh usado qnd a classe termina e nao vai ter mais uma instancia para ele trabalhar
		DriverFactory.killDriver(); // chama o método para matar o driver
	}
	
}