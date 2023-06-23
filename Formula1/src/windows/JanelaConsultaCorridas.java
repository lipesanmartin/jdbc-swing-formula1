package windows;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.TableColumn;

import entities.Corrida;
import windows.customclasses.TabelaNaoEditavel;

public class JanelaConsultaCorridas {
	private static JFrame janelaConsultaCorridas;
	private static TabelaNaoEditavel tableModel;
	private static JTable tabela;
	private JButton botaoAtualizar;
	
	public JFrame criarJanela() {
		janelaConsultaCorridas = new JFrame("Tabela de Corridas");
		janelaConsultaCorridas.setResizable(false);
		janelaConsultaCorridas.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		janelaConsultaCorridas.setSize(732, 350);
		janelaConsultaCorridas.setLocationRelativeTo(null);
		Container caixa = janelaConsultaCorridas.getContentPane();
		caixa.setLayout(null);
		atualizarTabelaCorridas();
		botaoAtualizar = new JButton("Atualizar");
		botaoAtualizar.setBounds(300, 280, 100, 20);
		botaoAtualizar.setEnabled(true);
		janelaConsultaCorridas.add(botaoAtualizar);
		botaoAtualizar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				atualizarTabelaCorridas();
			}
		});
		return janelaConsultaCorridas;
	}
	
	// metodo transforma a largura das colunas de acordo com a porcentagem informada
		public static void setJTableColumnsWidth(JTable table, int tablePreferredWidth,
		        double... percentages) {
		    double total = 0;
		    for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
		        total += percentages[i];
		    }
		    for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
		        TableColumn column = table.getColumnModel().getColumn(i);
		        column.setPreferredWidth((int)
		                (tablePreferredWidth * (percentages[i] / total)));
		    }
		}
	
	public static void atualizarTabelaCorridas() {
		tableModel = new TabelaNaoEditavel();
		tabela = new JTable(tableModel);
		tabela.setRowSelectionAllowed(false);
		tableModel.addColumn("ID");
		tableModel.addColumn("Nome");
		tableModel.addColumn("Circuito");
		tableModel.addColumn("Vencedor");
		tableModel.addColumn("Equipe");
		setJTableColumnsWidth(tabela, 480, 4, 24, 24, 24, 24);
		JScrollPane painelTabela = new JScrollPane(tabela);
		painelTabela.setBounds(0, 0, 700, 270);
		janelaConsultaCorridas.add(painelTabela);
		Corrida corrida = new Corrida();
		for (Object[] obj : corrida.getDataFromTable()) {
			tableModel.addRow(obj);
		}
	}
}
