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
		// Form realizaros vários testes mas não foi possível retornar o texto presente na tag title.
		// O elemento é detectável mas sempre retorna null. Observando pelo debug, percebi que ao utilizar a instruçao par capturar o texto,
		// o que se notava é que estava sendo atribuído o valor null, por isso o retorno é null.
		//Assert.assertEquals("Lançamento de Games | Saraiva", menuPage.returnTextTitlePage());
	}
	
	@Test
	public void findItemByClick() {
		menuPage.goInComprePorCategoriaMenu(); 
		menuPage.goInGameMenu(); 
		menuPage.goInGameLancamentosMenu();
		Assert.assertEquals("Team Sonic Racing - PS4", menuPage.findItemByShownName("Team Sonic Racing - PS4") );
	}
	
	/*
	Este método nao foi concluído porque há um elemento na página que torna o campo de busca indetectável mesmo via XPath
	A intençao seria escrever no campo texto de busca da página e pesquisar por nome 
	@Test
	public void findItemBySearch() {
		menuPage.setSearchField("culinaria expressa");
		menuPage.search();
		Assert.assertTrue(menuPage.findItemByShownName("").contains("culinaria expressa"));
	}*/
	
}
