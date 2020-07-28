package br.com.selenium.page;

import org.openqa.selenium.By;

import br.com.selenium.core.BasePage;


public class ResumoMensalPage extends BasePage {

	
	public void deleteAccountMoviment(String valorColuna) {
		clickButtonTable("Descrição", valorColuna, "Ações", "tabelaExtrato", "span");
	}
	 
	public String returnMessageSuccess() {
		return returnText(By.xpath("//div[@class='alert alert-success']"));
	}
	
	public boolean returnEmptyTableMoviment() {
		
		// Forma 1: Uma forma é buscando se existe ou nao alguma coisa dentro da tag Body. Neste caso, se está vazio.
		return returnText(By.xpath("//*[@id='tabelaExtrato']//tbody")).isEmpty(); // Neste caso, o retorno precisa ser um boolean
		
		// Forma 2: Outra forma de fazer a verificaçao é ver se a tabela está vazia ou nao. Ou seja, buscando se tem algum TR(linha) na tabela, abaixo do body
		//List<WebElement> foundElements = DriverFactory.getDriver().findElements(By.xpath("//*[@id='tabelaExtrato']//tbody/tr"));
		//return foundElements.size(); // Neste caso, o retorno deve ser INT.
	}
	
	public void selectComboAno(String ano) {
		selectComboBoxById("ano", ano);
	}
	
	public void clickBuscar() {
		clickButton(By.xpath("//input[@value='Buscar']"));
	}
	
}