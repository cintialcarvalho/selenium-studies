package br.com.selenium.test;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;


// OBS: Esta classe foi refatorada com BOAS PRÁTICAS na classe TesteCampoTreinamentoRefatorado
public class TesteCampoTreinamento {

	@Test
	public void interagirTextFieldNome() {
		
		//WebDriver driver = new FirefoxDriver();
		WebDriver driver = new ChromeDriver(); // Instanciar drive
		driver.manage().window().setSize(new Dimension(1200, 865));	// abrir janela
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html"); // abrir pagina html
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Teste de escrita"); // achar elemento e escrever no campo
		Assert.assertEquals("Teste de escrita", driver.findElement(By.id("elementosForm:nome")).getAttribute("value")); // verificar
	
		driver.quit(); // fechar janela
	}
	
	
	@Test
	public void interagirTextAreaSugestoes() {
		
		WebDriver driver = new ChromeDriver(); // A soluçao para a repeticao dessas 3 linhas está na classe TestarRegrasFormulario
		driver.manage().window().setSize(new Dimension(1200, 865));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("Sugestoes aqui. \nPula Linha \nOutra Linha");
		Assert.assertEquals("Sugestoes aqui. \nPula Linha \nOutra Linha", driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));
		
		driver.quit(); // A soluçao para a repetiçao desta linha está na classe TestarRegrasFormulario
		
	}
	
	
	@Test
	public void interagirRadioButtonSexo() {
		
		WebDriver driver = new ChromeDriver();  // A soluçao para a repeticao dessas 3 linhas está na classe TestarRegrasFormulario
		driver.manage().window().setSize(new Dimension(1200, 865));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
		
		driver.quit();
		
	}
	
	@Test
	public void interagirCheckBoxComida() {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200, 865));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
		Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:2")).isSelected());
		
		driver.quit();
		
	}
	
	@Test
	public void interagirComboBoxEscolaridade() {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200, 865));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		
		Select combo = new Select(element);
		combo.selectByIndex(3); 							// ou assim, pelo index
		//combo.selectByValue("2graucomp"); 				// ou assim, pelo value
		//combo.selectByVisibleText("2o grau completo"); // ou assim, pelo valor que fica visível para o usuário
		Assert.assertEquals("2o grau completo", combo.getFirstSelectedOption().getText());
		
		driver.quit();
		
	}
	
	@Test
	public void interagirComboBoxValoresEscolaridade() {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200, 865));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		Assert.assertEquals(8, options.size());
		
		boolean encontrou = false;
		for(WebElement currentOption: options ) {
			if(currentOption.getText().equals("Mestrado")) {
				encontrou = true;
				break;
			}
		}
		Assert.assertTrue(encontrou);
		
		driver.quit();
		
	}
	
	@Test
	public void interagirComboBoxMultiploEsportes() {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200, 865));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		WebElement element = driver.findElement(By.id("elementosForm:esportes"));
		
		Select combo = new Select(element);
		combo.selectByVisibleText("Natacao");
		combo.selectByVisibleText("Corrida");
		combo.selectByVisibleText("O que eh esporte?");
		
		List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
		Assert.assertEquals(3, allSelectedOptions.size());
		
		//Se a página vier com itens já marcados no combo e houver necessidade de desmarcar alguns itens iniciar ou concluir o teste
		combo.deselectByVisibleText("Corrida"); // repete qnts quiser ou usa o deselectAll() se quiser desmarcar tudo
		allSelectedOptions = combo.getAllSelectedOptions(); // já que desmarcou um item, recebe de novo os itens selecionados
		Assert.assertEquals(2, allSelectedOptions.size());  // verifica novamente a qtd de itens selecionados
		
		driver.quit();
		
	}
	
	@Test
	public void interagirComBotaoCliqueMe() {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200, 865));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		// ou usa o findElement nas duas linhas abaixo
		//driver.findElement(By.id("buttonSimple")).click();
		//Assert.assertEquals("Obrigado!", driver.findElement(By.id("buttonSimple")).getAttribute("value"));
		
		//ou cria uma variável botao do tipo WebElement
		WebElement botao = driver.findElement(By.id("buttonSimple"));
		botao.click();
		Assert.assertEquals("Obrigado!", botao.getAttribute("value"));
		
		driver.quit();
		
	}
	
	@Test
	@Ignore
	public void interagirComLinks() {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200,865));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		driver.findElement(By.linkText("Voltar")).click();
		
		// este teste não está concluído de propósito. 
		// Sempre que um teste ficar pela metado, é aconselhável usar a notaçao @Ignore para que o teste não seja 
		// executado. Porque caso ele seja executado e fique com barra verde, provavelmente a pessoa vai esquecer de completar o teste.
		// A notaçao @Ignore pula a execuçao do teste porém informa que ele foi pulado da execuçao.
		
		// A conclusão deste método está no método interagirComLinkVoltar()
		
		driver.quit();
		
	}
	
	@Test
	public void buscarTextosNaPagina() {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200, 865));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		// traz todos os textos visíveis na página --> ver console
		//System.out.println(driver.findElement(By.tagName("Body")).getText()); 
		
		// ao trazer todos os textos da página, o CONTAINS localiza o 'Campo Treinamento'. Esta não é uma prática performática pq terá que varrer td o texto.
		//driver.findElement(By.tagName("Body")).getText().contains("Campo de Treinamento"); 
		
		// apenas para concluir o teste, mas NÃO é uma boa prática
		Assert.assertTrue(driver.findElement(By.tagName("Body")).getText().contains("Campo de Treinamento"));
		
		//A melhor opçao para localizar um texto dentro de uma página é procurar sempre pelo ID de uma tag, de uma tabela ou celula, DIV, span, class, etc....
		//Porém o teste irá parar no primeiro item que ele encontrar no html (ex.: <span>). Então é sempre bom usar algo que de fato seja único na página 
		
		//Usando tagName. 
		//Neste caso o texto que será localizado está dentro da tag <h3> mas NÃO garante que o texto trazido nesta tag será o correto pq ela pode não ser única
		Assert.assertEquals("Campo de Treinamento", driver.findElement(By.tagName("h3") ).getText() );
		
		
		
		// Tentando pegar o conteúdo da tag Title, porém usando JavaScript.
		// Nao funcionou pq aparentemente o java script está atribuindo null ao inves de capturar o texto que já está na tag title. Entao o return é sempre null.
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.title ='blah'");
		
		//WebElement title = driver.findElement(By.xpath("//head/title")) ;
		//String titulo2 = (String) js.executeScript("arguments[0].text", title);
		//Assert.assertEquals("blah",  titulo2);
		
		String titulo;
		titulo = (String) js.executeScript("document.getElementsByTagName('TITLE')[0]");
		//titulo = (String) js.executeScript("document.getElementsByTagName('TITLE')[0].text");
				 //(String) js.executeScript("document.title.textContent");
		Assert.assertEquals("blah",  titulo);
		
		
		
		
		//Usando className <=====
		//Este teste garante que este elemento é único na página HTML
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", driver.findElement(By.className("facilAchar")).getText());
		
		driver.quit();
		
	}
	
	@Test
	//Conclusao do método interagirComLinks()
	//utilizando o findElement By.id para localizar o texto na página
	public void interagirComLinkVoltar() {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200, 865));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		driver.findElement(By.linkText("Voltar")).click();
		Assert.assertEquals("Voltou!", driver.findElement(By.id("resultado")).getText());
		
		driver.quit();
		
	}
	
	
}