package windows.table;

import javax.swing.table.DefaultTableModel;

public class TabelaNaoEditavel extends DefaultTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public boolean isCellEditable(int row, int column) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
