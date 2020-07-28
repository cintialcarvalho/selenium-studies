package br.com.selenium.test;
import static br.com.selenium.core.DriverFactory.getDriver;
import static br.com.selenium.core.DriverFactory.killDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import br.com.selenium.core.DSL;
import br.com.selenium.page.CadastrarPage;

//UTILIZANDO A ARQUITEURA POM - PAGE OBJECT MODEL

public class TesteFramesRefatorado {
	
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
	public void interagirComFrame() {
		
		//dsl.enterFrame("frame1");
		//dsl.clickButton("frameButton");
		
		page.changeToFrame1();
		page.clickFrameButton();
		
		String msgAlertFrame = page.returnTextThenAcceptAlert();
		Assert.assertEquals("Frame OK!", msgAlertFrame);
		page.exitFrame(); // volta o foco para a página principal
		//dsl.writeField("elementosForm:nome", msgAlertFrame);
		page.setName(msgAlertFrame);
		
	}
	
	@Test
	// Interagir com janela
	public void interagirComPopup() {
		
		//dsl.clickButton("buttonPopUpEasy");
		//dsl.changeWindow("Popup");
		
		page.clickButtonPopUpEasy();
		page.changeWindowPopUp();
		
		dsl.writeField(By.tagName("textarea"), "Escrevendo no textArea do PopUp");
		dsl.changeWindow(""); // Volta para a página principal. Neste caso a página náo tem identificador, por isso foi usado só aspas
		//driver.switchTo().defaultContent(); // NÃO PODE USAR ESTE ITEM pq neste caso não é quesão de setar foco e sim de alterar janelas
				
		//dsl.writeField("elementosForm:sugestoes", "Escrevendo no TextArea da Página principal para finalizar o teste.");
		page.setSugestions("Escrevendo no TextArea da Página principal para finalizar o teste.");
		
	}
	
	@Test
	// Executando comando Javascript
	public void interagirComFrameEscondido() {
		//OBS: As duas linhas abaixo serve para rolar a tela para o final da página.
		//utilizando o Chrome, ao localizar o elemento frame2, o próprio navegador rola a tela para baixo. Nao sendo necessária a utilizacao deste código.
		//Porém utilizando o FireFox, o navegador NÃO rola a tela para o final da página e NÃO consegue localizar o elemento frame2, ocasionando erro.
		//Pode-se utilizar o código abaixo como padrão para que não ocorra erro independente do navegador que está sendo usado.
		WebElement frame = getDriver().findElement(By.id("frame2"));
		dsl.executarJS("window.scrollBy(0, arguments[0])", frame.getLocation().y);
		
		dsl.enterFrame("frame2");
		dsl.clickButton("frameButton");
		String textAlert = dsl.returnTextThenAcceptAlert();
		Assert.assertEquals("Frame OK!", textAlert);
		
	}
	
	
	@Test
	public void interagirComPopupSemIdentificador() {

		dsl.clickButton("buttonPopUpHard");
		
		System.out.println(getDriver().getWindowHandle()); // mostra o identificador gerenciado pelo selenium da janela principal
		System.out.println(getDriver().getWindowHandles()); // mostra um ARRAY com os identificadores de todas as janelas abertas daquela instancia
		
		// Mudando o foco para o Popup que não tem identificador no html. Por isso foi usada a funcao getWindowHandles()
		dsl.changeWindow((String) getDriver().getWindowHandles().toArray()[1]); // teve que fazer um cast para String pq o retorno do getWindowHandles() é um Array
		dsl.writeField(By.tagName("textarea"), "Escrevendo no TextArea do Popp sem identificador");

		// Voltando para a página principal
		dsl.changeWindow((String) getDriver().getWindowHandles().toArray()[0]);
		dsl.writeField("elementosForm:sugestoes", "Escrevendo no TextArea da janela principal para terminar o teste.");
		
	}
	
}