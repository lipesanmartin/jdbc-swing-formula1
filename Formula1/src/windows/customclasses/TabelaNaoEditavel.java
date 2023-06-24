package windows.customclasses;

import javax.swing.table.DefaultTableModel;
/**
 * Uma classe personalizada que estende a classe DefaultTableModel do Swing para criar uma tabela não editável.
 * A classe substitui o método isCellEditable da classe pai para sempre retornar false, impedindo a edição das células da tabela.
 */
public class TabelaNaoEditavel extends DefaultTableModel {
	private static final long serialVersionUID = 1L;
	/**
	 * Sobrescreve o método isCellEditable da classe pai, indicando se uma célula da tabela é editável ou não.
	 * Neste caso, todas as células da tabela são definidas como não editáveis, portanto o método retorna sempre false.
	 *
	 * @param row    o índice da linha da célula
	 * @param column o índice da coluna da célula
	 * @return false para indicar que a célula não é editável
	 */	
	@Override
	public boolean isCellEditable(int row, int column) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
