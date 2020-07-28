package br.com.selenium.test;

import org.junit.Assert;
import org.junit.Test;

import br.com.selenium.core.BaseTest;
import br.com.selenium.page.HomePage;
import br.com.selenium.page.MenuPage;

public class SaldoTest extends BaseTest {

	private HomePage homePage = new HomePage();
	private MenuPage menuPage = new MenuPage();
	
	@Test
	public void testBalanceAccount() {
		menuPage.goInHome();
		String balance = homePage.balanceAccount("Conta para saldo");
		Assert.assertEquals("534.00", balance);
	}
	
}