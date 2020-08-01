package br.com.selenium.page;

import static br.com.selenium.core.DriverFactory.getDriver;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.selenium.core.BasePage;
import br.com.selenium.core.DriverFactory;

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
		WebDriverWait wait = new WebDriverWait(getDriver(), 5); // Tempo de espera em segundos: 5 segundos, podendo ser menos
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*/././a[.='"+item+"']/../../div[@class='product__actions']//span"))); 
	}
	
	public void waitForCep() {
		WebDriverWait wait = new WebDriverWait(getDriver(), 10); // Tempo de espera em segundos: 10 segundos, podendo ser menos
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("input-frete-pdp"))); 
	}
	
	public String returnShippingRecebeEmCasa() {
		WebDriverWait wait = new WebDriverWait(getDriver(), 10); // Tempo de espera em segundos: 10 segundos, podendo ser menos
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='receba-em-casa']//../..//tbody[@id='include-receba-em-casa']/tr"))); 
		return returnText(By.xpath("//*/li[@class='nav-item tab-receba-em-casa']/a"));
	}
	
	
}

