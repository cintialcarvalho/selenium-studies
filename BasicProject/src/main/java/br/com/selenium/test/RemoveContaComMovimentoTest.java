package br.com.selenium.test;

import org.junit.Assert;
import org.junit.Test;

import br.com.selenium.core.BaseTest;
import br.com.selenium.page.AccountPage;
import br.com.selenium.page.MenuPage;

public class RemoveContaComMovimentoTest extends BaseTest {
	
	@Test
	public void deleteAccountWithMoviment() {
		
		MenuPage menuPage = new MenuPage();
		AccountPage accountPage = new AccountPage();
		
		menuPage.goInListAccount();
		accountPage.clickAccountToDelete("Conta com movimentacao");
		Assert.assertEquals("Conta em uso na movimentações", accountPage.returnMessageFail());
	}
	
}
