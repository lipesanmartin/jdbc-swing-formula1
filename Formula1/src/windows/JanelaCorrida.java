package windows;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import entities.Corrida;
import entities.Equipe;
import entities.Piloto;

public class JanelaCorrida {
	private JFrame janelaCorrida;
	private JTextField jTextId;
	private JTextField jTextNome;
	private JTextField jTextLocal;
	JComboBox<Integer> dropdownVencedorId;
	JTextField jTextVencedorNome;
	JTextField jTextEquipeVencedora;
	private JButton botaoConsultar;
	private JButton botaoGravar;
	private JButton botaoLimpar;
	private JButton botaoApagar;

	public JFrame criarJanelaCorrida() {
		// Define a janela
		janelaCorrida = new JFrame("Atualização de corrida"); // Janela Normal
		janelaCorrida.setResizable(false); // A janela não poderá ter o tamanho ajustado
		janelaCorrida.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		janelaCorrida.setSize(500, 400); // Define tamanho da janela
		janelaCorrida.setLocationRelativeTo(null);
		// Define o layout da janela
		Container caixa = janelaCorrida.getContentPane();
		caixa.setLayout(null);
		// Define os labels dos campos
		JLabel labelId = new JLabel("ID: ");
		JLabel labelNome = new JLabel("Nome: ");
		JLabel labelLocal = new JLabel("Local: ");
		JLabel labelVencedorId = new JLabel("Vencedor ID: ");
		JLabel labelVencedorNome = new JLabel("Vencedor: ");
		JLabel labelEquipeVencedora = new JLabel("Equipe: ");
		// Posiciona os labels na janela
		labelId.setBounds(50, 40, 100, 20); // coluna, linha, largura, tamanho
		labelNome.setBounds(50, 80, 100, 20);
		labelLocal.setBounds(50, 120, 100, 20);
		labelVencedorId.setBounds(50, 160, 100, 20);
		labelVencedorNome.setBounds(50, 200, 100, 20);
		labelEquipeVencedora.setBounds(50, 240, 100, 20);
		// Define os input box
		jTextId = new JTextField();
		jTextNome = new JTextField();
		jTextLocal = new JTextField();
		dropdownVencedorId = new JComboBox<Integer>();
		jTextVencedorNome = new JTextField();
		jTextEquipeVencedora = new JTextField();
		// Define se os campos estão habilitados ou não no início
		jTextId.setEnabled(true);
		jTextNome.setEnabled(false);
		jTextLocal.setEnabled(false);
		dropdownVencedorId.setEnabled(false);
		jTextVencedorNome.setEnabled(false);
		jTextEquipeVencedora.setEnabled(false);
		// Posiciona os input box
		jTextId.setBounds(180, 40, 50, 20);
		jTextNome.setBounds(180, 80, 150, 20);
		jTextLocal.setBounds(180, 120, 150, 20);
		dropdownVencedorId.setBounds(180, 160, 50, 20);
		jTextVencedorNome.setBounds(180, 200, 150, 20);
		jTextEquipeVencedora.setBounds(180, 240, 150, 20);
		jTextId.setDisabledTextColor(Color.DARK_GRAY);
		jTextNome.setDisabledTextColor(Color.DARK_GRAY);
		jTextLocal.setDisabledTextColor(Color.DARK_GRAY);
		jTextVencedorNome.setDisabledTextColor(Color.DARK_GRAY);
		jTextEquipeVencedora.setDisabledTextColor(Color.DARK_GRAY);
		// Adiciona os rótulos e os input box na janela
		janelaCorrida.add(labelId);
		janelaCorrida.add(labelNome);
		janelaCorrida.add(labelLocal);
		janelaCorrida.add(labelVencedorId);
		janelaCorrida.add(labelVencedorNome);
		janelaCorrida.add(labelEquipeVencedora);
		janelaCorrida.add(jTextId);
		janelaCorrida.add(jTextNome);
		janelaCorrida.add(jTextLocal);
		janelaCorrida.add(dropdownVencedorId);
		janelaCorrida.add(jTextVencedorNome);
		janelaCorrida.add(jTextEquipeVencedora);
		// Define botões e a localização deles na janela
		botaoConsultar = new JButton("Consultar");
		botaoConsultar.setBounds(230, 40, 100, 20);
		janelaCorrida.add(botaoConsultar);
		botaoGravar = new JButton("Gravar");
		botaoGravar.setBounds(50, 300, 100, 20);
		botaoGravar.setEnabled(false);
		janelaCorrida.add(botaoGravar);
		botaoLimpar = new JButton("Limpar");
		botaoLimpar.setBounds(350, 300, 100, 20);
		janelaCorrida.add(botaoLimpar);
		botaoApagar = new JButton("Apagar");
		botaoApagar.setBounds(200, 300, 100, 20);
		botaoApagar.setEnabled(false);
		janelaCorrida.add(botaoApagar);
		// Define objeto corrida para pesquisar no banco de dados
		Corrida corrida = new Corrida();
		// Define ações dos botões
		botaoConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Piloto piloto = new Piloto();
					int id = Integer.parseInt(jTextId.getText());
					botaoGravar.setEnabled(true);
					String nome, local, vencedorNome = null, equipeNome = null;
					int vencedorId;
					if (!corrida.consultarCorrida(id)) {
						JOptionPane.showMessageDialog(janelaCorrida, "Corrida não cadastrada.");
						nome = "";
						local = "";
						vencedorId = 0;
						vencedorNome = "";
						equipeNome = "";
						jTextId.setEnabled(false);
						jTextLocal.setEnabled(true);
						botaoApagar.setEnabled(false);
						jTextNome.setEnabled(true);
						jTextNome.requestFocus();
					} else {
						Equipe equipe = new Equipe();
						int equipeId;
						nome = corrida.getNome();
						local = corrida.getLocal();
						vencedorId = corrida.getVencedor();
						if (piloto.consultarPiloto(vencedorId)) {
							vencedorNome = piloto.getNome();
							equipeId = piloto.getIdEquipe();
							if (equipe.consultarEquipe(equipeId)) {
								equipeNome = equipe.getNome();
							}
						}
						jTextId.setEnabled(false);
						jTextLocal.setEnabled(false);
						botaoApagar.setEnabled(true);
						botaoGravar.setBounds(30, 300, 150, 20);
						botaoGravar.setText("Atualizar vencedor");
					}
					List<Integer> listaPilotos = new ArrayList<>();
					for (Integer i : piloto.getPilotoIdList()) {
						listaPilotos.add(i);
					}
					for (Integer item : listaPilotos) {
						dropdownVencedorId.addItem(item);
					}
					dropdownVencedorId.getSelectedItem();
					jTextNome.setText(nome);
					jTextLocal.setText(local);
					jTextVencedorNome.setText(vencedorNome);
					jTextEquipeVencedora.setText(equipeNome);
					dropdownVencedorId.setEnabled(true);
					botaoConsultar.setEnabled(false);

				} catch (Exception erro) {
					JOptionPane.showMessageDialog(janelaCorrida, "Preencha os campos corretamente!!");
				}
			}
		});

		botaoGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int resposta = JOptionPane.showConfirmDialog(janelaCorrida, "Deseja atualizar?", "Confirmação",
							JOptionPane.YES_NO_OPTION);
					if (resposta == JOptionPane.YES_OPTION) {
						int id;
						try {
							id = Integer.parseInt(jTextId.getText());
						} catch (NumberFormatException ex) {
							JOptionPane.showMessageDialog(janelaCorrida, "ID inválido! Insira um número válido.");
							return;
						}
						String nome = jTextNome.getText().trim(); // Retira os espaços em branco
						String local = jTextLocal.getText().trim(); // Retira os espaços em branco
						int vencedorId;
						Object selectedItem = dropdownVencedorId.getSelectedItem();
						if (selectedItem == null) {
							JOptionPane.showMessageDialog(janelaCorrida,
									"Não é possível cadastrar a corrida pois não há pilotos cadastrados!");
							return;
						} else {
							try {
								vencedorId = (int) selectedItem;
							} catch (ClassCastException ex) {
								JOptionPane.showMessageDialog(janelaCorrida, "Erro ao obter o ID do piloto vencedor!");
								return;
							}
						}
						if (nome.length() == 0) {
							JOptionPane.showMessageDialog(janelaCorrida, "Preencha o campo nome");
							jTextNome.requestFocus();
						} else if (local.length() == 0) {
							JOptionPane.showMessageDialog(janelaCorrida, "Preencha o campo local");
							jTextLocal.requestFocus();
						} else {
							if (!corrida.consultarCorrida(id)) {
								if (!corrida.cadastrarCorrida(id, nome, local, vencedorId)) {
									JOptionPane.showMessageDialog(janelaCorrida, "Erro no cadastro da corrida!");
								} else {
									JOptionPane.showMessageDialog(janelaCorrida, "Cadastro realizado!");
								}
							} else {
								if (!corrida.atualizarVencedor(id, vencedorId)) {
									JOptionPane.showMessageDialog(janelaCorrida,
											"Erro na atualização da corrida! O piloto vencedor precisa estar cadastrado!");
								} else {
									JOptionPane.showMessageDialog(janelaCorrida, "Atualização realizada!");
								}
							}
						}
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(janelaCorrida, "Erro inesperado: " + ex.getMessage());
				} finally {
					resetarJanela();
				}
			}
		});

		botaoApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(jTextId.getText());
				// Verifica se a corrida existe
				if (corrida.consultarCorrida(id)) {
					int confirmacao = JOptionPane.showConfirmDialog(janelaCorrida, "Tem certeza?", "Apagar corrida",
							JOptionPane.YES_NO_OPTION);
					if (confirmacao == JOptionPane.YES_OPTION) {
						// Apaga a corrida da tabela
						corrida.apagarCorrida(id);
						JOptionPane.showMessageDialog(janelaCorrida, "Corrida apagada da tabela!");
						resetarJanela();
					}
				} else {
					JOptionPane.showMessageDialog(janelaCorrida, "Corrida não encontrada na tabela!");
				}
			}
		});

		botaoLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetarJanela();
			}
		});
		return janelaCorrida;
	}

	// reseta a janela para as condições originais
	public void resetarJanela() {
		jTextId.setText(""); // Limpar campos
		jTextNome.setText("");
		jTextLocal.setText("");
		dropdownVencedorId.removeAllItems();
		jTextVencedorNome.setText("");
		jTextEquipeVencedora.setText("");
		jTextId.setEnabled(true);
		jTextNome.setEnabled(false);
		jTextLocal.setEnabled(false);
		dropdownVencedorId.setEnabled(false);
		botaoConsultar.setEnabled(true);
		botaoGravar.setEnabled(false);
		botaoLimpar.setEnabled(true);
		botaoApagar.setEnabled(false);
		jTextId.requestFocus(); // Colocar o foco em um campo
		botaoGravar.setBounds(50, 300, 100, 20);
		botaoGravar.setText("Gravar");
	}
}