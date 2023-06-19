package windows;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import entities.Corrida;

public class JanelaCorrida {

	public static JFrame criarJanelaCorrida() {
		// Define a janela
		JFrame janelaCorrida = new JFrame("Atualização de corrida"); // Janela Normal
		janelaCorrida.setResizable(false); // A janela não poderá ter o tamanho ajustado
		janelaCorrida.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		janelaCorrida.setSize(500, 400); // Define tamanho da janela
		// Define o layout da janela
		Container caixa = janelaCorrida.getContentPane();
		caixa.setLayout(null);
		// Define os labels dos campos
		JLabel labelId = new JLabel("ID: ");
		JLabel labelNome = new JLabel("Nome: ");
		JLabel labelLocal = new JLabel("Local: ");
		JLabel labelVencedorId = new JLabel("Vencedor ID: ");
		// Posiciona os labels na janela
		labelId.setBounds(50, 40, 100, 20); // coluna, linha, largura, tamanho
		labelNome.setBounds(50, 80, 100, 20);
		labelLocal.setBounds(50, 120, 100, 20);
		labelVencedorId.setBounds(50, 160, 100, 20);
		// Define os input box
		JTextField jTextId = new JTextField();
		JTextField jTextNome = new JTextField();
		JTextField jTextLocal = new JTextField();
		JTextField jTextVencedorId = new JTextField();
		// Define se os campos estão habilitados ou não no início
		jTextId.setEnabled(true);
		jTextNome.setEnabled(false);
		jTextLocal.setEnabled(false);
		jTextVencedorId.setEnabled(false);
		// Posiciona os input box
		jTextId.setBounds(180, 40, 50, 20);
		jTextNome.setBounds(180, 80, 150, 20);
		jTextLocal.setBounds(180, 120, 150, 20);
		jTextVencedorId.setBounds(180, 160, 50, 20);
		// Adiciona os rótulos e os input box na janela
		janelaCorrida.add(labelId);
		janelaCorrida.add(labelNome);
		janelaCorrida.add(labelLocal);
		janelaCorrida.add(labelVencedorId);
		janelaCorrida.add(jTextId);
		janelaCorrida.add(jTextNome);
		janelaCorrida.add(jTextLocal);
		janelaCorrida.add(jTextVencedorId);
		// Define botões e a localização deles na janela
		JButton botaoConsultar = new JButton("Consultar");
		botaoConsultar.setBounds(230, 40, 100, 20);
		janelaCorrida.add(botaoConsultar);
		JButton botaoGravar = new JButton("Gravar");
		botaoGravar.setBounds(50, 275, 100, 20);
		botaoGravar.setEnabled(false);
		janelaCorrida.add(botaoGravar);
		JButton botaoLimpar = new JButton("Limpar");
		botaoLimpar.setBounds(350, 275, 100, 20);
		janelaCorrida.add(botaoLimpar);
		JButton botaoApagar = new JButton("Apagar");
		botaoApagar.setBounds(200, 275, 100, 20);
		botaoApagar.setEnabled(false);
		janelaCorrida.add(botaoApagar);
		// Define objeto corrida para pesquisar no banco de dados
		Corrida corrida = new Corrida();
		// Define ações dos botões
		botaoConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int id = Integer.parseInt(jTextId.getText());
					botaoGravar.setEnabled(true);
					String nome, local;
					int vencedorId;

					if (!corrida.consultarCorrida(id)) {
						nome = "";
						local = "";
						vencedorId = 0;
						jTextId.setEnabled(false);
						jTextLocal.setEnabled(true);
						botaoApagar.setEnabled(false);
						jTextNome.setEnabled(true);
						jTextNome.requestFocus();

					} else {
						nome = corrida.getNome();
						local = corrida.getLocal();
						vencedorId = corrida.getVencedor();
						jTextId.setEnabled(false);
						jTextLocal.setEnabled(false);
						botaoApagar.setEnabled(true);
						botaoGravar.setBounds(50, 275, 150, 20);
						botaoGravar.setText("Atualizar vencedor ID");
					}

					jTextNome.setText(nome);
					jTextLocal.setText(local);
					jTextVencedorId.setText(Integer.toString(vencedorId));
					jTextVencedorId.setEnabled(true);
					botaoConsultar.setEnabled(false);

				} catch (Exception erro) {
					JOptionPane.showMessageDialog(janelaCorrida, "Preencha os campos corretamente!!");
				}
			}
		});

		botaoGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int resposta = JOptionPane.showConfirmDialog(janelaCorrida, "Deseja atualizar?", "Confirmação",
						JOptionPane.YES_NO_OPTION);
				if (resposta == JOptionPane.YES_OPTION) {
					int id = Integer.parseInt(jTextId.getText());
					String nome = jTextNome.getText().trim(); // Retira os espaços em branco
					String local = jTextLocal.getText().trim(); // Retira os espaços em branco
					int vencedorId = Integer.parseInt(jTextVencedorId.getText());

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
								JOptionPane.showMessageDialog(janelaCorrida, "Erro na atualização da corrida!");
							} else {
								JOptionPane.showMessageDialog(janelaCorrida, "Atualização realizada!");
							}
						}
					}
				}
				jTextId.setText(""); // Limpar campo
				jTextNome.setText(""); // Limpar campo
				jTextLocal.setText(""); // Limpar campo
				jTextVencedorId.setText(""); // Limpar campo
				jTextId.setEnabled(true);
				jTextNome.setEnabled(false);
				jTextLocal.setEnabled(false);
				jTextVencedorId.setEnabled(false);
				botaoApagar.setEnabled(false);
				botaoConsultar.setEnabled(true);
				botaoGravar.setEnabled(false);
				botaoLimpar.setEnabled(true);
				jTextId.requestFocus(); // Colocar o foco em um campo
				botaoGravar.setBounds(50, 275, 100, 20);
				botaoGravar.setText("Gravar");
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

						jTextId.setText("");
						jTextNome.setText("");
						jTextLocal.setText("");
						jTextVencedorId.setText("");
						jTextId.setEnabled(true);
						jTextNome.setEnabled(false);
						jTextLocal.setEnabled(false);
						jTextVencedorId.setEnabled(false);

						botaoConsultar.setEnabled(true);
						botaoGravar.setEnabled(false);
						botaoApagar.setEnabled(false);
						botaoGravar.setBounds(50, 275, 100, 20);
						botaoGravar.setText("Gravar");
					}
				} else {
					JOptionPane.showMessageDialog(janelaCorrida, "Corrida não encontrada na tabela!");
				}
			}
		});

		botaoLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jTextId.setText(""); // Limpar campo
				jTextNome.setText(""); // Limpar campo
				jTextLocal.setText(""); // Limpar campo
				jTextVencedorId.setText(""); // Limpar campo
				jTextId.setEnabled(true);
				jTextNome.setEnabled(false);
				jTextLocal.setEnabled(false);
				jTextVencedorId.setEnabled(false);
				botaoConsultar.setEnabled(true);
				botaoGravar.setEnabled(false);
				botaoLimpar.setEnabled(true);
				botaoApagar.setEnabled(false);
				jTextId.requestFocus(); // Colocar o foco em um campo
				botaoGravar.setBounds(50, 275, 100, 20);
				botaoGravar.setText("Gravar");
			}
		});
		return janelaCorrida;

	}
}
