package br.com.selenium.test;
import static br.com.selenium.core.DriverFactory.getDriver;
import static br.com.selenium.core.DriverFactory.killDriver;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.selenium.core.DSL;


public class TesteSincronismo {
	
	//private WebDriver driver;
	private DSL dsl;
	
	@Before
	public void inicializar() {
	    //driver = new ChromeDriver();
		//driver.manage().window().setSize(new Dimension(1200, 865)); ==> Essas duas linhas nao precisam mais estar aq pq foi criada a classe DriverFactory
		//driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html"); ==> Esta linha foi substituida pela linha abaixo
		
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");	 // acessa diretamente pq está no import acima
		
		/* Nao usa mais os parametros por causa da criacao da classe DriverFactory
		dsl = new DSL(driver);*/
		
		dsl = new DSL();
	}
	
	@After
	public void finalizar() {
		// driver.quit(); ==> nao precisa mais estar aq pq foi criada a classe DriverFactory
		// DriverFactory.killDriver(); // pode fazer uma chama mais simplificada fazendo o import desta funcao. Ver linha abaixo:
		killDriver();
	}
	
	@Test
	// lançando para o throws, deixamos que o proprio junit gerencie caso ocorra erro, por este motivo não se utiliza um try catch
	public void interagirComRespostaDemoradaEsperaFixa() throws InterruptedException { 
		dsl.clickButton("buttonDelay");
		Thread.sleep(5000); // aguarda 5 segundo (5000 milissegundos) até escrever no campo que vai aparecer
		dsl.writeField("novoCampo", "Escrevendo no campo novo"); // porém não é recomendado pq a aplicaçao pode demorar mais que isso para liberar o campo
		Assert.assertEquals("Escrevendo no campo novo", dsl.returnValueField("novoCampo"));
	}
	
	@Test
	public void interagirComRespostaDemoradaEsperaImplicita() throws InterruptedException { 
		dsl.clickButton("buttonDelay");
		// Espera por 5 segundos pelo carregamento dos outros elementos da página, enquanto isso o Junit fica checando se o elemento está disponível ou nao.
		// Caso o elemento fique disponível antes dos 5 segundos (ex.: 2 segundos), o teste continua o fluxo. Porém caso passe dos 5 segundos, dá falha no teste.
		// Também é possível utilizar essa linha parametrizada no iniciarlizar() para que todos os testes passem a utilizar de forma padrão.
		getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		dsl.writeField("novoCampo", "Escrevendo no campo novo"); 
		Assert.assertEquals("Escrevendo no campo novo", dsl.returnValueField("novoCampo"));
		
		// Mudando para 0 segundos para que os próximos casos de teste nao sejam impactados pela espera de 5 segundos.
		getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS); 
	}
	
	@Test
	public void interagirComRespostaDemoradaEsperaExplicita() { 
		dsl.clickButton("buttonDelay");
		WebDriverWait wait = new WebDriverWait(getDriver(), 30); // Tempo de espera em segundos: 30 segundos
		// Vai esperar especificamente por este campo por 30 segundos. Caso o elemento fique dispoível antes disso, o teste segue o fluxo. Caso demore mais tempo, ocorre falha.
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("novoCampo"))); 
		dsl.writeField("novoCampo", "Escrevendo no campo novo");
		Assert.assertEquals("Escrevendo no campo novo", dsl.returnValueField("novoCampo"));
	}
	/*OBS:
	 * NUNCA se deve misturar espera implicita e explicita. 
	 * A implicita ocorre no servidor remoto e pode estar sujeita a alteraçoes.
	 * É mais remendado utilizar a esperar explicita, para os casos específicos que se quer tratar, ainda mais qnd se trata de ajax.
	 * */
	
}
