package br.com.selenium.core;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import br.com.selenium.core.Properties.TipoExecucao;

public class DriverFactory {
	
	// Comentado devido a refatoraçao para uso de teste paralelos (plugin do maven)
	//private static WebDriver driver;

	//Metodo criado para usar os testes em paralelo do maven
	private static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<WebDriver>() {
		@Override
		protected synchronized WebDriver initialValue() {
			return initDriver();			
		}
	};
	
	private DriverFactory() { }
	
	//Método foi alterado para usar os testes em paralelo do maven
	public static WebDriver getDriver() {
		
		return threadDriver.get();  //passa a retorna a thread do driver
		
		/*if(driver != null) {
			switch(Properties.browser) {
			case FIREFOX: 
				driver = new FirefoxDriver(); 
				break;
			case CHROME: 
				driver = new ChromeDriver(); 
				break;
			}
			driver.manage().window().setSize(new Dimension(1200, 865));
		}
		return driver;*/
	}	
	
	
	// método abaixo criado para usar os testes em paralelo do maven
	// método refatorado para usar o selenium grid e para execvução na nuvem
	public static WebDriver initDriver() {
		
		WebDriver driver = null;
		
		if(Properties.TIPO_EXECUCAO == TipoExecucao.LOCAL) {
			switch(Properties.BROWSER) {
			case FIREFOX: driver = new FirefoxDriver(); break;
			case CHROME: driver = new ChromeDriver(); break;
			}
		}
		
		if(Properties.TIPO_EXECUCAO == TipoExecucao.GRID) {
			DesiredCapabilities cap = null;
			switch(Properties.BROWSER) {
				case FIREFOX: cap = DesiredCapabilities.firefox(); break;
				case CHROME: cap = DesiredCapabilities.chrome(); break;
			}
			try {
				driver = new RemoteWebDriver(new URL("http://192.168.15.13:4444/wd/hub"), cap);
			} catch (MalformedURLException e) {
				System.err.println("Falha de conexão com o GRID.");
				e.printStackTrace();
			}
		}
		
		if(Properties.TIPO_EXECUCAO == TipoExecucao.CLOUD) {
			DesiredCapabilities cap = null;
			switch(Properties.BROWSER) {
				case FIREFOX: cap = DesiredCapabilities.firefox(); break;
				case CHROME: cap = DesiredCapabilities.chrome(); break;
			}
			try {
				driver = new RemoteWebDriver(new URL(""), cap);
				
			} catch (MalformedURLException e) {
				System.err.println("Falha de conexão com o GRID.");
				e.printStackTrace();
			}
			
		}
		driver.manage().window().setSize(new Dimension(1200, 865));
		return driver;
	}
	
	public static void killDriver() {		
		WebDriver driver = getDriver();
		if(driver != null) {
			driver.quit();
			driver = null;
		}
		if(threadDriver != null) { // validacao incluida para usar os testes em paralelo do maven
			threadDriver.remove();
		}
	}

	
}