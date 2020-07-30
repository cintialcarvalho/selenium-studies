package br.com.selenium.core;

public class Properties {
	
	// OBSERVACAO: A prática de utilizar o mesmo browser para cada teste é uma prática perigosa. 
	//			   A BOA PRÁTICA diz que CADA teste deve utilizar seu PRÓPRIO browser, executando o teste e FECHANDO o browser
	// 			   pq cada teste deve ser AUTO-CONTIDO, ou seja, um teste não deve depender de outro para
	// 			   funcionar e nao deve ser impactao por sujeira de outros testes.
	//			   Sempre que o selenium  abre uma novo browser, ele cria um novo profile para aquela execuçao. Ele não usa o profile que ja está em uso.
	//
	// ATENÇAO:
	//			   A estratégia de usar apenas um browser para todas as execuçoes se aplica apenas a fase de DESENVOLVIMENTO pq ajuda a ganhar tempo.
	//			   Quando os testes estiverem sendo executados numa bateria de testes REAIS, no ambiente de teste, deve-se VOLTAR a utilizar
	//			   UM browser para CADA EXECUÇAO.
	public static boolean CLOSE_BROWSER = true; // FALSE = não fecha o browser a cada execuçao de cada teste. Deixa o teste mais rápido.
											   // TRUE = fecha o browser e abre um novo a cada execuçao de teste. Deixa o teste mais lento.
	 
	// Este parametro pode ser usado para dar um nome fixo a um determinado item que está sendo inserido no sistema durante a execuçao dos testes.
	// Neste caso abaixo, o parametro poderá ser usado no teste de alteraçao de conta, substituindo o valor descrito manualmente no método. 
	// Assim a conta alterada terá um nome quase fixo.
	// OBS: Preferi nao usar este parametros nos meus testes pq estou excluindo a conta que foi inserida e alterada pela própria suite.
	// Ao final do curso, com a inserção do click no link escondido que reseta a base de testes, este parametro se tornou inútil. Agora apenas para fins didáticos.
	//public static String NOME_CONTA_ALTERADA = "Conta Alterada "+ System.nanoTime(); 
	
	// Criado para mudar a execuçao por browser sem precisar alterar a classe do teste
	public static Browsers BROWSER = Browsers.CHROME;
	
	// Refatoraçao para definir se a suite será executada de forma local ou utilizando o Selenium GRID
	public static TipoExecucao TIPO_EXECUCAO = TipoExecucao.LOCAL;
	
	public enum Browsers{
		CHROME,
		FIREFOX
	}
	
	public enum TipoExecucao{
		LOCAL,
		GRID,
		CLOUD // Utilizando os recursos da SAUCE LABS
	}
	
}