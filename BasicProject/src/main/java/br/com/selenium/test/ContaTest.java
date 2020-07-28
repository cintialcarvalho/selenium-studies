package br.com.selenium.test;

import org.junit.Assert;
import org.junit.Test;

import br.com.selenium.core.BaseTest;
import br.com.selenium.page.AccountPage;
import br.com.selenium.page.MenuPage;


//@FixMethodOrder(MethodSorters.NAME_ASCENDING) // Nao precisa desta linha caso esteja usando teste em paralelo
public class ContaTest extends BaseTest {

	MenuPage menuPage = new MenuPage();
	AccountPage accountPage = new AccountPage();
	
	@Test
	public void test_1_InsertAccount() {
		menuPage.goInInsertAccount();
		accountPage.setName("Conta teste 4");
		accountPage.saveAccount();
		Assert.assertEquals("Conta adicionada com sucesso!", accountPage.returnMessageSuccess());
	}
	
	@Test
	public void test_2_InsertAccountSameName() {
		menuPage.goInInsertAccount();
		accountPage.setName("Conta mesmo nome");
		accountPage.saveAccount();
		Assert.assertEquals("Já existe uma conta com esse nome!", accountPage.returnMessageFail());

	}
	
	@Test
	public void test_3_UpdateAccount() {
		menuPage.goInListAccount();
		// Pode-se usar qualquer um dos métodos abaixo:
		//accountPage.selectAccountToUpdate("any one alterada"); //Esse método utiliza o clickLink(), passando o Xpath do botao Update Conta
		accountPage.clickAccountToUpdate("Conta para alterar"); //Esse método utiliza o returnCellTable() e passa apenas parte do Xpath do botão Update Conta
		accountPage.setName("Conta alterada");
		accountPage.saveAccount();
		Assert.assertEquals("Conta alterada com sucesso!", accountPage.returnMessageSuccess());
	}
	
	@Test
	public void test_4_UpdateAccountSameName() {
		menuPage.goInListAccount();
		accountPage.selectAccountToUpdate("Conta para saldo"); //Esse método utiliza o clickLink(), passando o Xpath do botao Update Conta
		accountPage.setName("Conta mesmo nome");
		accountPage.saveAccount();
		Assert.assertEquals("Já existe uma conta com esse nome!", accountPage.returnMessageFail());
	}

}