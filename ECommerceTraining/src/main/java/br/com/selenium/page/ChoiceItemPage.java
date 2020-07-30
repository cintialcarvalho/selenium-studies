package br.com.selenium.page;

import org.openqa.selenium.By;

import br.com.selenium.core.BasePage;

public class ChoiceItemPage extends BasePage {

	public String findAvailableItemByShownName(String item) {
		return returnText(By.xpath("//*/././a[.='"+item+"']/../../div[@class='product__actions']//span"));
	}	
	
	public String findUnavailableItemByShownName(String item) {
		return returnText(By.xpath("//*/././a[.='"+item+"']/../../div[@class='product__actions block-no-stock']/div"));
	}
	
}
