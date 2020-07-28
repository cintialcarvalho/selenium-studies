package br.com.selenium.test;
import static br.com.selenium.core.DriverFactory.getDriver;
import static br.com.selenium.core.DriverFactory.killDriver;

import java.util.Arrays;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;

import br.com.selenium.core.DSL;
import br.com.selenium.page.CadastrarPage;

public class TesteCampoTreinamentoRefatorado {

	//private WebDriver driver;
	private DSL dsl;
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
		
		dsl = new DSL();
		page = new CadastrarPage();
	}
	
	@After
	public void finalizar() {
		//driver.quit();
		killDriver();
	}
	
	
	@Test
	public void interagirTextFieldNome() {
		
		// Ao inves de usar 'driver.findElement(By.id("elementosForm:nome")).sendKeys("Teste de escrita")', chama o método criado na classe DSL
		// dsl.writeField("elementosForm:nome", "Teste de escrita");
		page.setName("Teste de escrita");
		
		// Substituí o 'driver.findElement(By.id("elementosForm:nome")).getAttribute("value")' dentro do 'Assert.assertEquals' pelo método da classe DSL
		// Assert.assertEquals("Teste de escrita", driver.findElement(By.id("elementosForm:nome")).getAttribute("value")); 
		Assert.assertEquals("Teste de escrita", page.returnValueName()); 
	
	}
	
	@Test
	public void interagirTextFieldDouble() {
		/*dsl.writeField("elementosForm:nome", "Escreve uma vez");
		Assert.assertEquals("Escreve uma vez", dsl.returnValueField("elementosForm:nome"));*/
		
		page.setName("Escreve uma vez");
		Assert.assertEquals("Escreve uma vez", page.returnValueName());
		
		/*dsl.writeField("elementosForm:nome", "Escreve outra vez");
		Assert.assertEquals("Escreve outra vez", dsl.returnValueField("elementosForm:nome"));*/
		
		page.setName("Escreve outra vez");
		Assert.assertEquals("Escreve outra vez", page.returnValueName());
		
	}
	
	@Test
	public void interagirTextAreaSugestoes() {
		
		//dsl.writeField("elementosForm:sugestoes", "Sugestoes aqui. \nPula Linha \nOutra Linha");
		page.setSugestions("Sugestoes aqui. \nPula Linha \nOutra Linha");
		Assert.assertEquals("Sugestoes aqui. \nPula Linha \nOutra Linha", page.returnValueSugestions());
		
	}
	
	
	@Test
	public void interagirRadioButtonSexo() {
		
		//dsl.clickRadioButton("elementosForm:sexo:0");
		page.setSexFemale();
		//Assert.assertTrue(dsl.isRadioButtonSelected("elementosForm:sexo:0"));
		Assert.assertTrue(page.returnIsSexFemale());
		
	}
	
	@Test
	public void interagirCheckBoxComida() {
		
		//dsl.clickCheckBox("elementosForm:comidaFavorita:2");
		page.setFavoriteFoodPizza();
		//Assert.assertTrue(dsl.isCheckBoxSelected("elementosForm:comidaFavorita:2"));
		Assert.assertTrue(page.returnIsFavoriteFoodPizza());

	}
	
	@Test
	public void interagirComboBoxEscolaridade() {
		
		//dsl.selectComboBox("elementosForm:escolaridade", "2graucomp");
		page.setSchoolDegree("2graucomp");
		Assert.assertEquals("2o grau completo", page.returnValueComboBoxSchool());

	}
	
	@Test
	public void interagirComboBoxValoresEscolaridade() {
	
		Assert.assertEquals(8, page.returnSizeComboboxSchool());
		Assert.assertTrue(page.returnFindValueComboSchool("Mestrado") );
		
	}
	
	@Test
	public void interagirComboBoxMultiploEsportes() {
		
		/*dsl.selectComboBox("elementosForm:esportes", "natacao");
		dsl.selectComboBox("elementosForm:esportes", "Corrida");
		dsl.selectComboBox("elementosForm:esportes", "nada");*/
		
		page.setSport("natacao", "Corrida", "nada");
		
		Assert.assertEquals(3, page.returnSizeSelectedComboSport());
		
		//Se a página vier com itens já marcados no combo e houver necessidade de desmarcar alguns itens iniciar ou concluir o teste
		//dsl.deselectCombo("elementosForm:esportes", "Corrida");
		page.setDeselectComboSport("Corrida");
		Assert.assertEquals(2, page.returnSizeSelectedComboSport());
		
		//Assert.assertTrue(dsl.returnAllSelectedValuesCombo("elementosForm:esportes").containsAll(Arrays.asList("Natacao", "O que eh esporte?")));
		Assert.assertTrue( page.returnAllSelectedValuesComboSport().containsAll(Arrays.asList("Natacao", "O que eh esporte?")));

	}
	
	@Test
	public void interagirComBotaoCliqueMe() {
		
		//dsl.clickButton("buttonSimple");
		page.clickButtonSimple();
		Assert.assertEquals("Obrigado!", dsl.returnValueButton("buttonSimple", "value"));

	}
	
	@Test
	@Ignore
	public void interagirComLinks() {

		dsl.clickLink("Voltar");
		
		// este teste não está concluído de propósito. 
		// Sempre que um teste ficar pela metado, é aconselhável usar a notaçao @Ignore para que o teste não seja 
		// executado. Porque caso ele seja executado e fique com barra verde, provavelmente a pessoa vai esquecer de completar o teste.
		// A notaçao @Ignore pula a execuçao do teste porém informa que ele foi pulado da execuçao.
		
		// A conclusão deste método está no Método interagirComLinkVoltar()
		
	}
	
	@Test
	public void buscarTextosNaPagina() {

		// traz todos os textos visíveis na página --> ver console
		//System.out.println(driver.findElement(By.tagName("Body")).getText()); 
		
		// ao trazer todos os textos da página, o CONTAINS localiza o 'Campo Treinamento'. Esta não é uma prática performática pq terá que varrer td o texto.
		//driver.findElement(By.tagName("Body")).getText().contains("Campo de Treinamento"); 
		
		// apenas para concluir o teste, mas NÃO é uma boa prática o uso do contains varrendo todas as palavras retornadas
		Assert.assertTrue(dsl.returnText(By.tagName("Body")).contains("Campo de Treinamento"));
		
		//A melhor opçao para localizar um texto dentro de uma página é procurar sempre pelo ID de uma tag, de uma tabela ou celula, DIV, span, class, etc....
		//Porém o teste irá parar no primeiro item que ele encontrar no html (ex.: <span>). Então é sempre bom usar algo que de fato seja único na página 
		
		//Usando tagName. 
		//Neste caso o texto que será localizado está dentro da tag <h3> mas NÃO garante que o texto trazido nesta tag será o correto pq ela pode não ser única
		Assert.assertEquals("Campo de Treinamento", dsl.returnText(By.tagName("h3")));
		
		//Usando className <=====
		//Este teste garante que este elemento é único na página HTML
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", dsl.returnText(By.className("facilAchar")));

	}
	
	@Test
	//Conclusao do método interagirComLinks()
	//utilizando o findElement By.id para localizar o texto na página
	public void interagirComLinkVoltar() {
		
		//dsl.clickLink("Voltar");
		page.clickLinkVoltar();
		Assert.assertEquals("Voltou!", dsl.returnText("resultado"));
		
	}
	
	@Test
	public void deveClicarBotaoTabela() {
		
		dsl.clicarBotaoTabela("Nome", "Maria", "Botao", "elementosForm:tableUsuarios");
		Assert.assertEquals("Maria", dsl.returnTextThenAcceptAlert());
		
		dsl.clicarBotaoTabela("Escolaridade", "Mestrado", "Botao", "elementosForm:tableUsuarios");
		Assert.assertEquals("Usuario A", dsl.returnTextThenAcceptAlert());
		
		dsl.clicarBotaoTabela("Escolaridade", "Mestrado", "Radio", "elementosForm:tableUsuarios");
		
	}
	
}
