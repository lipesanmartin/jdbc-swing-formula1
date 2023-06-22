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

import entities.Piloto;
import windows.customclasses.TabelaNaoEditavel;

public class JanelaConsultaPilotos {
	private static JFrame janelaConsultaPilotos;
	private static TabelaNaoEditavel tableModel;
	private static JTable tabela;
	private JButton botaoAtualizar;

	public JFrame criarJanela() {
		janelaConsultaPilotos = new JFrame("Tabela de Pilotos");
		janelaConsultaPilotos.setResizable(false);
		janelaConsultaPilotos.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		janelaConsultaPilotos.setSize(720, 400);
		janelaConsultaPilotos.setLocationRelativeTo(null);
		Container caixa = janelaConsultaPilotos.getContentPane();
		caixa.setLayout(null);
		atualizarTabela();
		botaoAtualizar = new JButton("Atualizar");
		botaoAtualizar.setBounds(310, 325, 100, 20);
		botaoAtualizar.setEnabled(true);
		janelaConsultaPilotos.add(botaoAtualizar);
		
		botaoAtualizar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				atualizarTabela();
			}
		});

		return janelaConsultaPilotos;
	}

	// metodo transforma a largura das colunas de acordo com a porcentagem informada
	public static void setJTableColumnsWidth(JTable table, int tablePreferredWidth, double... percentages) {
		double total = 0;
		for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
			total += percentages[i];
		}
		for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
			TableColumn column = table.getColumnModel().getColumn(i);
			column.setPreferredWidth((int) (tablePreferredWidth * (percentages[i] / total)));
		}
	}

	// cria e atualiza a tabela
	public static void atualizarTabela() {
		tableModel = new TabelaNaoEditavel();
		tabela = new JTable(tableModel);
		tabela.setRowSelectionAllowed(false);
		tableModel.addColumn("ID");
		tableModel.addColumn("Nome");
		tableModel.addColumn("Nacionalidade");
		tableModel.addColumn("Nº Carro");
		tableModel.addColumn("Equipe");
		tableModel.addColumn("ID Equipe");
		setJTableColumnsWidth(tabela, 1000, 6.5, 27.5, 27.5, 8, 22.5, 8);
		JScrollPane painelTabela = new JScrollPane(tabela);
		painelTabela.setBounds(0, 0, 700, 310);
		janelaConsultaPilotos.add(painelTabela);
		Piloto piloto = new Piloto();
		for (Object[] obj : piloto.getDataFromTable()) {
			tableModel.addRow(obj);
		}
	}
}
