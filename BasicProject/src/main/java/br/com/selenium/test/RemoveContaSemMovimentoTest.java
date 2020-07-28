package br.com.selenium.test;

import org.junit.Assert;
import org.junit.Test;

import br.com.selenium.core.BaseTest;
import br.com.selenium.page.AccountPage;
import br.com.selenium.page.MenuPage;

public class RemoveContaSemMovimentoTest extends BaseTest {
 
	@Test
	public void deleteAccountWithoutMoviment() {
		
		MenuPage menuPage = new MenuPage();
		AccountPage accountPage = new AccountPage();
		
		menuPage.goInListAccount();
		accountPage.clickAccountToDelete("Conta Para Deletar");
		Assert.assertEquals("Conta removida com sucesso!", accountPage.returnMessageSuccess());
	} 
	 
}
