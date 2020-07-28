package br.com.selenium.test;
import static br.com.selenium.core.DriverFactory.getDriver;
import static br.com.selenium.core.DriverFactory.killDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.selenium.page.CadastrarPage;

//UTILIZANDO A ARQUITEURA POM - PAGE OBJECT MODEL

public class TesteAlertRefatorado {
	//private WebDriver driver;
	//private DSL dsl;
	private CadastrarPage page;
	
	@Before
	public void inicializar() {
	    //driver = new ChromeDriver();
		//driver.manage().window().setSize(new Dimension(1200, 865)); ==> Essas duas linhas nao precisam mais estar aq pq foi criada a classe DriverFactory
		//driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html"); ==> Esta linha foi substituida pela linha abaixo
		
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");	 // acessa diretamente pq está no import acima
		
		/* Nao usa mais os parametros por causa da criacao da classe DriverFactory
		dsl = new DSL(driver);
		page = new CadastrarPage(driver);*/
		
		//dsl = new DSL();
		page = new CadastrarPage();
	}
	
	@After
	public void finalizar() {
		//driver.quit();
		killDriver();
	}
	
	@Test
	public void interagirComBotaoAlert() {
		
		//dsl.clickButton("alert");	
		page.clickButtonALert();
		//dsl.writeField("elementosForm:nome", dsl.returnTextThenAcceptAlert());
		page.setName(page.returnTextThenAcceptAlert());
	}
	
	@Test
	public void interagirBotaoAlertConfirmar() {
		
		// Cenário click no OK
		//dsl.clickButton("confirm");
		//Assert.assertEquals("Confirm Simples", dsl.returnTextThenAcceptAlert());
		//Assert.assertEquals("Confirmado", dsl.returnTextThenAcceptAlert());
		
		page.clickButtonConfirm();
		Assert.assertEquals("Confirm Simples", page.returnTextThenAcceptAlert());
		Assert.assertEquals("Confirmado", page.returnTextThenAcceptAlert());

		// Cenário click no Cancel
		/*dsl.clickButton("confirm");
		Assert.assertEquals("Confirm Simples", dsl.returnTextThenDismissAlert());
		Assert.assertEquals("Negado", dsl.returnTextThenAcceptAlert());*/
		
		page.clickButtonConfirm();
		Assert.assertEquals("Confirm Simples", page.returnTextThenDismissAlert());
		Assert.assertEquals("Negado", page.returnTextThenAcceptAlert());
		
	}
	
	@Test
	public void interagirComBotaoAlertPrompt() {
		
		// Cenário click no OK
		/*dsl.clickButton("prompt");
		Assert.assertEquals("Digite um numero", dsl.returnTextAlert());
		dsl.writeTextAlert("12");
		Assert.assertEquals("Era 12?", dsl.returnTextThenAcceptAlert());
		Assert.assertEquals(":D", dsl.returnTextThenAcceptAlert());*/
		
		page.clickButtonPrompt();
		Assert.assertEquals("Digite um numero", page.returnTextAlert());
		page.setTextAlert("12");
		Assert.assertEquals("Era 12?", page.returnTextThenAcceptAlert());
		Assert.assertEquals(":D", page.returnTextThenAcceptAlert());

		// Cenário click no Cancel
		/*dsl.clickButton("prompt");
		Assert.assertEquals("Digite um numero", dsl.returnTextThenDismissAlert());
		Assert.assertEquals("Era null?", dsl.returnTextThenDismissAlert());
		Assert.assertEquals(":(", dsl.returnTextThenAcceptAlert());*/
		
		page.clickButtonPrompt();
		Assert.assertEquals("Digite um numero", page.returnTextThenDismissAlert());
		Assert.assertEquals("Era null?", page.returnTextThenDismissAlert());
		Assert.assertEquals(":(", page.returnTextThenAcceptAlert());
		
	}
	
}
