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
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import entities.Equipe;
import entities.Piloto;

public class JanelaPiloto {
	private JFrame janelaPiloto;
	private JTextField jTextId;
	private JTextField jTextNome;
	private JTextField jTextNacionalidade;
	private JTextField jTextNumero;
	private JButton botaoConsultar;
	private JComboBox<String> jComboboxIdEquipe; // transformar em um combobox de pilotos depois
	private JButton botaoGravar;
	private JButton botaoLimpar;
	private JButton botaoApagar;
	private JButton botaoAtualizarEquipe;
	private JLabel labelId;
	private JLabel labelNome;
	private JLabel labelNacionalidade;
	private JLabel labelNumero;
	private JLabel labelNomeEquipe;


	public JFrame criarJanelaPiloto() {
		// Define a janela
		janelaPiloto = new JFrame("Atualização de piloto"); // Janela Normal
		janelaPiloto.setResizable(false); // A janela não poderá ter o tamanho ajustado
		janelaPiloto.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		janelaPiloto.setSize(510, 400); // Define tamanho da janela
		janelaPiloto.setLocationRelativeTo(null);
		// Define o layout da janela
		Container caixa = janelaPiloto.getContentPane();
		caixa.setLayout(null);
		// Define os labels dos campos
		labelId = new JLabel("ID: ");
		labelNome = new JLabel("Nome: ");
		labelNacionalidade = new JLabel("Nacionalidade: ");
		labelNumero = new JLabel("Nº do Carro: ");
		labelNomeEquipe = new JLabel("Equipe: ");
		// Posiciona os labels na janela
		labelId.setBounds(50, 40, 100, 20); // coluna, linha, largura, tamanho
		labelNome.setBounds(50, 80, 100, 20);
		labelNacionalidade.setBounds(50, 120, 100, 20);
		labelNumero.setBounds(50, 160, 100, 20);
		labelNomeEquipe.setBounds(50, 200, 100, 20);
		// Define os input box
		jTextId = new JTextField();
		jTextNome = new JTextField();
		jTextNacionalidade = new JTextField();
		jTextNumero = new JTextField();
		jComboboxIdEquipe = new JComboBox<>(); // Novo JComboBox para o dropdown
		// Define se os campos estão habilitados ou não no início
		jTextId.setEnabled(true);
		jTextNome.setEnabled(false);
		jTextNacionalidade.setEnabled(false);
		jTextNumero.setEnabled(false);
		jComboboxIdEquipe.setEnabled(false);
		// Define a cor da fonte quando desabilitados
		jTextId.setDisabledTextColor(Color.DARK_GRAY);
		jTextNome.setDisabledTextColor(Color.DARK_GRAY);
		jTextNacionalidade.setDisabledTextColor(Color.DARK_GRAY);
		jTextNumero.setDisabledTextColor(Color.DARK_GRAY);
		UIManager.put("ComboBox.disabledForeground", Color.GRAY);
		
		// Posiciona os input box
		jTextId.setBounds(200, 40, 50, 20);
		jTextNome.setBounds(200, 80, 150, 20);
		jTextNacionalidade.setBounds(200, 120, 150, 20);
		jTextNumero.setBounds(200, 160, 50, 20);
		jComboboxIdEquipe.setBounds(200, 200, 150, 20);
		//jTextIdEquipe.setBounds(300, 200, 30, 20);
		// Adiciona os rótulos e os input box na janela
		janelaPiloto.add(labelId);
		janelaPiloto.add(labelNome);
		janelaPiloto.add(labelNacionalidade);
		janelaPiloto.add(labelNumero);
		janelaPiloto.add(labelNomeEquipe);
		janelaPiloto.add(jTextId);
		janelaPiloto.add(jTextNome);
		janelaPiloto.add(jTextNacionalidade);
		janelaPiloto.add(jTextNumero);
		janelaPiloto.add(jComboboxIdEquipe);
		// Define botões e a localização deles na janela
		botaoConsultar = new JButton("Consultar");
		botaoConsultar.setBounds(250, 40, 100, 20);
		janelaPiloto.add(botaoConsultar);
		botaoGravar = new JButton("Gravar");
		botaoGravar.setBounds(50, 275, 100, 20);
		botaoGravar.setEnabled(false);
		janelaPiloto.add(botaoGravar);
		botaoLimpar = new JButton("Limpar");
		botaoLimpar.setBounds(350, 275, 100, 20);
		janelaPiloto.add(botaoLimpar);
		botaoApagar = new JButton("Apagar");
		botaoApagar.setBounds(200, 275, 100, 20);
		botaoApagar.setEnabled(false);
		janelaPiloto.add(botaoApagar);
		botaoAtualizarEquipe = new JButton("Trocar Equipe");
		botaoAtualizarEquipe.setBounds(50, 200, 130, 20);
		botaoAtualizarEquipe.setEnabled(false);
		botaoAtualizarEquipe.setVisible(false);
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
					int numCarro;
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
							labelNomeEquipe.setVisible(false);
							jTextId.setEnabled(false);
							jTextNumero.setEnabled(true);
							jTextNome.setText(nome);
							jTextNacionalidade.setText(nacionalidade);
							jTextNumero.setText(Integer.toString(numCarro));
							jTextNacionalidade.setEnabled(false);
							jComboboxIdEquipe.addItem(nomeEquipe);
							botaoApagar.setEnabled(true);
							botaoGravar.setBounds(50, 275, 130, 20);
							botaoApagar.setBounds(215, 275, 100, 20);
							botaoGravar.setText("Atualizar piloto");
							botaoAtualizarEquipe.setEnabled(true);
							botaoAtualizarEquipe.setVisible(true);
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
		            int resposta = JOptionPane.showConfirmDialog(janelaPiloto, "Deseja atualizar?", "Confirmação", JOptionPane.YES_NO_OPTION);
		            if (resposta == JOptionPane.YES_OPTION) {
		                int id, numCarro, idEquipe;
		                try {
		                    id = Integer.parseInt(jTextId.getText());
		                } catch (NumberFormatException ex) {
		                    JOptionPane.showMessageDialog(janelaPiloto, "ID inválido. Insira um número inteiro válido.");
		                    return;
		                }
		                String nome = jTextNome.getText().trim();
		                String nacionalidade = jTextNacionalidade.getText().trim();
		                try {
		                    numCarro = Integer.parseInt(jTextNumero.getText());
		                } catch (NumberFormatException ex) {
		                    JOptionPane.showMessageDialog(janelaPiloto, "Número de carro inválido. Insira um número inteiro válido.");
		                    return;
		                }

		                if (piloto.consultarNumeroCarro(numCarro) && piloto.getNumCarroById(id) != numCarro) {
		                    JOptionPane.showMessageDialog(janelaPiloto, "Número de carro já cadastrado. Escolha outro número.");
		                    return;
		                }

		                if (jComboboxIdEquipe.getSelectedItem() == null) {
		                    JOptionPane.showMessageDialog(janelaPiloto, "Não é possível cadastrar, pois não há equipes cadastradas. Um piloto sempre precisará de uma equipe!");
		                    return;
		                } else {
		                	String nomeEquipe = (String) jComboboxIdEquipe.getSelectedItem();
		                	Equipe equipe = new Equipe();
		                	idEquipe = equipe.getIdByName(nomeEquipe);
		                }

		                if (nome.length() == 0) {
		                    JOptionPane.showMessageDialog(janelaPiloto, "Preencha o campo nome");
		                    jTextNome.requestFocus();
		                } else if (nacionalidade.length() == 0) {
		                    JOptionPane.showMessageDialog(janelaPiloto, "Preencha o campo nacionalidade");
		                    jTextNacionalidade.requestFocus();
		                } else {
		                    if (!piloto.consultarPiloto(id)) {
		                        if (!piloto.atualizarPiloto(id, numCarro, idEquipe)) {
		                            JOptionPane.showMessageDialog(janelaPiloto, "Erro no cadastro do piloto!");
		                        } else {
		                            JOptionPane.showMessageDialog(janelaPiloto, "Cadastro realizado!");
		                        }
		                        resetJanela();
		                    } else {
		                        if (!piloto.atualizarPiloto(id, numCarro, idEquipe)) {
		                            JOptionPane.showMessageDialog(janelaPiloto, "Erro na atualização do piloto!");
		                        } else {
		                            JOptionPane.showMessageDialog(janelaPiloto, "Atualização realizada!");
		                            resetJanela();
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
					}
				} else {
					JOptionPane.showMessageDialog(janelaPiloto, "Piloto não encontrado na tabela!");
				}
			}
		});

		botaoLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetJanela();
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
		jTextId.setEnabled(true);
		jTextNome.setEnabled(false);
		jTextNacionalidade.setEnabled(false);
		jTextNumero.setEnabled(false);
		labelNomeEquipe.setVisible(true);
		botaoAtualizarEquipe.setEnabled(false);
		botaoAtualizarEquipe.setVisible(false);
		jComboboxIdEquipe.setEnabled(false);
		botaoConsultar.setEnabled(true);
		botaoGravar.setEnabled(false);
		botaoLimpar.setEnabled(true);
		botaoApagar.setEnabled(false);
		jTextId.requestFocus(); // Colocar o foco em um campo
		botaoGravar.setBounds(50, 275, 100, 20);
		botaoApagar.setBounds(200, 275, 100, 20);
		botaoGravar.setText("Gravar");
		jComboboxIdEquipe.removeAllItems();
	}

	// cria o combo box para que o piloto só possa ser cadastrado em equipes
	// existentes.
	public void ativarDropdown() {
		List<String> listaEquipes = new ArrayList<>();
		Equipe equipe = new Equipe();
		for (String i : equipe.getEquipeNameList()) {
			listaEquipes.add(i);
		}
		for (String item : listaEquipes) {
			jComboboxIdEquipe.addItem(item);
		}

		botaoConsultar.setEnabled(false);
	}
}
