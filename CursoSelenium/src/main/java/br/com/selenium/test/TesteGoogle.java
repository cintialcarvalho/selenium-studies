package br.com.selenium.test;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteGoogle {

	@Test
	public void teste() {
		
		//WebDriver driver = new FirefoxDriver();
		WebDriver driver = new ChromeDriver();
		
		//driver.manage().window().setPosition(new Point(100, 100));
		driver.manage().window().setSize(new Dimension(1200, 765));
		
		driver.get("http://www.google.com");
		Assert.assertEquals("Google", driver.getTitle());

		//driver.close(); // fecha apenas uma aba do browser
		driver.quit(); // fecha a janela e mata a instancia do browser

 	}
	
}