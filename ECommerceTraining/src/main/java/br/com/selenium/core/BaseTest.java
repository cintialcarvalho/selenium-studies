package br.com.selenium.core;

import static br.com.selenium.core.DriverFactory.getDriver;
import static br.com.selenium.core.DriverFactory.killDriver;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import br.com.selenium.page.MenuPage;

// Todas as classes de teste herdam da BaseTest. Aqui ficam os métodos que são comuns a todos os testes. Neste caso, so temos um método que é comum a todos.
public class BaseTest {

	private MenuPage menupage = new MenuPage();
	
	@Rule
	public TestName testName = new TestName();
	
	@Before
	public void first() {
		menupage.goInWebSite();
	}
	
	@After
	public void end() throws IOException {
		
		//Usando o ScreenShot da tela no final de cada teste. Este item tem que ficar antes de mandar fechar o driver
		TakesScreenshot screenShot = (TakesScreenshot) getDriver();
		File file = screenShot.getScreenshotAs(OutputType.FILE);
		
		FileUtils.copyFile(file, new File( "target"+ File.separator +"ScreenShot"+ File.separator + testName.getMethodName() + ".jpg"));
		if(Properties.CLOSE_BROWSER) {
			killDriver();
		}
	
	}
	
}
