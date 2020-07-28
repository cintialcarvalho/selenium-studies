package br.com.selenium.core;
import static br.com.selenium.core.DriverFactory.getDriver;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

// Todas as classes Pages (classes que fazem a interação com a página web do projeto) devem herdar desta classe.
// Esta classe DSL (que poderia ser unificada com a classe BasePage) promove o reuso de comandos que se tornariam repetitivos dentro do projeto.
// Também promove a abstração já que os métodos podem ser chamados de forma mais simples e transparentes atraves das classes Pages de cada teste.
public class DSL {

	// private WebDriver driver; // Devido a refatoraçao, também nao precisa desta declaracao. Tudo vem da classe DriverFactory.

	//Devido a criaçao da classe DriverFactory, esta classe nao precisa mais de construtor
	/*public DSL(WebDriver driver) {
		this.driver = driver;
	}*/
	
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
	public void selectComboBox(By by, String value) {
		WebElement element = getDriver().findElement(by);
		Select combo = new Select(element);
		combo.selectByValue(value); 			
	}
	
	public void selectComboBox(String idField, String value) {
		
		selectComboBox(By.id(idField), value);
		
		/*WebElement element = getDriver().findElement(By.id(idField));
		Select combo = new Select(element);
		combo.selectByValue(value); */			
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
	public void clickLink(String textLink) {
		getDriver().findElement(By.linkText(textLink)).click();
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
	public void clicarBotaoTabela(String colunaBusca, String valorColuna, String colunaBotao, String idTabela) {
		//elementosForm:tableUsuarios
		// procurar coluna do registro
		// XPath da tabela: //*[@id="elementosForm:tableUsuarios"]/th
		WebElement tabela = getDriver().findElement(By.xpath("//*[@id=\"elementosForm:tableUsuarios\"]")); // este xpath leva até a tabela
		int idColuna = obterIndiceColuna(colunaBusca, tabela);
		
		// encontrar linha do registro
		// XPath da linha: //*[@id="elementosForm:tableUsuarios"]//tr/td[1]
		
		int idLinha = obterIndiceLinha(valorColuna, tabela, idColuna);
		
		// procurar coluna do botao
		// como o procedimento de busca é o mesmo, ou seja, busca pela coluna do botao, é só utilizar o mesmo método porém buscando pela colunaBotao
		int idColunaBotao = obterIndiceColuna(colunaBotao, tabela);
		
		// clicar no botao da celula encontrado
		// Xpath do botao:  //*[@id="elementosForm:tableUsuarios"]/tbody/tr[2]/td[3]/input
		WebElement celula = tabela.findElement(By.xpath(".//tr["+idLinha+"]/td["+idColunaBotao+"]"));
		celula.findElement(By.xpath(".//input")).click(); // usa o .// para garantir que o xpath vai se manter apenas neste ponto do código
		
	}

	protected int obterIndiceLinha(String valorColuna, WebElement tabela, int idColuna) {
		
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

	protected int obterIndiceColuna(String coluna, WebElement tabela) {
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
	
	
}
