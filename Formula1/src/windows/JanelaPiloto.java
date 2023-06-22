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
	private JFrame janelaPiloto;
	private JTextField jTextId;
	private JTextField jTextNome;
	private JTextField jTextNacionalidade;
	private JTextField jTextNumero;
	private JTextField jTextNomeEquipe;
	private JTextField jTextIdEquipe;
	private JButton botaoConsultar;
	private JComboBox<Integer> jComboboxIdEquipe;
	private JButton botaoGravar;
	private JButton botaoLimpar;
	private JButton botaoApagar;
	private JButton botaoAtualizarEquipe;

	public JFrame criarJanelaPiloto() {
		// Define a janela
		janelaPiloto = new JFrame("Atualização de piloto"); // Janela Normal
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
		JLabel labelNumero = new JLabel("Numero: ");
		JLabel labelEquipe = new JLabel("ID Equipe: ");
		JLabel labelNomeEquipe = new JLabel("Equipe: ");
		JLabel labelIdEquipe = new JLabel("ID: ");
		// Posiciona os labels na janela
		labelId.setBounds(50, 40, 100, 20); // coluna, linha, largura, tamanho
		labelNome.setBounds(50, 80, 100, 20);
		labelNacionalidade.setBounds(50, 120, 100, 20);
		labelNumero.setBounds(50, 160, 100, 20);
		labelEquipe.setBounds(50, 240, 100, 20);
		labelNomeEquipe.setBounds(50, 200, 100, 20);
		labelIdEquipe.setBounds(284, 200, 100, 20);
		// Define os input box
		jTextId = new JTextField();
		jTextNome = new JTextField();
		jTextNacionalidade = new JTextField();
		jTextNumero = new JTextField();
		jTextNomeEquipe = new JTextField();
		jTextIdEquipe = new JTextField();
		jComboboxIdEquipe = new JComboBox<>(); // Novo JComboBox para o dropdown
		// Define se os campos estão habilitados ou não no início
		jTextId.setEnabled(true);
		jTextNome.setEnabled(false);
		jTextNacionalidade.setEnabled(false);
		jTextNumero.setEnabled(false);
		jComboboxIdEquipe.setEnabled(false);
		jTextNomeEquipe.setEnabled(false);
		jTextIdEquipe.setEnabled(false);
		// Define a cor da fonte quando desabilitados
		jTextId.setDisabledTextColor(Color.DARK_GRAY);
		jTextNome.setDisabledTextColor(Color.DARK_GRAY);
		jTextNacionalidade.setDisabledTextColor(Color.DARK_GRAY);
		jTextNumero.setDisabledTextColor(Color.DARK_GRAY);
		jTextNomeEquipe.setDisabledTextColor(Color.DARK_GRAY);
		jTextIdEquipe.setDisabledTextColor(Color.DARK_GRAY);
		// Posiciona os input box
		jTextId.setBounds(180, 40, 50, 20);
		jTextNome.setBounds(180, 80, 150, 20);
		jTextNacionalidade.setBounds(180, 120, 150, 20);
		jTextNumero.setBounds(180, 160, 50, 20);
		jComboboxIdEquipe.setBounds(180, 240, 50, 20);
		jTextNomeEquipe.setBounds(180, 200, 100, 20);
		jTextIdEquipe.setBounds(300, 200, 30, 20);
		// Adiciona os rótulos e os input box na janela
		janelaPiloto.add(labelId);
		janelaPiloto.add(labelNome);
		janelaPiloto.add(labelNacionalidade);
		janelaPiloto.add(labelNumero);
		janelaPiloto.add(labelEquipe);
		janelaPiloto.add(labelNomeEquipe);
		janelaPiloto.add(labelIdEquipe);
		janelaPiloto.add(jTextId);
		janelaPiloto.add(jTextNome);
		janelaPiloto.add(jTextNacionalidade);
		janelaPiloto.add(jTextNumero);
		janelaPiloto.add(jComboboxIdEquipe);
		janelaPiloto.add(jTextNomeEquipe);
		janelaPiloto.add(jTextIdEquipe);
		// Define botões e a localização deles na janela
		botaoConsultar = new JButton("Consultar");
		botaoConsultar.setBounds(230, 40, 100, 20);
		janelaPiloto.add(botaoConsultar);
		botaoGravar = new JButton("Gravar");
		botaoGravar.setBounds(50, 300, 100, 20);
		botaoGravar.setEnabled(false);
		janelaPiloto.add(botaoGravar);
		botaoLimpar = new JButton("Limpar");
		botaoLimpar.setBounds(350, 300, 100, 20);
		janelaPiloto.add(botaoLimpar);
		botaoApagar = new JButton("Apagar");
		botaoApagar.setBounds(200, 300, 100, 20);
		botaoApagar.setEnabled(false);
		janelaPiloto.add(botaoApagar);
		botaoAtualizarEquipe = new JButton("Trocar Equipe");
		botaoAtualizarEquipe.setBounds(240, 240, 120, 20);
		botaoAtualizarEquipe.setEnabled(false);
		janelaPiloto.add(botaoAtualizarEquipe);
		// Define objeto piloto para pesquisar no banco de dados
		Piloto piloto = new Piloto();

		// Define ações dos botões
		botaoConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int id = Integer.parseInt(jTextId.getText());
					botaoGravar.setEnabled(true);
					String nome, nacionalidade, nomeEquipe;
					int numCarro, idEquipe;
					if (!piloto.consultarPiloto(id)) {
						// piloto não cadastrado, prepara a janela para receber os inputs
						JOptionPane.showMessageDialog(janelaPiloto, "Piloto não cadastrado.");
						nome = "";
						nacionalidade = "";
						numCarro = 0;
						jTextNome.setText(nome);
						jTextNacionalidade.setText(nacionalidade);
						jTextNumero.setText(Integer.toString(numCarro));
						jTextId.setEnabled(false);
						jTextNacionalidade.setEnabled(true);
						botaoApagar.setEnabled(false);
						jTextNome.setEnabled(true);
						jTextNome.requestFocus();
						jComboboxIdEquipe.setEnabled(true);
						ativarDropdown();
					} else {
						// piloto cadastrado, mostra os atributos na tela
						Equipe equipe = new Equipe();
						if (equipe.consultarEquipe(piloto.getIdEquipe())) {
							nome = piloto.getNome();
							nacionalidade = piloto.getNacionalidade();
							numCarro = piloto.getNumCarro();
							nomeEquipe = equipe.getNome();
							idEquipe = equipe.getIdEquipe();
							jTextId.setEnabled(false);
							jTextNumero.setEnabled(true);
							labelEquipe.setText("Atualizar equipe: ");
							jTextNomeEquipe.setText(nomeEquipe);
							jTextNome.setText(nome);
							jTextNacionalidade.setText(nacionalidade);
							jTextNumero.setText(Integer.toString(numCarro));
							jTextIdEquipe.setText(Integer.toString(idEquipe));
							jTextNacionalidade.setEnabled(false);
							jComboboxIdEquipe.addItem(idEquipe);
							botaoApagar.setEnabled(true);
							botaoGravar.setBounds(50, 300, 130, 20);
							botaoGravar.setText("Atualizar piloto");
							botaoAtualizarEquipe.setEnabled(true);
						}
					}
					
					jTextNumero.setEnabled(true);

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
						int id, numCarro, idEquipe;
						try {
							id = Integer.parseInt(jTextId.getText());
						} catch (NumberFormatException ex) {
							JOptionPane.showMessageDialog(janelaPiloto,
									"ID inválido. Insira um número inteiro válido.");
							return;
						}
						String nome = jTextNome.getText().trim();
						String nacionalidade = jTextNacionalidade.getText().trim();
						try {
							numCarro = Integer.parseInt(jTextNumero.getText());
							if (piloto.consultarNumeroCarro(numCarro)) {
								if (piloto.getIdPiloto() != piloto.getNumCarro()) {
									JOptionPane.showMessageDialog(janelaPiloto, "Numero já cadastrado, escolha outro numero.");
									return;
								}
							}
						} catch (NumberFormatException ex) {
							JOptionPane.showMessageDialog(janelaPiloto,
									"Número de carro inválido. Insira um número inteiro válido.");
							return;
						}

						if (jComboboxIdEquipe.getSelectedItem() == null) {
							JOptionPane.showMessageDialog(janelaPiloto,
									"Não é possível cadastrar pois não há equipes cadastradas. Um piloto sempre precisará de uma equipe!");
							return;
						} else {
							idEquipe = (int) jComboboxIdEquipe.getSelectedItem();
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
								resetJanela();
								labelEquipe.setText("ID Equipe: ");
							} else {
								if (!piloto.atualizarPiloto(id, numCarro, idEquipe)) {
									JOptionPane.showMessageDialog(janelaPiloto, "Erro na atualização do piloto!");
								} else {
									JOptionPane.showMessageDialog(janelaPiloto, "Atualização realizada!");
									resetJanela();
									labelEquipe.setText("ID Equipe: ");
								}
							}
						}
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(janelaPiloto, "Ocorreu um erro: " + ex.getMessage());
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
						if (!piloto.apagarPiloto(id)) {
							JOptionPane.showMessageDialog(janelaPiloto,
									"Piloto não apagado pois está cadastrado como vencedor de uma corrida");
						} else {
							JOptionPane.showMessageDialog(janelaPiloto, "Piloto apagado da tabela!");
						}
						resetJanela();
						labelEquipe.setText("ID Equipe: ");
					}
				} else {
					JOptionPane.showMessageDialog(janelaPiloto, "Piloto não encontrado na tabela!");
				}
			}
		});

		botaoLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetJanela();
				labelEquipe.setText("ID Equipe: ");
			}
		});

		botaoAtualizarEquipe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(jTextId.getText());
				Piloto piloto = new Piloto();
				if (piloto.consultarPiloto(id)) {
					jComboboxIdEquipe.removeAllItems();
					jComboboxIdEquipe.setEnabled(true);
					ativarDropdown();
				}

			}
		});
		return janelaPiloto;
	}

	// reseta a janela para as condições originais
	public void resetJanela() {
		jTextId.setText(""); // Limpar campo
		jTextNome.setText(""); // Limpar campo
		jTextNacionalidade.setText(""); // Limpar campo
		jTextNumero.setText(""); // Limpar campo
		jTextIdEquipe.setText("");
		jTextId.setEnabled(true);
		jTextNome.setEnabled(false);
		jTextNacionalidade.setEnabled(false);
		jTextNumero.setEnabled(false);
		jTextNomeEquipe.setText("");
		botaoAtualizarEquipe.setEnabled(false);
		jComboboxIdEquipe.setEnabled(false);
		botaoConsultar.setEnabled(true);
		botaoGravar.setEnabled(false);
		botaoLimpar.setEnabled(true);
		botaoApagar.setEnabled(false);
		jTextId.requestFocus(); // Colocar o foco em um campo
		botaoGravar.setBounds(50, 300, 100, 20);
		botaoGravar.setText("Gravar");
		jComboboxIdEquipe.removeAllItems();
	}

	// cria o combo box para que o piloto só possa ser cadastrado em equipes
	// existentes.
	public void ativarDropdown() {
		List<Integer> listaEquipes = new ArrayList<>();
		Equipe equipe = new Equipe();
		for (Integer i : equipe.getEquipeIdList()) {
			listaEquipes.add(i);
		}
		for (Integer item : listaEquipes) {
			jComboboxIdEquipe.addItem(item);
		}
		jComboboxIdEquipe.getSelectedItem();

		botaoConsultar.setEnabled(false);
	}
}
