package br.com.selenium.test;
import static br.com.selenium.core.DriverFactory.getDriver;
import static br.com.selenium.core.DriverFactory.killDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.selenium.page.CadastrarPage;

//UTILIZANDO A ARQUITEURA POM - PAGE OBJECT MODEL

public class TestarRegrasFormularioRefatorado {
	//private WebDriver driver; // ==> Refatoração: nao precisa mais estar aq pq foi criada a classe DriverFactory
	//private DSL dsl;
	private CadastrarPage page;
	
	@Before
	public void inicializar() {
	    
		//driver = new ChromeDriver();
		//driver.manage().window().setSize(new Dimension(1200, 865)); ==> Essas duas linhas nao precisam mais estar aq pq foi criada a classe DriverFactory
		
		//driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html"); ==> Esta linha foi substituida pela linha abaixo
		
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");	 // acessa diretamente pq está no import acima
		
		//dsl = new DSL(driver);
		
		//Esta linha se resolve alterando a classe PAGE e tirando o construtor do driver. Como está estático na DriverFactory, o driver ja vai estar instanciado
		page = new CadastrarPage(); 
	}
	
	@After
	public void finalizar() {
		// driver.quit(); ==> nao precisa mais estar aq pq foi criada a classe DriverFactory
		// DriverFactory.killDriver(); // pode fazer uma chama mais simplificada fazendo o import desta funcao. Ver linha abaixo:
		killDriver();
	}
	
	
	@Test
	public void verificarNomeObrigatorio() {
		//dsl.clickButton("elementosForm:cadastrar"); // Esta linha virou uma função na classe DSL (BasePage) e da classe CadastroPage
		// A função acima, para seguir as boas práticas de programação de testes, só deve ser referenciada pela classe Page (CadastrarPage)
		page.submit();
		
		//Assert.assertEquals("Nome eh obrigatorio", dsl.returnTextThenAcceptAlert());
		Assert.assertEquals("Nome eh obrigatorio", page.returnTextThenAcceptAlert());
	}
	
	@Test
	public void verificarSobrenomeObrigatorio() {
		/*dsl.writeField("elementosForm:nome", "Fulano"); 
		dsl.clickButton("elementosForm:cadastrar");*/
		
		page.setName("Fulano");
		page.submit();
		
		Assert.assertEquals("Sobrenome eh obrigatorio", page.returnTextThenAcceptAlert());
	}
	
	@Test
	public void verificarSexoObrigatorio() {
		/*dsl.writeField("elementosForm:nome", "Fulano");
		dsl.writeField("elementosForm:sobrenome", "da Silva");
		dsl.clickButton("elementosForm:cadastrar");*/

		page.setName("Fulano");
		page.setSurname("da Silva");
		page.submit();
		Assert.assertEquals("Sexo eh obrigatorio", page.returnTextThenAcceptAlert());
	}
	
	@Test
	public void verificarComidaSelecionada() {
		/*dsl.writeField("elementosForm:nome", "Fulano");
		dsl.writeField("elementosForm:sobrenome", "da Silva");
		dsl.clickRadioButton("elementosForm:sexo:1");
		dsl.clickRadioButton("elementosForm:comidaFavorita:0");
		dsl.clickRadioButton("elementosForm:comidaFavorita:3");
		dsl.clickButton("elementosForm:cadastrar");		*/
		
		page.setName("Fulano");
		page.setSurname("da Silva");
		page.setSexFemale();
		page.setFavoriteFoodCarne();
		page.setFavoriteFoodVegetariano();
		page.submit();
		
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", page.returnTextThenAcceptAlert());
	}
	
	@Test
	public void verificarEsporteSelecionado() {
		
		//OBS: Este método foi refeito utilizando a Metodologia DDT na classe TesteRegrasCadastro.
		
		/*dsl.writeField("elementosForm:nome", "Fulano");
		dsl.writeField("elementosForm:sobrenome", "da Silva");
		dsl.clickRadioButton("elementosForm:sexo:1");
		dsl.clickRadioButton("elementosForm:comidaFavorita:0");
		dsl.clickRadioButton("elementosForm:comidaFavorita:2");
		dsl.selectComboBox("elementosForm:esportes", "Corrida");
		dsl.selectComboBox("elementosForm:esportes", "nada");
		dsl.clickButton("elementosForm:cadastrar");	*/
		
		page.setName("Fulano");
		page.setSurname("da Silva");
		page.setSexFemale();
		page.setFavoriteFoodCarne();
		page.setFavoriteFoodPizza();
		page.setSport("Corrida", "nada");
		page.submit();
		
		Assert.assertEquals("Voce faz esporte ou nao?", page.returnTextThenAcceptAlert());
	}
}