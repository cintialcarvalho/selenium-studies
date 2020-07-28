package br.com.selenium.test;

import org.junit.Assert;
import org.junit.Test;

import br.com.selenium.core.BaseTest;
import br.com.selenium.core.DriverFactory;
import br.com.selenium.page.MenuPage;
import br.com.selenium.page.ResumoMensalPage;

//@FixMethodOrder(MethodSorters.NAME_ASCENDING) // Nao precisa desta linha caso esteja usando teste em paralelo
public class ResumoMensalTest extends BaseTest {

	private MenuPage menuPage = new MenuPage();
	private ResumoMensalPage resumoMensalPage = new ResumoMensalPage();
	
	@Test
	public void test_1_DeleteMovimentAccount() {
		menuPage.goInResumoMensal();
		resumoMensalPage.deleteAccountMoviment("Movimentacao para exclusao");
		Assert.assertEquals("Movimentação removida com sucesso!", resumoMensalPage.returnMessageSuccess());	
	}
	 
	@Test
	public void test_2_VerifyTitlePage() {
		menuPage.goInResumoMensal();
		Assert.assertEquals("Seu Barriga - Extrato", DriverFactory.getDriver().getTitle());
	}
	
	@Test
	public void test_3_VerifyResumoMensalEmpty() {
		menuPage.goInResumoMensal();
		resumoMensalPage.selectComboAno("2016");
		resumoMensalPage.clickBuscar();
		Assert.assertTrue(resumoMensalPage.returnEmptyTableMoviment()); // forma 1
		//Assert.assertEquals(0, resumoMensalPage.returnEmptyTableMoviment()); //forma 2
	}

}