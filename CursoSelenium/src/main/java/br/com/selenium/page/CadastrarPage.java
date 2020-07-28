package br.com.selenium.page;
import java.util.List;

import org.openqa.selenium.By;

import br.com.selenium.core.BasePage;
import br.com.selenium.core.DSL;

// Utilizando o modelo POM - Page Object Model
// Using the model POM - Page Object Model

public class CadastrarPage extends BasePage { // passou a estender da BasePage

	// public DSL dsl; // esta linha foi comentada pq a classe passou a herdar a BasePage
	
	//Para utilizar a refatoraçao (classe DriverFactory), nao precisa mais usar o parametro WebDriver do construtor dessa classe
	/*public CadastrarPage(WebDriver driver) {
		dsl = new DSL(driver);
	}*/
	
	/*public CadastrarPage() { // este bloco foi comentado pq a classe passou a herdar a BasePage e será instanciado lá
		//dsl = new DSL(driver); //Tambem nao precisa passar o driver como parametro. Porem também precisou retirar este parametro da classe DSL.
		dsl = new DSL();
	}*/


	// set
	public void setName(String name) {
		dsl.writeField("elementosForm:nome", name);
	}
	
	public void setSurname(String surname) {
		dsl.writeField("elementosForm:sobrenome", surname);
	}
	
	public void setSexMale() {
		dsl.clickRadioButton("elementosForm:sexo:0");
	}
	
	public void setSexFemale() {
		dsl.clickRadioButton("elementosForm:sexo:1");
	}
	
	public void setFavoriteFoodCarne() {
		dsl.clickCheckBox("elementosForm:comidaFavorita:0");
	}

	public void setFavoriteFoodPizza() {
		dsl.clickCheckBox("elementosForm:comidaFavorita:2");
	}
	
	public void setFavoriteFoodVegetariano() {
		dsl.clickCheckBox("elementosForm:comidaFavorita:3");
	}
	
	public void setSchoolDegree(String value) {
		dsl.selectComboBox("elementosForm:escolaridade", value);
	}
	
	public void setSport(String... values) {
		for(String value: values)
			dsl.selectComboBox("elementosForm:esportes", value);
	}
	
	public void setDeselectComboSport(String value) {
		dsl.deselectCombo("elementosForm:esportes", value);
	}
	
	public void setSugestions(String value) {
		dsl.writeField("elementosForm:sugestoes", value);
	}

	public void setTextAlert(String value) {
		dsl.writeTextAlert(value);
	}
	
	// Return
	public String returnTextOnSubmit() {
		return dsl.returnText(By.xpath("//*[@id='resultado']/span"));
		//return dsl.returnText("resultado");
	}
	
	public String returnTextName() {
		return dsl.returnText(By.xpath("//*[@id='descNome']/span"));
		//return dsl.returnText("descNome");
	}
	
	public String returnValueName() {
		return dsl.returnValueField("elementosForm:nome");
	}
	
	public String returnTextSurname() {
		return dsl.returnText(By.xpath("//*[@id='descSobrenome']/span"));
	}
	
	public String returnValueSurname() {
		return dsl.returnValueField("sobrenome");
	}
	
	public String returnSex() {
		return dsl.returnText(By.xpath("//*[@id='descSexo']/span"));	
	}
	
	public Boolean returnIsSexFemale() {
		return dsl.isRadioButtonSelected("elementosForm:sexo:1");
	}
	
	public Boolean returnIsSexMale() {
		return dsl.isRadioButtonSelected("elementosForm:sexo:0");
	}
	
	public String returnFavoriteFood() {
		return dsl.returnText(By.xpath("//*[@id='descComida']/span"));
	}
	
	public Boolean returnIsFavoriteFoodPizza() {
		return dsl.isCheckBoxSelected("elementosForm:comidaFavorita:2");
	}
	
	public String returnSchoolDegre() {
		return dsl.returnText(By.xpath("//*[@id='descEscolaridade']/span"));
	}
	
	public String returnValueComboBoxSchool() {
		return dsl.returnValueComboBox("elementosForm:escolaridade");
	}
	
	public int returnSizeComboboxSchool() {
		return dsl.returnSizeCombo("elementosForm:escolaridade");
	}
	
	public Boolean returnFindValueComboSchool(String value) {
		return dsl.findValueCombo("elementosForm:escolaridade", value);
	}
	
	public String returnSport() {
		return dsl.returnText(By.xpath("//*[@id='descEsportes']/span"));
	}
	
	public int returnSizeSelectedComboSport() {
		return dsl.returnSizeSelectedCombo("elementosForm:esportes");
	}
	
	public List<String> returnAllSelectedValuesComboSport() {
		return dsl.returnAllSelectedValuesCombo("elementosForm:esportes");
	}
	
	public String returnValueSugestions() {
		return dsl.returnValueField("elementosForm:sugestoes");
	}
	
	
	//Button
	public void submit() {
		dsl.clickButton("elementosForm:cadastrar");
	}
	
	public void  clickButtonSimple() {
		dsl.clickButton("buttonSimple");
	}
	
	public void clickButtonALert() {
		dsl.clickButton("alert");
	}
	
	public void clickButtonConfirm() {
		dsl.clickButton("confirm");
	}
	
	public void clickButtonPrompt() {
		dsl.clickButton("prompt");
	}
	
	public void clickFrameButton() {
		dsl.clickButton("frameButton");
	}
	
	public void clickButtonPopUpEasy() {
		dsl.clickButton("buttonPopUpEasy");
	}
	
	
	//Alert
	public String returnTextThenAcceptAlert() {
		return dsl.returnTextThenAcceptAlert();
	}
	
	public String returnTextThenDismissAlert() {
		return dsl.returnTextThenDismissAlert();
	}
	
	public String returnTextAlert() {
		return dsl.returnTextAlert();
	}
	
	
	//Link
	public void  clickLinkVoltar() {
		dsl.clickLink("Voltar");
	} 
	
	
	//Frame
	public void changeToFrame1() {
		dsl.enterFrame("frame1");
	}
	
	public void exitFrame() {
		dsl.exitFrame();
	}
	
	
	// window
	public void changeWindowPopUp() {
		dsl.changeWindow("Popup");
	}
	
	
}
