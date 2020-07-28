package br.com.selenium.test;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;


public class TesteCadastrar {

	@Test
	public void cadastrarComSucesso() {
		
		WebDriver driver = new ChromeDriver(); // A soluçao para a repeticao dessas 3 linhas está na classe TestarRegrasFormulario
		driver.manage().window().setSize(new Dimension(1200, 865));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Fulano");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("da Silva");
		driver.findElement(By.id("elementosForm:sexo:1")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
		
		WebElement elementComboEscolaridade = driver.findElement(By.id("elementosForm:escolaridade"));
		Select comboEscolaridade = new Select(elementComboEscolaridade);
		comboEscolaridade.selectByValue("especializacao");
		
		WebElement elementComboEsport = driver.findElement(By.id("elementosForm:esportes"));
		Select comboEsport = new Select(elementComboEsport);
		comboEsport.selectByValue("natacao");
		comboEsport.selectByValue("Corrida");
		
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		
		// não é a maneira correta de se fazer
		Assert.assertTrue(driver.findElement(By.id("resultado")).getText().startsWith("Cadastrado!"));
		Assert.assertTrue(driver.findElement(By.id("descNome")).getText().endsWith("Fulano"));
		Assert.assertEquals("Sobrenome: da Silva", driver.findElement(By.id("descSobrenome")).getText());
		Assert.assertEquals("Sexo: Feminino", driver.findElement(By.id("descSexo")).getText());
		Assert.assertEquals("Comida: Pizza", driver.findElement(By.id("descComida")).getText());
		Assert.assertEquals("Escolaridade: especializacao", driver.findElement(By.id("descEscolaridade")).getText());
		Assert.assertEquals("Esportes: Natacao Corrida", driver.findElement(By.id("descEsportes")).getText());
		
		driver.quit(); // A soluçao para a repetiçao desta linha está na classe TestarRegrasFormulario
		
	}
	
}