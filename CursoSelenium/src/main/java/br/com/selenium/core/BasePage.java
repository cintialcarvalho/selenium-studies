package br.com.selenium.core;


// Ainda em fase de aprendizado, esta classe apenas instancia a classe DSL, onde fica toda a interação com a página web do projeto de testes.
// No projeto BasicProject, a classe BasePage e a DSL foram unidas, simplificando a estrutura do projeto.
public class BasePage {

	protected DSL dsl;
	
	public BasePage() {
		dsl = new DSL();
	}
	
}
