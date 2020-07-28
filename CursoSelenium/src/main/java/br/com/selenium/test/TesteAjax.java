package br.com.selenium.test;
import static br.com.selenium.core.DriverFactory.getDriver;
import static br.com.selenium.core.DriverFactory.killDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.selenium.core.DSL;

public class TesteAjax {
	
	//private WebDriver driver;
	private DSL dsl;
	
	@Before
	public void inicializar() {
	    //driver = new ChromeDriver();
		//driver.manage().window().setSize(new Dimension(1200, 865)); ==> Essas duas linhas nao precisam mais estar aq pq foi criada a classe DriverFactory
		//driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html"); ==> Esta linha foi substituida pela linha abaixo
		
		getDriver().get("https://www.primefaces.org/showcase/ui/ajax/basic.xhtml");	 // acessa diretamente pq está no import acima
		
		//dsl = new DSL(driver);
		
		//Esta linha se resolve alterando a classe PAGE e tirando o construtor do driver. Como está estático na DriverFactory, o driver ja vai estar instanciado
		dsl = new DSL();
	}
	
	@After
	public void finalizar() {
		// driver.quit(); ==> nao precisa mais estar aq pq foi criada a classe DriverFactory
		// DriverFactory.killDriver(); // pode fazer uma chama mais simplificada fazendo o import desta funcao. Ver linha abaixo:
		killDriver();
	}
	
	@Test
	public void testeAjax() {
		dsl.writeField("j_idt725:name", "Testando");
		dsl.clickButton("j_idt725:j_idt728");
		WebDriverWait wait = new WebDriverWait(getDriver(), 30);
		wait.until(ExpectedConditions.textToBe(By.id("j_idt725:display"), "Testando")); // pode-se fazer desta forma
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("j_idt799_start"))); // ou desta forma: esperando que a imagem que aparece no canto direito inferior na tela suma
		Assert.assertEquals("Testando", dsl.returnText("j_idt725:display"));
	}
	
	
}
