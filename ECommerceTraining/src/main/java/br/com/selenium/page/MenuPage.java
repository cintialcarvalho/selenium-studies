package br.com.selenium.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import br.com.selenium.core.BasePage;
import br.com.selenium.core.DriverFactory;

public class MenuPage extends BasePage {

	public void goInWebSite() {
		DriverFactory.getDriver().get("https://www.saraiva.com.br");
	}
	
	public void goInComprePorCategoriaMenu() {
		clickLink(By.xpath("//*[@id='custommenu-toggle-categorias-01']")); 
	}
	
	public void goInGameMenu() {
		// O menu abaixo de 'categorias' fica desabilitado e só é habilitado quando há a interação do mouse com o menu, fazendo com que o menu 'se mova'.
		// Por mais que o elemento esteja na página e que o JUnit encontre-o pelo findElement, não é possível interagir com o elemento (ul).
		// Assim, antes de clicar no menu 'games', é necessário habilitá-lo alterando o style da tag 'ul' de 'display:nome' para 'display:block' 
		executarJS("arguments[0].style = arguments[1]", returnWebElement(By.xpath("//*[@id='menu-games']/../../../ul[@class='nav__links']")), "display:block; ");
		clickLink(By.xpath("//*[@id='menu-games']/span")); 
	}
	
	public void goInGameLancamentosMenu() {
		executarJS("arguments[0].style = arguments[1]", returnWebElement(By.xpath("//*/div[@class='sub-item sub-item-slug__games']")), "display:block; ");
		clickLink(By.xpath("//*[@id='submenu-consoles-e-games-lancamentos-01']/..")); 
	}
		
	public String returnTextTitlePage() {
		return executarJS("return document.title").toString();
		
		/* Por algum motivo a funcao (abaixo) do Junit está sempre retornando NULL porém a tag TITLE existe e possui texto
		   Log apresentado: org.junit.ComparisonFailure: expected:<[Lançamento de Games | Saraiva]> but was:<[]>
		   Por este motivo estou utilizando a chamada em JS acima */
		
		// return returnText(By.tagName("title"));
	}
	
	public String findItemByShownName(String item) {
		waitFor(By.xpath("//*[@id=\"app\"]//../a[@class='nm-product-name']"), 10);
		return returnText(By.xpath("//*/././a[.='"+item+"']"));
	}	
	
	public void setSearchField(String valueToWrite) { // Ver método na classe Page para detalhes
		writeOnShadowElement("linx-impulse-autocomplete", "#search-form > input", valueToWrite);
	}
	
	public void search() { // Ver método na classe Page para detalhes
		clickOnShadowElementButton("linx-impulse-autocomplete", "#search-form > button");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// livraria cultura - futura iteracao
	/*public void goInWebSite() {
		DriverFactory.getDriver().get("https://www3.livrariacultura.com.br");
	}
	
	public void goInComprePorCategoriaMenu() {
		clickLink(By.xpath("//*[@class='menu-item __categories']")); 
	}
	
	public void goInGeekHqsLivrosMenu(String itemMenu) {
		clickLink(By.xpath("//*[@id='menuff-musicas-c']/..//li[.='"+itemMenu+"']"));
		//clickLink(By.xpath("//..//li[@id='menuf-geek']/..//div[@class='menu-submenu']/.//li//a[.='Hqs e Livros']/..//li[.='"+itemMenu+"']"));
		//li[@class='menu-item __categories']//li[@id='menuf-geek']//div[@class='menu-submenu']/.//li//a[.='Hqs e Livros']/..//a[.='Fantasia']
		//li[@class='menu-item __categories']/..//li[@id='menuf-geek']/..//div[@class='menu-submenu']/.//li//a[.='Hqs e Livros']/..//a[.='Ficção Científica']
		//*[@id='menuff-musicas-c']/..//li[.='Lego']
	}

	public void goInGeekJogosBrinquedosMenu(String itemMenu) {
		clickLink(By.xpath("//*[@id='menuff-musicas-c']/..//li[.='"+itemMenu+"']/a"));
		//clickLink(By.xpath("//li[@class='menu-item __categories']/..//li[@id='menuf-geek']/..//div[@class='menu-submenu']/.//li//a[.='Jogos e Brinquedos']/..//a[.='"+itemMenu+"']"));
		//*[@id='menuff-musicas-c']/..//li[.='Lego']
	}
	
	
	public void goInGeekFilmesSeriesMenu(String itemMenu) {
		clickLink(By.xpath("//li[@class='menu-item __categories']/..//li[@id='menuf-geek']/..//div[@class='menu-submenu']/.//li//a[.='Filmes e Séries']/..//a[.='"+itemMenu+"']"));
		//*[@id='menuff-geek-d']/..//li[.='Canecas']
	}
	
	public void goInGeekETCMenu(String itemMenu) {
		clickLink(By.xpath("//li[@class='menu-item __categories']/..//li[@id='menuf-geek']/..//div[@class='menu-submenu']/.//li//a[.='ETC']/..//a[.='"+itemMenu+"']"));
		//*[@id='menuff-geek-d']/..//li[.='Canecas']
	}	*/
	
}