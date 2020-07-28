package br.com.selenium.page;

import org.openqa.selenium.By;

import br.com.selenium.core.BasePage;

public class AccountPage extends BasePage {

	
	public void setName(String name) {
		writeField("nome", name);
	}
	
	public void saveAccount() {
		clickButton(By.xpath("//button[.='Salvar']"));
	}
	
	public String returnMessageSuccess() {
		return returnText(By.xpath("//div[@class='alert alert-success']"));
	}
	
	public String returnMessageFail() {
		return returnText(By.xpath("//div[@class='alert alert-danger']"));
	}
	
	// Esta classe faz a mesma coisa que a clickAccountToUpdate, porém de um jeito diferente
	public void selectAccountToUpdate(String accountName) {
		clickLink(By.xpath("//*[@id='tabelaContas']/.//./td[.='"+ accountName +"']/..//span[@class='glyphicon glyphicon-edit']"));
	}
	
	// A classe selectAccountToUpdate faz a mesma coisa que esta faz, porém de uma maneira diferente
	public void clickAccountToUpdate(String accountName) {
		returnCellTable("Conta", accountName, "Ações", "tabelaContas").findElement(By.xpath(".//span[@class='glyphicon glyphicon-edit']")).click();
	}
	
	public void clickAccountToDelete(String accountName) {
		returnCellTable("Conta", accountName, "Ações", "tabelaContas").findElement(By.xpath(".//span[@class='glyphicon glyphicon-remove-circle']")).click();
	}
	
}
