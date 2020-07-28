package br.com.selenium.test;
import static br.com.selenium.core.DriverFactory.getDriver;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.selenium.core.BaseTest;
import br.com.selenium.page.CadastrarPage;

// UTILIZANDO A ARQUITEURA POM - PAGE OBJECT MODEL

//public class TesteCadastrarRefatorada { // passando a estender a BaseTest para herdar o metodo finalizar()
public class TesteCadastrarRefatorada extends BaseTest {
	
	//private WebDriver driver;
	//private DSL dsl;
	private CadastrarPage page;
	
	@Before
	public void inicializar() {
	    //driver = new ChromeDriver();
		//driver.manage().window().setSize(new Dimension(1200, 865)); ==> Essas duas linhas nao precisam mais estar aq pq foi criada a classe DriverFactory
		//driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html"); ==> Esta linha foi substituida pela linha abaixo
		
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");	 // acessa diretamente pq está no import acima
		
		//dsl = new DSL(driver);
		//page = new CadastrarPage(driver); // nao usa mais o parametro por causa da classe DriverFactory
		page = new CadastrarPage();
	}
	
	/*@After // ==> este bloco de codigo foi retirado pq a classe passou a estender a classe BaseTest e este método ja está nesta nova classe 
	public void finalizar() {
		//driver.quit();
		killDriver();
	}*/
	
	@Test
	public void cadastrarComSucesso() {
		
		/*
		dsl.writeField("elementosForm:nome", "Fulano");
		dsl.writeField("elementosForm:sobrenome", "da Silva");
		dsl.clickRadioButton("elementosForm:sexo:1");
		dsl.clickCheckBox("elementosForm:comidaFavorita:2");
		dsl.selectComboBox("elementosForm:escolaridade", "especializacao");
		dsl.selectComboBox("elementosForm:esportes", "natacao");
		dsl.selectComboBox("elementosForm:esportes", "Corrida");	
		dsl.clickButton("elementosForm:cadastrar");*/
		
		// using page object model
		page.setName("Fulano");
		page.setSurname("da Silva");
		page.setSexFemale();
		page.setFavoriteFoodPizza();
		page.setSchoolDegree("especializacao");
		page.setSport("natacao");
		page.setSport("Corrida");
		page.submit();
		
	
		/*
		 * // não é a maneira correta de se fazer
		Assert.assertTrue(dsl.returnText("resultado").startsWith("Cadastrado!"));
		Assert.assertTrue(dsl.returnText("descNome").endsWith("Fulano"));
		Assert.assertEquals("Sobrenome: da Silva", dsl.returnText("descSobrenome"));
		Assert.assertEquals("Sexo: Feminino", dsl.returnText("descSexo"));
		Assert.assertEquals("Comida: Pizza", dsl.returnText("descComida"));
		Assert.assertEquals("Escolaridade: especializacao", dsl.returnText("descEscolaridade"));
		Assert.assertEquals("Esportes: Natacao Corrida", dsl.returnText("descEsportes"));*/
		
		//Refatorado, usando XPath para retornar os valores
		Assert.assertEquals("Cadastrado!", page.returnTextOnSubmit());
		Assert.assertEquals("Fulano", page.returnTextName());
		Assert.assertEquals("da Silva", page.returnTextSurname());
		Assert.assertEquals("Feminino", page.returnSex());
		Assert.assertEquals("Pizza", page.returnFavoriteFood());
		Assert.assertEquals("especializacao", page.returnSchoolDegre());
		Assert.assertEquals("Natacao Corrida", page.returnSport());
		
	}
}
