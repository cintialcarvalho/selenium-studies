package br.com.selenium.test;
import static br.com.selenium.core.DriverFactory.getDriver;
import static br.com.selenium.core.DriverFactory.killDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import br.com.selenium.core.DSL;

// utilizando uma página escrita em PrimeFaces

public class TestePrime {

	//private WebDriver driver;
	private DSL dsl;
	
	@Before
	public void inicializar() {
	    //driver = new ChromeDriver();
		//driver.manage().window().setSize(new Dimension(1200, 865)); ==> Essas duas linhas nao precisam mais estar aq pq foi criada a classe DriverFactory
		//driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html"); ==> Esta linha foi substituida pela linha abaixo
		
		getDriver().get("https://www.primefaces.org/showcase/ui/input/oneRadio.xhtml");	 // acessa diretamente pq está no import acima
		
		/* Nao usa mais os parametros por causa da criacao da classe DriverFactory
		dsl = new DSL(driver);*/
		dsl = new DSL();

	}
	
	@After
	public void finalizar() {
		//driver.quit();
		killDriver();
	}
	
	@Test
	public void interagirComRadioPrime() {
		// Xpath do radio: //*[@id="j_idt726:console:0"]
		//dsl.clickRadioButton("j_idt726:console:0"); // nao funciona desta forma pq o input está dentro de uma div class HIDDEN
		
		//Xpath da span que marca o radio //*[@id="j_idt726:console"]/tbody/tr/td[1]/div/div[2]/span , segue abaixo xpath mais generico
		//Xpath generico: //input[@id='j_idt726:console:0']/../..//span
		dsl.clickRadioButton(By.xpath("//input[@id='j_idt726:console:0']/../..//span"));
		Assert.assertTrue(dsl.isRadioButtonSelected("j_idt726:console:0"));
		
		// Para o caso do uso do PrimeFace com ID's gerados automaticamente, o ideal é que haja alteraçao de código incluindo os ID's nos elementos
		// porque caso a pagina tenha sua estrutura atualizada, os ID's automaticos dos elementos também irão mudar, fazendo com que o teste quebre.
		// Quando não se sabe quem desenvolveu ou nao se tem acesso, pode utilizar o XPath procurando por algo mais fixo, ex.: O texto visivel na tela.
		
		// XPath generico para procurar pelo texto PS4: //*[@id="j_idt726:console"]/tbody/tr/td[2]/label 
		// XPath mais genérica: //label[.='PS4']/..//span
		dsl.clickRadioButton(By.xpath("//label[.='PS4']/..//span"));
		Assert.assertTrue(dsl.isRadioButtonSelected("j_idt726:console:1"));
		
	}
	
	@Test
	public void interagirComComboPrime() {
		getDriver().get("https://www.primefaces.org/showcase/ui/input/oneMenu.xhtml");	
		
		// xpath da opcao PS4 do combo: //label[.='PS4']/../../div/label
		// Porem por ser um combo é preciso primeiro clicar na setinha do combo para abri-lo
		// dsl.selectComboBox("//label[.='"+value+"']/../../div/label", "PS4"); --> portanto isto nao vai funcionar pq nao iremos interagir com o elemento combo
		
		// Tem que procurar o xpath na setinha do combo para abrir:
		// Xpath da parte clicável do combo que faz ele abrir as opcoes: //*[@id='j_idt726:console']/../..//span
		dsl.clickButton(By.xpath("//*[@id='j_idt726:console_input']/../..//span"));
		
		// xpath do item a ser clicado dentro da estrutura que monta do combo mas em html: //*[@id='j_idt726:console_items']//li[.='PS4']
		dsl.clickButton(By.xpath("//*[@id='j_idt726:console_items']//li[.='PS4']"));
		
		// Para verificar se realmente foi selecionado, também nao verificamos pelo combo. Tem que pesquisar onde e como o texto esta sendo exibido
		// xpath do texto selecionado no combo: //*[@id='j_idt726:console_label'] ==> está sendo exibido através de uma label
		Assert.assertEquals("PS4", dsl.returnText("j_idt726:console_label"));
	}
	
	
	
}
