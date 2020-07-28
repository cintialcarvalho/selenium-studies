package br.com.selenium.test;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class TesteFrames {

	@Test
	public void interagirComFrame() {
		
		WebDriver driver = new ChromeDriver(); // A soluçao para a repeticao dessas 3 linhas está na classe TestarRegrasFormulario
		driver.manage().window().setSize(new Dimension(1200, 865));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		driver.switchTo().frame("frame1");
		driver.findElement(By.id("frameButton")).click();
		
		Alert alertaFrame = driver.switchTo().alert();
		String msgAlertaFrame = alertaFrame.getText();
		Assert.assertEquals("Frame OK!", msgAlertaFrame);
		alertaFrame.accept();
		
		driver.switchTo().defaultContent(); // volta o foco para a página principal
		driver.findElement(By.id("elementosForm:nome")).sendKeys(msgAlertaFrame);
		
		driver.close(); // A soluçao para a repetiçao desta linha em todos os métodos está na classe TestarRegrasFormulario
		
	}
	
	@Test
	public void interagirComPopup() {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200, 865));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		driver.findElement(By.id("buttonPopUpEasy")).click();
		driver.switchTo().window("Popup");
		driver.findElement(By.tagName("textarea")).sendKeys("Escrevendo no textArea do PopUp");
		driver.close(); // neste caso usa o close() pq a ideia é fechar apenas o popUp. Se usar o quit(), vai fechar toda a instancia do browser
		
		driver.switchTo().window(""); // Volta para a página principal. Neste caso a página náo tem identificador, por isso foi usado só aspas
		//driver.switchTo().defaultContent(); // NÃO PODE USAR ESTE ITEM pq neste caso não é quesão de setar foco e sim de alterar janelas
		driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("Escrevendo no TextArea da Página principal para finalizar o teste.");
	
		driver.close();
		
	}
	
	@Test
	public void interagirComPopupSemIdentificador() {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200, 865));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		driver.findElement(By.id("buttonPopUpHard")).click();
		
		System.out.println(driver.getWindowHandle()); // mostra o identificador gerenciado pelo selenium da janela principal
		System.out.println(driver.getWindowHandles()); // mostra um ARRAY com os identificadores de todas as janelas abertas daquela instancia
		
		// Mudando o foco para o Popup que não tem identificador no html. Por isso foi usada a funcao getWindowHandles()
		driver.switchTo().window( (String) driver.getWindowHandles().toArray()[1] ); // teve que fazer um cast para String pq o retorno do getWindowHandles() é um Array
		driver.findElement(By.tagName("textarea")).sendKeys("Escrevendo no TextArea do Popp sem identificador");
		
		// Voltando para a página principal
		driver.switchTo().window( (String) driver.getWindowHandles().toArray()[0] );
		driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("Escrevendo no TextArea da janela principal para terminar o teste.");
		
		driver.quit(); // Neste caso foi usado o quit() para fechar tanto o PopUp quanto a jenala principal
		
	}
	
	
	
	
}