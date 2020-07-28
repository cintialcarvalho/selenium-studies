package br.com.selenium.page;

import br.com.selenium.core.BasePage;

public class MenuPage extends BasePage {

	public void goInInsertAccount() {
		clickLink("Contas");
		clickLink("Adicionar");
	}
	
	public void goInListAccount() {
		clickLink("Contas");
		clickLink("Listar");
	}
	
	public void goInMovimentAccount() {
		clickLink("Criar Movimentação");
	}

	public void goInResumoMensal() {
		clickLink("Resumo Mensal");		
	}
	
	public void goInHome() {
		clickLink("Home");		
	}
	
}
