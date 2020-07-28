package br.com.selenium.suites;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.selenium.core.DriverFactory;
import br.com.selenium.page.LoginPage;
import br.com.selenium.test.ContaTest;
import br.com.selenium.test.MovimentTest;
import br.com.selenium.test.RemoveContaComMovimentoTest;
import br.com.selenium.test.ResumoMensalTest;
import br.com.selenium.test.SaldoTest;

@RunWith(Suite.class)
@SuiteClasses({
		ContaTest.class,
		MovimentTest.class,
		RemoveContaComMovimentoTest.class,
		SaldoTest.class,
		ResumoMensalTest.class
		//RemoveContaSemMovimentoTest.class // comentei porque foi substituído pelo click no link reset().
})

public class SuiteGeral {
	
	private static LoginPage loginPage = new LoginPage();
	
	
	// DESCOMENTAR ESSE MÉTODO PARA RODAR O TESTE EM SELENIUM GRID (teste em paralelo) OU EM CLOUD (Sauce Labs)
	// Este método foi incluído para rodar durante a execucao dos testes em paralelo. Ele irá resetar a massa de teste.
	/*@BeforeClass
	public static void reset() {
		loginPage.acessarTelaInicial();
		loginPage.setEmail("cileca123@mail.com");
		loginPage.setPassword("123456");
		loginPage.submitLogin();
		loginPage.reset();
		DriverFactory.killDriver();
	}*/
	

	// DESCOMENTAR ESSES DOIS MÉTODOS PARA RODAR OS TESTES DE MODO LOCAL
	// OBS: Toda essa parte foi comentada para usar os testes em paralelo do maven
	// Como os testes vao rodar em paralelo, cada teste deverá ter seu proprio login, senao os testes nao irao funcionar ja q cada um vai suar uma instancia do driver
	// O método inicializar() e finalizar() foi trazido para esta classe para que nao seja necessário fazer login a cada teste executado. 
	// Tb foi alterada configuracao para o browser nao fechar. Com isso a execucao dos testes, em forma de suite, fica mais rápida. (caso nao use teste em paralelo)
	@BeforeClass
	public static void inicializa() { 	// Como a declaraçao do loginPage está STATIC, entao este método tambem tem que ser STATIC
		loginPage.acessarTelaInicial();
		loginPage.setEmail("cileca123@mail.com");
		loginPage.setPassword("123456");
		loginPage.submitLogin();
		loginPage.reset();
	}
	
	@AfterClass
	public static void finalizar() {		// Como a declaraçao do loginPage está STATIC, entao este método tambem tem que ser STATIC
		DriverFactory.killDriver();
	}
	
}