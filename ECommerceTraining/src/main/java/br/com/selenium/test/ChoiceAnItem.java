package br.com.selenium.test;

import static br.com.selenium.core.DriverFactory.getDriver;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
	
	@Test
	public void calculateShipping() {
		menuPage.goInGameMenu(); 
		menuPage.goInGameLancamentosMenu();
		choicePage.waitForItem("Monster Hunter: Iceborne Xbox One");
		Assert.assertEquals("SAIBA MAIS", choicePage.findAvailableItemByShownName("Monster Hunter: Iceborne Xbox One"));
		choicePage.clickAvailableItemByShownName("Monster Hunter: Iceborne Xbox One");
		choicePage.waitForCep();
		choicePage.setCep("50060000");
		choicePage.calculateShipping();
		Assert.assertEquals("Receba em casa",choicePage.returnShippingRecebeEmCasa());
	}
	
}

