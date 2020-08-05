package br.com.selenium.test;

import org.junit.Assert;
import org.junit.Test;

import br.com.selenium.core.BaseTest;
import br.com.selenium.page.ChoiceItemPage;
import br.com.selenium.page.ManageCartPage;
import br.com.selenium.page.MenuPage;

public class ManageCart extends BaseTest {

	MenuPage menuPage = new MenuPage();
	ChoiceItemPage choicePage = new ChoiceItemPage();
	ManageCartPage manageCartPage = new ManageCartPage();
	
	@Test
	public void putItemOnCart() {
		menuPage.goInGameMenu(); 
		menuPage.goInGameLancamentosMenu();
		choicePage.waitForItem("Monster Hunter: Iceborne Xbox One");
		Assert.assertEquals("SAIBA MAIS", choicePage.findAvailableItemByShownName("Monster Hunter: Iceborne Xbox One"));
		choicePage.clickAvailableItemByShownName("Monster Hunter: Iceborne Xbox One");
		choicePage.waitForCep();
		choicePage.setCep("50060000");
		choicePage.calculateShipping();
		manageCartPage.clickBuyButton();
		Assert.assertTrue(manageCartPage.returnProductNameCart().contains("Monster Hunter: Iceborne Xbox One"));
	}
	
	@Test
	public void putItemOnCartAndDelete() {
		menuPage.goInGameMenu(); 
		menuPage.goInGameLancamentosMenu();
		choicePage.waitForItem("Monster Hunter: Iceborne Xbox One");
		Assert.assertEquals("SAIBA MAIS", choicePage.findAvailableItemByShownName("Monster Hunter: Iceborne Xbox One"));
		choicePage.clickAvailableItemByShownName("Monster Hunter: Iceborne Xbox One");
		choicePage.waitForCep();
		choicePage.setCep("50060000");
		choicePage.calculateShipping();
		manageCartPage.clickBuyButton();
		manageCartPage.clickDeleteItemLink();
		Assert.assertEquals("SEU CARRINHO ESTÁ VAZIO", manageCartPage.returnEmptyCartText());
	}
	
	@Test
	public void incrementItemQuantity() {
		menuPage.goInGameMenu(); 
		menuPage.goInGameLancamentosMenu();
		choicePage.waitForItem("Borderlands 3 - PS4");
		Assert.assertEquals("SAIBA MAIS", choicePage.findAvailableItemByShownName("Borderlands 3 - PS4"));
		choicePage.clickAvailableItemByShownName("Borderlands 3 - PS4");
		manageCartPage.clickBuyButton();
		manageCartPage.incrementItemQuantityCart();
		manageCartPage.waitOrderCalc();
		Assert.assertNotEquals("1", manageCartPage.quantityOfItem());
		// Para este teste a quantidade inicial de itens no carrinho sempre será 1
	}
	
}
