package br.com.selenium.test;

import org.junit.Assert;
import org.junit.Test;

import br.com.selenium.core.BaseTest;
import br.com.selenium.page.MenuPage;

public class FindItemByClick extends BaseTest {

	MenuPage menuPage = new MenuPage();
	
	@Test
	public void accessGameItemMenu() {
		menuPage.goInComprePorCategoriaMenu(); 
		menuPage.goInGameMenu(); 
		menuPage.goInGameLancamentosMenu();
		Assert.assertEquals("Lançamento de Games | Saraiva", menuPage.returnTextTitlePage());
		
		// O assert teria que comparar se o site está na página 'Lançamentos', mas o único elemento com essa informação é a tag TITLE.
		// Foram realizados vários testes mas não foi possível retornar o texto presente na tag title.
		// A tag é detectável pelo driver mas sempre retorna a informação null, porém o texto existe. 
		// Observando pelo debug, percebi que ao utilizar a instruçao para capturar o texto, era como se a instrução estivesse ATRIBUINDO ã tag o valor null 
		// ao invés de capturar o texto contigo na tag, por isso o retorno é null.
	}
	
	@Test
	public void findItemByClick() {
		menuPage.goInComprePorCategoriaMenu(); 
		menuPage.goInGameMenu(); 
		menuPage.goInGameLancamentosMenu();
		Assert.assertEquals("Team Sonic Racing - PS4", menuPage.findItemByShownName("Team Sonic Racing - PS4") );
	}
	
	
	// Este método foi concluído após pesquisas devido ao elemento do campo de busca na página ser do tipo #shadowRoot, indetectável para o Jnit mesmo via XPath.
	// O elemento é o '#shadow-root (open)' na tag '<linx-impulse-autocomplete></linx-impulse-autocomplete>'
	// A soluçao foi encontrada na página do stackoverflow
	// Link: https://stackoverflow.com/questions/56380091/how-to-interact-with-the-elements-within-shadow-root-open-while-clearing-brow
	@Test
	public void findItemBySearch() {
		menuPage.setSearchField("Culinária Expressa");
		menuPage.search();
 	    Assert.assertTrue(menuPage.findItemByShownName("Culinária Expressa").contains("Culinária Expressa"));
	}
	
}
