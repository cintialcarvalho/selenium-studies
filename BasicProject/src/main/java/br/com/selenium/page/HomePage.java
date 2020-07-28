package br.com.selenium.page;

import br.com.selenium.core.BasePage;

public class HomePage extends BasePage {

	public String balanceAccount(String accountName) {
		return returnTextTable("Conta", accountName, "Saldo", "tabelaSaldo");
	}
	
}
