package br.com.selenium.core;

import static br.com.selenium.core.DriverFactory.killDriver;

import java.io.File;
import java.io.IOException;

import static br.com.selenium.core.DriverFactory.getDriver;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class BaseTest {

	@Rule
	public TestName testName = new TestName();
	
	
	@After
	public void finalizar() throws IOException {
		
		//Usando o ScreenShot da tela no final de cada teste. Este item tem que ficar antes de mandar fechar o driver
		TakesScreenshot screenShot = (TakesScreenshot) getDriver();
		File file = screenShot.getScreenshotAs(OutputType.FILE);
		
		// Originalmente ficaria desta forma target/ScreenShot/, mas é melhor que o proprio java coloque a barra de acordo com o sistema operacional
		//FileUtils.copyFile(file, new File( "target/ScreenShot/" + testName.getMethodName() + ".jpg")); // abaixo o uso do File.separator
		
		FileUtils.copyFile(file, new File( "target"+ File.separator +"ScreenShot"+ File.separator + testName.getMethodName() + ".jpg"));
		if(Properties.CLOSE_BROWSER) {
			killDriver();
		}
	
	}
	
}
