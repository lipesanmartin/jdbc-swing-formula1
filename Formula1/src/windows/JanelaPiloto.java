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

import entities.Equipe;
import entities.Piloto;

public class JanelaPiloto {

	public static JFrame criarJanelaPiloto() {
		// Define a janela
		JFrame janelaPiloto = new JFrame("Atualização de piloto"); // Janela Normal
		janelaPiloto.setResizable(false); // A janela n�o poder� ter o tamanho ajustado
		janelaPiloto.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		janelaPiloto.setSize(500, 450); // Define tamanho da janela
		janelaPiloto.setLocationRelativeTo(null);
		// Define o layout da janela
		Container caixa = janelaPiloto.getContentPane();
		caixa.setLayout(null);
		// Define os labels dos campos
		JLabel labelId = new JLabel("ID: ");
		JLabel labelNome = new JLabel("Nome: ");
		JLabel labelNacionalidade = new JLabel("Nacionalidade: ");
		JLabel labelNum = new JLabel("Numero: ");
		JLabel labelEquipe = new JLabel("ID Equipe: ");
		JLabel labelNomeEquipe = new JLabel("Equipe: ");
		// Posiciona os labels na janela
		labelId.setBounds(50, 40, 100, 20); // coluna, linha, largura, tamanho
		labelNome.setBounds(50, 80, 100, 20);
		labelNacionalidade.setBounds(50, 120, 100, 20);
		labelNum.setBounds(50, 160, 100, 20);
		labelEquipe.setBounds(50, 240, 100, 20);
		labelNomeEquipe.setBounds(50, 200, 100, 20);
		// Define os input box
		JTextField jTextId = new JTextField();
		JTextField jTextNome = new JTextField();
		JTextField jTextNacionalidade = new JTextField();
		JTextField jTextNumero = new JTextField();
		JTextField jTextNomeEquipe = new JTextField();
		JComboBox<Integer> equipeDropdown = new JComboBox<>(); // Novo JComboBox para o dropdown
		// Define se os campos est�o habilitados ou n�o no in�cio
		jTextId.setEnabled(true);
		jTextNome.setEnabled(false);
		jTextNacionalidade.setEnabled(false);
		jTextNumero.setEnabled(false);
		equipeDropdown.setEnabled(false);
		jTextNomeEquipe.setEnabled(false);
		jTextId.setDisabledTextColor(Color.DARK_GRAY);
		jTextNome.setDisabledTextColor(Color.DARK_GRAY);
		jTextNacionalidade.setDisabledTextColor(Color.DARK_GRAY);
		jTextNumero.setDisabledTextColor(Color.DARK_GRAY);
		jTextNomeEquipe.setDisabledTextColor(Color.DARK_GRAY);
		// Posiciona os input box
		jTextId.setBounds(180, 40, 50, 20);
		jTextNome.setBounds(180, 80, 150, 20);
		jTextNacionalidade.setBounds(180, 120, 150, 20);
		jTextNumero.setBounds(180, 160, 50, 20);
		equipeDropdown.setBounds(180, 240, 50, 20);
		jTextNomeEquipe.setBounds(180, 200, 100, 20);
		// Adiciona os r�tulos e os input box na janela
		janelaPiloto.add(labelId);
		janelaPiloto.add(labelNome);
		janelaPiloto.add(labelNacionalidade);
		janelaPiloto.add(labelNum);
		janelaPiloto.add(labelEquipe);
		janelaPiloto.add(jTextId);
		janelaPiloto.add(jTextNome);
		janelaPiloto.add(jTextNacionalidade);
		janelaPiloto.add(jTextNumero);
		janelaPiloto.add(equipeDropdown);
		janelaPiloto.add(labelNomeEquipe);
		janelaPiloto.add(jTextNomeEquipe);
		// Define botões e a localização deles na janela
		JButton botaoConsultar = new JButton("Consultar");
		botaoConsultar.setBounds(230, 40, 100, 20);
		janelaPiloto.add(botaoConsultar);
		JButton botaoGravar = new JButton("Gravar");
		botaoGravar.setBounds(50, 300, 100, 20);
		botaoGravar.setEnabled(false);
		janelaPiloto.add(botaoGravar);
		JButton botaoLimpar = new JButton("Limpar");
		botaoLimpar.setBounds(350, 300, 100, 20);
		janelaPiloto.add(botaoLimpar);
		JButton botaoApagar = new JButton("Apagar");
		botaoApagar.setBounds(200, 300, 100, 20);
		botaoApagar.setEnabled(false);
		janelaPiloto.add(botaoApagar);
		// Define objeto piloto para pesquisar no banco de dados
		Piloto piloto = new Piloto();
		// Define ações dos botões
		botaoConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int id = Integer.parseInt(jTextId.getText());
					botaoGravar.setEnabled(true);
					String nome, nacionalidade, nomeEquipe;
					int numCarro;

					if (!piloto.consultarPiloto(id)) {
						JOptionPane.showMessageDialog(janelaPiloto, "Piloto não cadastrado.");
						nome = "";
						nacionalidade = "";
						numCarro = 0;
						jTextNome.setText(nome);
						jTextNacionalidade.setText(nacionalidade);
						jTextNumero.setText(Integer.toString(numCarro));
						jTextId.setEnabled(false);
						jTextNacionalidade.setEnabled(true);
						equipeDropdown.setEnabled(true);
						botaoApagar.setEnabled(false);
						jTextNome.setEnabled(true);
						jTextNome.requestFocus();
					} else {
						// int idEquipe;
						Equipe equipe = new Equipe();
						if (equipe.consultarEquipe(piloto.getIdEquipe())) {
							nome = piloto.getNome();
							nacionalidade = piloto.getNacionalidade();
							numCarro = piloto.getNumCarro();
							nomeEquipe = equipe.getNome();
							jTextId.setEnabled(false);
							labelEquipe.setText("Atualizar equipe: ");
							jTextNomeEquipe.setText(nomeEquipe);
							jTextNome.setText(nome);
							jTextNacionalidade.setText(nacionalidade);
							jTextNumero.setText(Integer.toString(numCarro));
							
							equipeDropdown.setEnabled(true);
							jTextNacionalidade.setEnabled(false);
							botaoApagar.setEnabled(true);
							botaoGravar.setBounds(50, 300, 130, 20);
							botaoGravar.setText("Atualizar piloto");
						}

					}
					List<Integer> listaEquipes = new ArrayList<>();
					Equipe equipe = new Equipe();
					for (Integer i : equipe.getEquipeIdList()) {
						listaEquipes.add(i);
					}
					for (Integer item : listaEquipes) {
						equipeDropdown.addItem(item);
					}
//					
					equipeDropdown.getSelectedItem();
					jTextNumero.setEnabled(true);
					botaoConsultar.setEnabled(false);

				} catch (Exception erro) {
					JOptionPane.showMessageDialog(janelaPiloto, "Preencha os campos corretamente!!");
				}
			}
		});

		botaoGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int resposta = JOptionPane.showConfirmDialog(janelaPiloto, "Deseja atualizar?", "Confirmação",
							JOptionPane.YES_NO_OPTION);
					if (resposta == JOptionPane.YES_OPTION) {
						int id;
						try {
							id = Integer.parseInt(jTextId.getText());
						} catch (NumberFormatException ex) {
							JOptionPane.showMessageDialog(janelaPiloto,
									"ID inválido. Insira um número inteiro válido.");
							return;
						}

						String nome = jTextNome.getText().trim();
						String nacionalidade = jTextNacionalidade.getText().trim();
						int numCarro;
						try {
							numCarro = Integer.parseInt(jTextNumero.getText());
						} catch (NumberFormatException ex) {
							JOptionPane.showMessageDialog(janelaPiloto,
									"Número de carro inválido. Insira um número inteiro válido.");
							return;
						}

						int idEquipe;
						if (equipeDropdown.getSelectedItem() == null) {
							JOptionPane.showMessageDialog(janelaPiloto,
									"Não é possível cadastrar pois não há equipes cadastradas. Um piloto sempre precisará de uma equipe!");
							return;
						} else {
							idEquipe = (int) equipeDropdown.getSelectedItem();
						}

						if (nome.length() == 0) {
							JOptionPane.showMessageDialog(janelaPiloto, "Preencha o campo nome");
							jTextNome.requestFocus();
						} else if (nacionalidade.length() == 0) {
							JOptionPane.showMessageDialog(janelaPiloto, "Preencha o campo nacionalidade");
							jTextNacionalidade.requestFocus();
						} else {
							if (!piloto.consultarPiloto(id)) {
								if (!piloto.cadastrarPiloto(id, nome, nacionalidade, numCarro, idEquipe)) {
									JOptionPane.showMessageDialog(janelaPiloto, "Erro no cadastro do piloto!");
								} else {
									JOptionPane.showMessageDialog(janelaPiloto, "Cadastro realizado!");
								}
							} else {
								if (!piloto.atualizarPiloto(id, numCarro, idEquipe)) {
									JOptionPane.showMessageDialog(janelaPiloto, "Erro na atualização do piloto!");
								} else {
									JOptionPane.showMessageDialog(janelaPiloto, "Atualização realizada!");
								}
							}
						}
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(janelaPiloto, "Ocorreu um erro: " + ex.getMessage());
				} finally {
					jTextId.setText("");
					jTextNome.setText("");
					jTextNacionalidade.setText("");
					jTextNumero.setText("");
					jTextNomeEquipe.setText("");
					equipeDropdown.resetKeyboardActions();
					labelEquipe.setText("ID Equipe: ");
					jTextId.setEnabled(true);
					jTextNome.setEnabled(false);
					jTextNacionalidade.setEnabled(false);
					jTextNumero.setEnabled(false);
					equipeDropdown.setEnabled(false);
					botaoApagar.setEnabled(false);
					botaoConsultar.setEnabled(true);
					botaoGravar.setEnabled(false);
					botaoLimpar.setEnabled(true);
					jTextId.requestFocus();
					botaoGravar.setBounds(50, 300, 100, 20);
					botaoGravar.setText("Gravar");
					equipeDropdown.removeAllItems();
				}
			}
		});

		botaoApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(jTextId.getText());

				// Verifica se o piloto existe
				if (piloto.consultarPiloto(id)) {
					int confirmacao = JOptionPane.showConfirmDialog(janelaPiloto, "Tem certeza?", "Apagar piloto",
							JOptionPane.YES_NO_OPTION);
					if (confirmacao == JOptionPane.YES_OPTION) {
						// Apaga o piloto da tabela
						piloto.apagarPiloto(id);
						JOptionPane.showMessageDialog(janelaPiloto, "Piloto apagado da tabela!");

						jTextId.setText("");
						jTextNome.setText("");
						jTextNacionalidade.setText("");
						jTextNumero.setText("");
						jTextNomeEquipe.setText("");
						labelEquipe.setText("ID Equipe: ");
						jTextId.setEnabled(true);
						jTextNome.setEnabled(false);
						jTextNacionalidade.setEnabled(false);
						jTextNumero.setEnabled(false);
						equipeDropdown.setEnabled(false);
						botaoConsultar.setEnabled(true);
						botaoGravar.setEnabled(false);
						botaoApagar.setEnabled(false);
						botaoGravar.setBounds(50, 300, 100, 20);
						botaoGravar.setText("Gravar");
						equipeDropdown.removeAllItems();
					}
				} else {
					JOptionPane.showMessageDialog(janelaPiloto, "Piloto não encontrado na tabela!");
				}
			}
		});

		botaoLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jTextId.setText(""); // Limpar campo
				jTextNome.setText(""); // Limpar campo
				jTextNacionalidade.setText(""); // Limpar campo
				jTextNumero.setText(""); // Limpar campo
				labelEquipe.setText("ID Equipe: ");
				jTextId.setEnabled(true);
				jTextNome.setEnabled(false);
				jTextNacionalidade.setEnabled(false);
				jTextNumero.setEnabled(false);
				jTextNomeEquipe.setText("");
				equipeDropdown.setEnabled(false);
				botaoConsultar.setEnabled(true);
				botaoGravar.setEnabled(false);
				botaoLimpar.setEnabled(true);
				botaoApagar.setEnabled(false);
				jTextId.requestFocus(); // Colocar o foco em um campo
				botaoGravar.setBounds(50, 300, 100, 20);
				botaoGravar.setText("Gravar");
				equipeDropdown.removeAllItems();
			}
		});
		return janelaPiloto;

	}
}
