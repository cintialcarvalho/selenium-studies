package br.com.selenium.page;

import org.openqa.selenium.By;

import br.com.selenium.core.BasePage;
import br.com.selenium.core.DriverFactory;

public class LoginPage extends BasePage{

	public void acessarTelaInicial() {
		DriverFactory.getDriver().get("http://seubarriga.wcaquino.me/login");
	}
	
	public void setEmail(String email) {
		writeField("email", email);
	}
	
	public void setPassword(String password) {
		writeField("senha", password);
	}
	
	public void submitLogin() {
		clickButton(By.xpath("//button[.='Entrar']"));
	}
	
	public void reset() {
		clickLink("reset");
	}
	
	
}
