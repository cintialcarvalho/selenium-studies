package br.com.selenium.page;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import br.com.selenium.core.BasePage;
import br.com.selenium.core.DriverFactory;

public class MovimentPage extends BasePage {

	public void setTypeMoviment(String value) {
		selectComboBoxById("tipo", value);
	}
	
	public void setDateMoviment(String dateMoviment) {
		writeField("data_transacao", dateMoviment);
	}
	
	public void setDatePayment(String datePayment) {
		writeField("data_pagamento", datePayment);
	}
	
	public void setDetails(String details) {
		writeField("descricao", details);
	}
	
	public void setPersonInterested(String setInterestedPerson) {
		writeField("interessado", setInterestedPerson);
	}
	
	public void setValue(String value) {
		writeField("valor", value);		
	}
	
	public void selectAccountById(String account) {
		selectComboBoxById("conta", account);
	}
	
	public void selectAccountByVisibleText(String visibleText) {
		selectComboBoxByVisibleText("conta", visibleText);
	}
	
	public void setSituationPaid() {
		clickRadioButton("status_pago");
	}
	
	public void setSituationPending() {
		clickRadioButton("status_pendente");
	}
	
	public void saveMoviment() {
		clickButton(By.xpath("//button[.='Salvar']"));
	}
	
	public String returnMessageSuccess() {
		return returnText(By.xpath("//div[@class='alert alert-success']"));
	}
	
	public String returnMessageFail() {
		return returnText(By.xpath("//div[@class='alert alert-danger']"));
	}
	
	public String returnMessageMovimentValidation(String msg) {
		return returnText(By.xpath("//div[@class='alert alert-danger']/.//li[.='" + msg + "']")); 
	}
	
	public List<String> returnAllFailMessagesMoviment(){
		
		List<WebElement> errorMsgs = DriverFactory.getDriver().findElements(By.xpath("//div[@class='alert alert-danger']/.//li"));
		List<String> returnList = new ArrayList<String>();
		
		for(WebElement errorMsg : errorMsgs){
			returnList.add(errorMsg.getText());
		}
		return returnList;
	}
	
}
