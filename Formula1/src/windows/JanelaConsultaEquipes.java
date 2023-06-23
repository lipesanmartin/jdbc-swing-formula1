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

import entities.Equipe;
import windows.customclasses.TabelaNaoEditavel;

public class JanelaConsultaEquipes {
	private static JFrame janelaConsultaEquipes;
	private static TabelaNaoEditavel tableModel;
	private static JTable tabela;
	private JButton botaoAtualizar;

	public JFrame criarJanela() {
		janelaConsultaEquipes = new JFrame("Tabela de Equipes");
		janelaConsultaEquipes.setResizable(false);
		janelaConsultaEquipes.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		janelaConsultaEquipes.setSize(732, 350);
		janelaConsultaEquipes.setLocationRelativeTo(null);
		Container caixa = janelaConsultaEquipes.getContentPane();
		caixa.setLayout(null);
		atualizarTabelaEquipes();
		botaoAtualizar = new JButton("Atualizar");
		botaoAtualizar.setBounds(300, 280, 100, 20);
		botaoAtualizar.setEnabled(true);
		janelaConsultaEquipes.add(botaoAtualizar);
		
		botaoAtualizar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				atualizarTabelaEquipes();
			}
		});
		
		
		return janelaConsultaEquipes;
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
	
	public static void atualizarTabelaEquipes() {
		tableModel = new TabelaNaoEditavel();
		tabela = new JTable(tableModel);
		tabela.setRowSelectionAllowed(false);
		tableModel.addColumn("ID");
		tableModel.addColumn("Nome");
		tableModel.addColumn("Nacionalidade");
		tableModel.addColumn("Chefe de Equipe");
		setJTableColumnsWidth(tabela, 480, 4, 32, 32, 32);
		JScrollPane painelTabela = new JScrollPane(tabela);
		painelTabela.setBounds(0, 0, 700, 270);
		janelaConsultaEquipes.add(painelTabela);
		Equipe equipe = new Equipe();
		for (Object[] obj : equipe.getDataFromTable()) {
			tableModel.addRow(obj);
		}
	}
}
