package br.com.selenium.page;
import org.openqa.selenium.By;

import br.com.selenium.core.BasePage;

public class ChoiceItemPage extends BasePage {

	
	public void setCep(String value) {
		writeField("input-frete-pdp", value);
	}
	
	public String findAvailableItemByShownName(String item) {
		return returnText(By.xpath("//*/././a[.='"+item+"']/../../div[@class='product__actions']//span"));
	}	
	
	public void clickAvailableItemByShownName(String item) {
		clickLink(By.xpath("//*/././a[.='"+item+"']/../../div[@class='product__actions']//span"));
	}
	
	public String findUnavailableItemByShownName(String item) {
		return returnText(By.xpath("//*/././a[.='"+item+"']/../../div[@class='product__actions block-no-stock']/div"));
	}
	
	public void calculateShipping() {
		clickButton("btn-frete-pdp");
	}
	
	public void waitForItem(String item) {
		waitFor(By.xpath("//*/././a[.='"+item+"']/../../div[@class='product__actions']//span"), 5); 
	}
	
	public void waitForCep() {
		//WebDriverWait wait = new WebDriverWait(getDriver(), 10); // Tempo de espera em segundos: 10 segundos, ou menor
		//wait.until(ExpectedConditions.presenceOfElementLocated(By.id("input-frete-pdp"))); 
		waitFor(By.id("input-frete-pdp"), 10); 
	}
	
	public String returnShippingRecebeEmCasa() {
		waitFor(By.xpath("//*[@id='receba-em-casa']//../..//tbody[@id='include-receba-em-casa']/tr"), 10); // Tempo de espera em segundos: 10 segundos ou menos
		return returnText(By.xpath("//*/li[@class='nav-item tab-receba-em-casa']/a"));
	}
	
	public String returnMsgBlankZipcode() {
		return returnText(By.xpath("//*/div[@class='alert alert-warning alert-dismissible fade show ml-2 mt-2']"));
	}
	
	public boolean closeMsgBlankZipcode() {
		// Não foi utilizado a função do JUnit que interage com javascript pq este elemento (alert) é um componente bootstrap.
		// Ao tentar interagir com o JUnit, ocorria exceçao de elemento não interagível, apesar de ser detectado pelo findElement. 
		// Também não era possível usar a função getDriver.switchTo.alert() pq nenhum alert era identificado pelo JUnit.
		// A solução foi utilizar comando Javascript para fechar o alert.
		executarJS(" $('.alert').alert('close'); ");
		return returnText(By.xpath("//*/form[@id='busca-frete']/small")).isEmpty();
	}
	
}
