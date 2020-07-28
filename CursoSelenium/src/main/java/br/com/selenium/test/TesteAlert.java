package br.com.selenium.test;

import static br.com.selenium.core.DriverFactory.getDriver;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;



public class TesteAlert {

	@Test
	public void interagirComBotaoAlert() {
		
	    //driver = new ChromeDriver();
		//driver.manage().window().setSize(new Dimension(1200, 865)); ==> Essas duas linhas nao precisam mais estar aq pq foi criada a classe DriverFactory
		//driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html"); ==> Esta linha foi substituida pela linha abaixo
		
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");	 // acessa diretamente pq está no import acima
		
		getDriver().findElement(By.id("alert")).click();
		
		Alert alert = getDriver().switchTo().alert(); // O SwitchTo() tira o foco do formuário e bota no Alert que está fora da página
		String textoDoAlert = alert.getText();
		
		Assert.assertEquals("Alert Simples", textoDoAlert);
		alert.accept();
		getDriver().findElement(By.id("elementosForm:nome")).sendKeys(textoDoAlert); // Escrevendo o conteúdo do Alert no campo Nome só para mostrar que é possível
		
		getDriver().quit(); // A soluçao para a repetiçao desta linha está na classe TestarRegrasFormulario
		
	}
	
	@Test
	public void interagirBotaoAlertConfirmar() {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200, 865));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		// Cenário click no OK
		driver.findElement(By.id("confirm")).click();
		
		Alert alert = driver.switchTo().alert();
		String textoAlert = alert.getText();
		
		Assert.assertEquals("Confirm Simples", textoAlert);
		alert.accept();
		textoAlert = alert.getText();
		Assert.assertEquals("Confirmado", textoAlert);
		alert.accept();
		
		// Cenário click no Cancel
		driver.findElement(By.id("confirm")).click();
		
		alert = driver.switchTo().alert();
		textoAlert = alert.getText();
		Assert.assertEquals("Confirm Simples", textoAlert);
		alert.dismiss();
		
		textoAlert = alert.getText();
		Assert.assertEquals("Negado", textoAlert);
		alert.accept();
		
		driver.quit();
		
	}
	
	@Test
	public void interagirComBotaoAlertPrompt() {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200, 865));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		// Cenário click no OK
		driver.findElement(By.id("prompt")).click();
		Alert alert = driver.switchTo().alert();
		String textoDoAlert = alert.getText();
		Assert.assertEquals("Digite um numero", textoDoAlert);
		alert.sendKeys("12"); 			// Escreve no prompt da janela alert
		alert.accept();
		textoDoAlert = alert.getText();
		Assert.assertEquals("Era 12?", textoDoAlert);
		alert.accept();
		textoDoAlert = alert.getText();
		Assert.assertEquals(":D", textoDoAlert);
		alert.accept();
		
		// Cenário click no Cancel
		driver.findElement(By.id("prompt")).click();
		alert = driver.switchTo().alert();
		Assert.assertEquals("Digite um numero", alert.getText());
		alert.dismiss();
		Assert.assertEquals("Era null?", alert.getText());
		alert.dismiss();
		Assert.assertEquals(":(", alert.getText());
		alert.accept();
		
		driver.quit();
		
	}
	
	
}
