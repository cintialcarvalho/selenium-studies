package br.com.selenium.test;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import br.com.selenium.core.BaseTest;
import br.com.selenium.page.ChoiceItemPage;
import br.com.selenium.page.MenuPage;



public class ChoiceAnItem extends BaseTest {

	MenuPage menuPage = new MenuPage();
	ChoiceItemPage choicePage = new ChoiceItemPage();
	
	@Test
	public void accessAvailableItem() {
		menuPage.goInGameMenu(); 
		menuPage.goInGameLancamentosMenu();
		Assert.assertEquals("SAIBA MAIS", choicePage.findAvailableItemByShownName("Crash Team Racing Nitro-Fueled - Xbox One") );
	}
	
	@Test
	public void accessUnavailableItem() {
		menuPage.goInGameMenu(); 
		menuPage.goInGameLancamentosMenu();
		Assert.assertEquals("Fora do estoque", choicePage.findUnavailableItemByShownName("Nba 2K20 - PS4") );
	}
	
	@Ignore
	public void calculateShipping() {
		
	}
	
	
}

