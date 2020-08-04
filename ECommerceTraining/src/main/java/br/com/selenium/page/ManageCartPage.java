package br.com.selenium.page;

import org.openqa.selenium.By;

import br.com.selenium.core.BasePage;

public class ManageCartPage extends BasePage {

	public void clickBuyButton() {
		clickButton("btn-comprar-fisico-0");
	}
	
	public String returnProductNameCart() {
		waitFor(By.xpath("//*[@id='miniCart']//../*[@class='product-name']/a"), 10);
		return returnText(By.xpath("//*[@id='miniCart']//../*[@class='product-name']/a"));
	}
	
}
