package br.com.selenium.page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import br.com.selenium.core.BasePage;

public class ManageCartPage extends BasePage {

	public void clickBuyButton() {
		waitFor(By.id("btn-comprar-fisico-0"), 10);
		clickButton("btn-comprar-fisico-0");
	}
	
	public String returnProductNameCart() {
		waitFor(By.xpath("//*[@id='miniCart']//../*[@class='product-name']/a"), 10);
		return returnText(By.xpath("//*[@id='miniCart']//../*[@class='product-name']/a"));
	}
	
	public int returnCartSize() {
		waitFor(By.xpath("//*[@id='miniCart']//../div[@class='product-item']"), 10);
		List<WebElement> listOfItems = returnContent(By.xpath("//*[@id='miniCart']//../div[@class='product-item']"));
		return listOfItems.size();
	}
	
	public String returnCartImageQuantity(){
		return returnText(By.xpath("//*[@id='link-cart']/span"));
	}
	
	public void clickDeleteItemLink() {
		waitFor(By.xpath("//*[@id='miniCart']//../a[@title='remover']"), 10);
		clickLink(By.xpath("//*[@id='miniCart']//../a[@title='remover']"));
	}
	
	public String returnEmptyCartText() {
		waitFor(By.xpath("//*[@id='miniCart']//../*[.='SEU CARRINHO ESTÁ VAZIO']"), 10);
		return returnText(By.xpath("//*[@id='miniCart']//../*[.='SEU CARRINHO ESTÁ VAZIO']"));
	}
	
	public void incrementItemQuantityCart() {
		waitFor(By.xpath("//*[@id='item-quantity-change-increment-10574769']"), 15);
		clickLink(By.id("item-quantity-change-increment-10574769"));
	}
	
	public String quantityOfItem() {
		return returnValueAttibuteField(By.xpath("//div[@class='quantity']//../input"), "placeholder");
	}
	
	public void waitOrderCalc() {
		waitFor(By.xpath("//*[@id='miniCart']//../a[@id='cart-to-orderform']"), 10);
	}
	
}
