package br.com.selenium.test;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

// Classe sem seguir nenhuma metodologia de boas práticas. Podemos ver que há grande repetiçao de código.
public class TestarRegrasFormulario {

	private WebDriver driver;
	
	@Before
	public void inicializar() {
	    driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200, 865));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");	
	}
	
	@After
	public void finalizar() {
		driver.quit();
	}
	
	
	@Test
	public void verificarNomeObrigatorio() {
		
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alertaNome = driver.switchTo().alert();
		String MsgAlertaNome = alertaNome.getText();
		alertaNome.accept();		
		Assert.assertEquals("Nome eh obrigatorio", MsgAlertaNome);
		
	}
	
	@Test
	public void verificarSobrenomeObrigatorio() {
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Fulano"); 
		
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alertaSobrenome = driver.switchTo().alert();
		String msgAlertaSobrenome = alertaSobrenome.getText();
		alertaSobrenome.accept();
		Assert.assertEquals("Sobrenome eh obrigatorio", msgAlertaSobrenome);

	}
	
	@Test
	public void verificarSexoObrigatorio() {
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Fulano"); 
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("da Silva"); 
		
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alertaSexo = driver.switchTo().alert();
		String msgAlertaSexo = alertaSexo.getText();
		alertaSexo.accept();
		Assert.assertEquals("Sexo eh obrigatorio", msgAlertaSexo);

	}
	
	@Test
	public void verificarComidaSelecionada() {
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Fulano"); 
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("da Silva"); 
		driver.findElement(By.id("elementosForm:sexo:1")).click(); 
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		
		Alert alertaComida = driver.switchTo().alert();
		String msgAlertaComida = alertaComida.getText();
		alertaComida.accept();
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", msgAlertaComida);

	}
	
	@Test
	public void verificarEsporteSelecionado() {
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Fulano"); 
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("da Silva");
		driver.findElement(By.id("elementosForm:sexo:1")).click(); 
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click(); 
		driver.findElement(By.id("elementosForm:comidaFavorita:2")).click(); 
		
		WebElement elementoEsporte = driver.findElement(By.id("elementosForm:esportes"));
		Select comboEsporte = new Select(elementoEsporte);
		comboEsporte.selectByValue("Corrida");
		comboEsporte.selectByValue("nada");
		
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alertaEsporte = driver.switchTo().alert();
		String msgAlertaEsporte = alertaEsporte.getText();
		alertaEsporte.accept();
		Assert.assertEquals("Voce faz esporte ou nao?", msgAlertaEsporte);

	}

}