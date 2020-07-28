package br.com.selenium.core;

import static br.com.selenium.core.DriverFactory.getDriver;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/* No projeto CursoSelenium as classes DSL e BasePage foram tratadas de forma destinta.
 * Neste projeto (CursoSelenium), a classe DSL foi incorporada a classe BasePage, por esse motivo os métodos estão nessa classe. 
 * E por este motivo a classe DSL nao foi trazida para este projeto.
 * */

public class BasePage {

	/*** TextField and TextArea ***/
	public void writeField(By by, String value) {
		getDriver().findElement(by).clear();
		getDriver().findElement(by).sendKeys(value);
	}	
	
	public void writeField(String idField, String value) {
		writeField(By.id(idField), value);
	}
	
	public String returnValueField(String idField) {
		return getDriver().findElement(By.id(idField)).getAttribute("value");
	}
	
	
	/*** RadioButton and RadioBox ***/
	public void clickRadioButton(By by) {
		 getDriver().findElement(by).click();
	}
	
	public void clickRadioButton(String idField) {
		 //getDriver().findElement(By.id(idField)).click();
		 clickRadioButton(By.id(idField));
	}
	
	public Boolean isRadioButtonSelected(String idField) {
		return getDriver().findElement(By.id(idField)).isSelected();
	}
	
	public void clickCheckBox(String idField) {
		 getDriver().findElement(By.id(idField)).click();
	}

	public Boolean isCheckBoxSelected(String idField) {
		return getDriver().findElement(By.id(idField)).isSelected();
	}
	
	
	/*** Combobox ***/
	public void selectComboBoxById(By by, String value) {
		WebElement element = getDriver().findElement(by);
		Select combo = new Select(element);
		combo.selectByValue(value); 			
	}
	
	public void selectComboBoxById(String idField, String value) {
		selectComboBoxById(By.id(idField), value);
		/*WebElement element = getDriver().findElement(By.id(idField));
		Select combo = new Select(element);
		combo.selectByValue(value); */			
	}
	
	public void selectComboBoxByVisibleText(By by, String visibleText) {
		WebElement element = getDriver().findElement(by);
		Select combo = new Select(element);
		combo.selectByVisibleText(visibleText);		
	}
	
	public void selectComboBoxByVisibleText(String idField, String visibleText) {
		selectComboBoxByVisibleText(By.id(idField), visibleText);	
	}
	
	public String returnValueComboBox(String idField) {
		WebElement element = getDriver().findElement(By.id(idField));
		Select combo = new Select(element);
		return combo.getFirstSelectedOption().getText(); 			
	}
	
	public List<String> returnAllSelectedValuesCombo(String idField) {
		WebElement element = getDriver().findElement(By.id(idField));
		Select combo = new Select(element);
		List<WebElement> allSelectedoptions = combo.getAllSelectedOptions();
		List <String> valores = new ArrayList<String>();
		for(WebElement option: allSelectedoptions) {
			valores.add(option.getText());
		}
		return valores;
	}
	
	public void deselectCombo(String idField, String value) {
		WebElement element = getDriver().findElement(By.id(idField)); 
		Select combo = new Select(element);
		combo.deselectByValue(value);
	}
	
	public int returnSizeCombo(String idField) {
		WebElement element = getDriver().findElement(By.id(idField));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		return options.size();
	}
	
	public int returnSizeSelectedCombo(String idField) {
		WebElement element = getDriver().findElement(By.id(idField)); 
		Select combo = new Select(element);
		List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
		return allSelectedOptions.size();
	}
	
	public boolean findValueCombo(String idField, String value) {
		WebElement element = getDriver().findElement(By.id(idField));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		
		for(WebElement currentOption: options ) {
			if(currentOption.getText().equals(value)) {
				return true;
			}
		}
		return false;
	}
	
	
	/*** Click Button ***/
	public void clickButton(By by) {
		getDriver().findElement(by).click();
	}
	
	public void clickButton(String idField) {
		clickButton(By.id(idField));
		//getDriver().findElement(By.id(idField)).click();
	}
	
	public String returnValueButton(String idField, String value) {
		return getDriver().findElement(By.id(idField)).getAttribute(value);
	}
	
	
	/*** Click Link ***/
	public void clickLink(By by) {
		getDriver().findElement(by).click();
	}
	
	public void clickLink(String textLink) {
	    clickLink(By.linkText(textLink));
	}
	
	
	/*** Return Text ***/
	public String returnText(By by) {
		return getDriver().findElement(by).getText();
	}	
	 
	public String returnText(String idField) {
		return returnText(By.id(idField));
	}
	
	
	/*** Alert ***/
	public String returnTextAlert() {
		Alert alertName = getDriver().switchTo().alert();
		return alertName.getText();
	}
	
	public String returnTextThenAcceptAlert() {
		Alert alertName = getDriver().switchTo().alert();
		String msgAlertName = alertName.getText();
		alertName.accept();		
		return msgAlertName;
	}

	public String returnTextThenDismissAlert() {
		Alert alertName = getDriver().switchTo().alert();
		String msgAlertName = alertName.getText();
		alertName.dismiss();		
		return msgAlertName;
	}
	
	public void writeTextAlert(String value) {
		Alert alert = getDriver().switchTo().alert();
		alert.sendKeys(value);
		alert.accept();
	}
	
	
	/*** Frames and Windows ***/
	public void enterFrame(String idField) {
		getDriver().switchTo().frame(idField);
	}
	
	public void exitFrame() {
		getDriver().switchTo().defaultContent();
	}
	
	public void changeWindow(String idField) {
		getDriver().switchTo().window(idField);
	}
	
	
	/*** JS ***/
	public Object executarJS(String cmd, Object... param) {
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		return js.executeScript(cmd, param);
	}
	
	/*** Tabela ***/
	public void clickButtonTable(String colunaBusca, String valorColuna, String colunaBotao, String idTabela) {
		
		WebElement celula = returnCellTable(colunaBusca, valorColuna, colunaBotao, idTabela);
		// clicar no botao da celula encontrada
		celula.findElement(By.xpath(".//input")).click(); // usa o .// para garantir que o xpath vai se manter apenas neste ponto do código
	}
	
	public void clickButtonTable(String colunaBusca, String valorColuna, String colunaBotao, String idTabela, String elementType) {
		
		// parametro String elementType: informa o elemento que será procurado. Ou seja, span, input, div, etc.
		WebElement celula = returnCellTable(colunaBusca, valorColuna, colunaBotao, idTabela);
		celula.findElement(By.xpath(".//" + elementType )).click(); 
	
	}
	
	public String returnTextTable(String colunaBusca, String valorColuna, String colunaRetorno, String idTabela) {
		
		WebElement celula = returnCellTable(colunaBusca, valorColuna, colunaRetorno, idTabela);
		return celula.getText();
	
	}
	
	public WebElement returnCellTable(String colunaBusca, String valorColuna, String colunaBotao, String idTabela) {

		WebElement tabela = getDriver().findElement(By.xpath("//*[@id='"+idTabela +"']")); // este xpath leva até a tabela

		//Encontrar coluna do registro
		int idColuna = returnIndexColum(colunaBusca, tabela);
		
		// encontrar linha do registro
		int idLinha = returnIndexLine(valorColuna, tabela, idColuna);
		
		// procura a coluna do botao
		// como o procedimento de busca é o mesmo, ou seja, busca pela coluna do botao, é só utilizar o mesmo método porém buscando pela colunaBotao
		int idColunaBotao = returnIndexColum(colunaBotao, tabela);
		
		// clicar no botao da celula encontrado
		WebElement celula = tabela.findElement(By.xpath(".//tr["+idLinha+"]/td["+idColunaBotao+"]"));
		
		return celula;
	}

	protected int returnIndexColum(String coluna, WebElement tabela) {
		// este ponto seguido de duas barras .// significa que o xpath vai fazer a busca a partir do diretório corrente. Ou seja, a partir da tabela q ele está.
		List<WebElement> colunas = tabela.findElements(By.xpath(".//th"));   
		
		int idColuna = -1; // Os identificadores automáticos sempre começam por 1 entao eh necessário comecar a variável por -1
		for(int i=0; i<colunas.size(); i++) {
			if(colunas.get(i).getText().equals(coluna)) {
				idColuna = i+1; // se usar idColuna = i o identificador da coluna nao vai bater com a variavel de controle do loop e vai indicar um ID errado na tabela, por isso soma +1
				break;
			}
		}
		return idColuna;
	}
	
	protected int returnIndexLine(String valorColuna, WebElement tabela, int idColuna) {
		
		// como está na mesma tabela, pode-se usar a variavel tabela, já carregada mais acima, e assim buscar só pelo terminal .//tr/td[1], porém deixando genérico através da variável idColuna
		List<WebElement> linhas = tabela.findElements(By.xpath("./tbody/tr/td["+idColuna+"]"));
		
		int idLinha = -1; // Os identificadores automáticos sempre começam por 1 entao eh necessário comecar a variável por -1
		for(int i=0; i<linhas.size(); i++) {
			if(linhas.get(i).getText().equals(valorColuna)) {
				idLinha = i+1; // se usar idLinha = i o identificador da linha nao vai bater com a variavel de controle do loop e vai indicar um ID errado na tabela, por isso soma +1
				break;
			}
			// No caso de busca por linhas, caso a tabela tenha paginaçao é possível incluir aqui neste ponto o código para 'clicar' no 'next' e ir para a 
			// próxima página da tabela prosseguindo com a busca até o fim da paginaçao.
		}
		return idLinha;
	}


	
}
