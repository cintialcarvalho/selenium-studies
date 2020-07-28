package br.com.selenium.test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.selenium.core.BaseTest;
import br.com.selenium.page.MenuPage;
import br.com.selenium.page.MovimentPage;
import br.com.selenium.utils.DataUtils;


//@FixMethodOrder(MethodSorters.NAME_ASCENDING) // Nao precisa desta linha caso esteja usando teste em paralelo
public class MovimentTest extends BaseTest{
	 
	private MenuPage menuPage = new MenuPage();
	private MovimentPage movimentPage = new MovimentPage();
	
	@Test
	public void test_1_InsertAccountMoviment() {
		menuPage.goInMovimentAccount();
		movimentPage.setTypeMoviment("REC");
		movimentPage.setDateMoviment(DataUtils.formatDate( new Date()));
		movimentPage.setDatePayment(DataUtils.formatDate( new Date()));
		movimentPage.setDetails("Gerando movimentacao "+ DataUtils.formatHour( new Date()) );
		movimentPage.setPersonInterested("Joazinho");
		movimentPage.setValue("1259");
		movimentPage.selectAccountByVisibleText("Conta para movimentacoes");
		movimentPage.setSituationPaid();
		movimentPage.saveMoviment();
		Assert.assertEquals("Movimentação adicionada com sucesso!", movimentPage.returnMessageSuccess());
	} 
	
	@Test
	public void test_2_MandatoryFields() {
		menuPage.goInMovimentAccount();
		movimentPage.saveMoviment();
		List<String> errors = movimentPage.returnAllFailMessagesMoviment();
		
		Assert.assertEquals(6, errors.size()); // Além de verificar cada uma das msgs, tb quero ver se a qtd de msgs retornadas é igual a qtd de msgs testadas (neste caso, 6).
		
		// Para verificar cada uma das mensagens, pode-se fazer das formas, assim:
		Assert.assertTrue(errors.containsAll( Arrays.asList(
				"Data da Movimentação é obrigatório",
				"Data do pagamento é obrigatório",
				"Descrição é obrigatório",
				"Interessado é obrigatório",
				"Valor é obrigatório",
				"Valor deve ser um número") ));
		
		// Ou assim:
		/*Assert.assertTrue(errors.contains("Data da Movimentação é obrigatório"));
		Assert.assertTrue(errors.contains("Data do pagamento é obrigatório"));
		Assert.assertTrue(errors.contains("Descrição é obrigatório"));
		Assert.assertTrue(errors.contains("Interessado é obrigatório"));
		Assert.assertTrue(errors.contains("Valor é obrigatório"));
		Assert.assertTrue(errors.contains("Valor deve ser um número"));*/
		
	}
	
	@Test
	public void test_3_FutureMovimentDate() {
		menuPage.goInMovimentAccount();
		movimentPage.setTypeMoviment("REC");
		
		//movimentPage.setDateMoviment("20/08/2021"); //Para que a data nao fique estática no código, foi criada funcao para pegar a data do dia.
		movimentPage.setDateMoviment(DataUtils.returnCalculatedDate(5));
		
		//movimentPage.setDatePayment("20/08/2020");  
		movimentPage.setDatePayment(DataUtils.returnCalculatedDate(15)); 
		
		movimentPage.setDetails("Gerando movimentacao Futura "+ DataUtils.formatHour( new Date()) );
		movimentPage.setPersonInterested("eu denovo");
		movimentPage.setValue("1.45");
		movimentPage.selectAccountByVisibleText("Conta para movimentacoes");
		movimentPage.setSituationPaid();
		movimentPage.saveMoviment();
		Assert.assertEquals("Data da Movimentação deve ser menor ou igual à data atual", movimentPage.returnMessageFail());
	}
	
}
