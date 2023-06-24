package application;

import windows.JanelaPrincipal;
/**
 * A classe Programa é responsável por iniciar a aplicação e apresentar o menu principal.
 * @author Felipe Sanmartin, Daniel Nogueira, Francisco Ferreira, Igor Rodrigues
 *
 */
public class Programa {
	/**
	 * O método main é o ponto de entrada da aplicação.
	 * Cria uma instância da classe JanelaPrincipal e chama o método apresentarMenu. 
	 * 
	 */
	public static void main(String[] args) {
		JanelaPrincipal programa = new JanelaPrincipal();
		programa.apresentarMenu();
	}
}
