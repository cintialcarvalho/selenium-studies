package br.com.selenium.core;

public class Properties {
	
	public static boolean CLOSE_BROWSER = true; // FALSE = não fecha o browser a cada execuçao de cada teste. Deixa o teste mais rápido.
											   // TRUE = fecha o browser e abre um novo a cada execuçao de teste. Deixa o teste mais lento.
	
	// OBSERVACAO: A prática de utilizar o mesmo browser para cada teste é uma prática perigisa. 
	//			   A BOA PRÁTICA diz que CADA teste deve utilizar seu PRÓPRIO browser, execute o teste e FECHE o browser
	// 			   pq cada teste deve ser AUTO-CONTIDO, ou seja, um teste não deve depender de outro para
	// 			   funcionar e nao deve ser impactao por sujeira de outros testes.
	//			   Sempre que o selenium  abre uma novo browser, ele cria um novo profile para aquela execuçao. Ele não usa o profile que ja está em uso.
	//
	//ATENÇAO:
	//			   A estratégia de usar apenas um browser para todas as execuçoes se aplica apenas a fase de DESENVOLVIMENTO pq ajuda a ganhar tempo.
	//			   Quando os testes estiverem sendo executados numa bateria de testes REAIS, no ambiente de teste, deve-se VOLTAR a utilizar
	//			   UM browser para CADA EXECUÇAO.
	
	
	// Criado para mudar a execuçao por browser sem precisar alterar a classe do teste
	public static Browsers browser = Browsers.FIREFOX;
	
	public enum Browsers{
		CHROME,
		FIREFOX
	}
	
	
}
