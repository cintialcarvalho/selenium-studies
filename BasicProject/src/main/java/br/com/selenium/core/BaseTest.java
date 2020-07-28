package br.com.selenium.core;

import static br.com.selenium.core.DriverFactory.killDriver;

import java.io.File;
import java.io.IOException;

import static br.com.selenium.core.DriverFactory.getDriver;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import br.com.selenium.page.LoginPage;

public class BaseTest {

	private LoginPage loginPage = new LoginPage();
	
	@Rule
	public TestName testName = new TestName(); // Usa o nome do teste durante a execucao para dar nome ao screenshot
	
	
	// ESTE MÉTODO DEVE FICAR AQUI CASO SEJA RODADO USANDO O SELENIUM GRID (testes em paralelo), explicações abaixo:
	// Este bloco deve ser comentado pq caso seja transferido para a classe SuiteGeral para que nao seja necessário fazer login a cada teste da suite.
	// A intençao é que os testes rodem mais rápido. Tambem foi alterada a classe Properties para que o browser nao feche. (Caso nao use teste em paralelo)
	// Caso esteja usando teste em paralelo, esta inicializacao deve ficar aqui e nao na classe SuiteGeral para que cada threaddriver tenha seu proprio login
	// Esse método vai ser comum a todos os outros testes pq o login é obrigatório. Por isso ele foi colocado como Before e por isso está nessa classe
	@Before
	public void inicializa() {
		loginPage.acessarTelaInicial();
		loginPage.setEmail("cileca123@mail.com");
		loginPage.setPassword("123456");
		loginPage.submitLogin();
	} 
	
	// Ao finalizar os testes, o Junit tira screenshots das telas.
	@After
	public void finalizar() throws IOException {
		TakesScreenshot screenShot = (TakesScreenshot) getDriver();
		File file = screenShot.getScreenshotAs(OutputType.FILE);
		 
		FileUtils.copyFile(file, new File( "target"+ File.separator +"ScreenShot"+ File.separator + testName.getMethodName() + ".jpg"));
		if(Properties.CLOSE_BROWSER) {
			killDriver();
		}
	
	}
	
}
