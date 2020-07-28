package br.com.selenium.test;
import static br.com.selenium.core.DriverFactory.getDriver;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import br.com.selenium.core.BaseTest;
import br.com.selenium.page.CadastrarPage;

// ===> UTILIZANDO A METODOLOGIA DDT - DATA DRIVEN TESTING <===

@RunWith(Parameterized.class)
//public class TesteRegrasCadastro { // passa a estender a classe BaseTest e herda os metodos de la
public class TesteRegrasCadastro extends BaseTest {

	//private WebDriver driver;
	//private DSL dsl;
	private CadastrarPage page;
	
	@Parameter
	public String name;
	
	@Parameter(value=1)
	public String surname;
	
	@Parameter(value=2)
	public Object sexo;
	
	@Parameter(value=3)
	public List<String> comidas;
	
	@Parameter(value=4)
	public String[] sports;
	
	@Parameter(value=5)
	public String msg;
	
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
	
	/*@After // ==> Comentado pq vai passar a herdar da classe BaseTest
	public void finalizar() {
		//driver.quit();
		killDriver();
	}*/
	
	@Parameters
	public static Collection<Object[]> getCollection(){
		return Arrays.asList(new Object[][] {
							{"", "", "", Arrays.asList(), new String[] {}, "Nome eh obrigatorio" },
							{"Fulano", "", "", Arrays.asList(), new String[] {}, "Sobrenome eh obrigatorio"},
							{"Fulano", "da Silva", "", Arrays.asList(), new String[] {}, "Sexo eh obrigatorio"},
							{"Fulano", "da Silva", "Feminino", Arrays.asList("Carne", "Vegetariano"), new String[] {}, "Tem certeza que voce eh vegetariano?"},
							{"Fulano", "da Silva", "Feminino", Arrays.asList("Carne", "Pizza"), new String[]{"Corrida", "nada"}, "Voce faz esporte ou nao?"}
		});
	}
	
	@Test
	public void verificarEsporteSelecionado() {
		page.setName(name);
		page.setSurname(surname);
		
		if(sexo.equals("Masculino")) page.setSexMale();
		if(sexo.equals("Feminino")) page.setSexFemale();
		
		if(comidas.contains("Carne")) page.setFavoriteFoodCarne();
		if(comidas.contains("Pizza")) page.setFavoriteFoodPizza();
		if(comidas.contains("Vegetariano")) page.setFavoriteFoodVegetariano();

		page.setSport(sports);
	
		page.submit();
		Assert.assertEquals(msg, page.returnTextThenAcceptAlert());
	}
	
}