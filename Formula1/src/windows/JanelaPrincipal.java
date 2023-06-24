package windows;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.WindowConstants;

public class JanelaPrincipal {
    /**
     * Apresenta o menu principal da aplicação.
     */
	public void apresentarMenu() {
		// define a janela
		JFrame janelaPrincipal = new JFrame("Administração F1");
		janelaPrincipal.setResizable(false);
		janelaPrincipal.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		janelaPrincipal.getContentPane().setBackground(Color.DARK_GRAY);
		janelaPrincipal.setSize(390, 300);
		janelaPrincipal.setLocationRelativeTo(null);
		// cria o menu da janela principal
		JMenuBar menuBar = new JMenuBar();
		// adiciona a barra de menu ao frame
		janelaPrincipal.setJMenuBar(menuBar);
		// define e adiciona menu na barra de menu
		JMenu menuConsultar = new JMenu("Consultar");
		menuBar.add(menuConsultar);
		JMenu menuAtualizar = new JMenu("Atualizar");
		menuBar.add(menuAtualizar);
		// cria e adiciona os itens
		JMenuItem menuEquipe = new JMenuItem("Equipe");
		menuAtualizar.add(menuEquipe);
		JMenuItem menuPiloto = new JMenuItem("Piloto");
		menuAtualizar.add(menuPiloto);
		JMenuItem menuCorrida = new JMenuItem("Corrida");
		menuAtualizar.add(menuCorrida);
		// cria as janelas de atualização
		JMenuItem tabelaEquipes = new JMenuItem("Equipes");
		menuConsultar.add(tabelaEquipes);
		JMenuItem tabelaPilotos = new JMenuItem("Pilotos");
		menuConsultar.add(tabelaPilotos);
		JMenuItem tabelaCorridas = new JMenuItem("Corridas");
		menuConsultar.add(tabelaCorridas);
		JanelaPiloto janelaPiloto = new JanelaPiloto();
		JFrame piloto = janelaPiloto.criarJanelaPiloto();
		JanelaEquipe janelaEquipe = new JanelaEquipe();
		JFrame equipe = janelaEquipe.criarJanelaEquipe();
		JanelaCorrida janelaCorrida = new JanelaCorrida();
		JFrame corrida = janelaCorrida.criarJanelaCorrida();
		JanelaConsultaPilotos consultaPilotos = new JanelaConsultaPilotos();
		JFrame dadosPilotos = consultaPilotos.criarJanela();
		JanelaConsultaEquipes consultaEquipes = new JanelaConsultaEquipes();
		JFrame dadosEquipes = consultaEquipes.criarJanela();
		JanelaConsultaCorridas consultaCorridas = new JanelaConsultaCorridas();
		JFrame dadosCorridas = consultaCorridas.criarJanela();
		JLabel label = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/f1icon.png")).getImage();
		label.setIcon(new ImageIcon(img));
		label.setBounds(30, 0, 0, 0);
		janelaPrincipal.add(label);
		// adiciona ação para os itens de menu
		/**
		 * Adiciona um ActionListener ao item de menu "Piloto" para exibir a janela de pilotos quando selecionado.
		 *
		 * @param e o evento de ação que ocorreu
		 */
		menuPiloto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				piloto.setVisible(true);
			}
		});
		/**
		 * Adiciona um ActionListener ao item de menu "Equipe" para exibir a janela de equipe quando selecionado.
		 *
		 * @param e o evento de ação que ocorreu
		 */
		menuEquipe.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				equipe.setVisible(true);
			}
		});
		/**
		 * Adiciona um ActionListener ao item de menu "Corrida" para exibir a janela de corrida quando selecionado.
		 *
		 * @param e o evento de ação que ocorreu
		 */
		menuCorrida.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				corrida.setVisible(true);
			}
		});
		/**
		 * Adiciona um ActionListener ao item de menu "Pilotos" para exibir a janela de dados dos pilotos quando selecionado.
		 *
		 * @param e o evento de ação que ocorreu
		 */
		tabelaPilotos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dadosPilotos.setVisible(true);
			}
		});
		/**
		 * Adiciona um ActionListener ao item de menu "Equipes" para exibir a janela de dados das equipes quando selecionado.
		 *
		 * @param e o evento de ação que ocorreu
		 */
		tabelaEquipes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dadosEquipes.setVisible(true);
			}
		});
		/**
		 * Adiciona um ActionListener ao item de menu "Corridas" para exibir a janela de dados das corridas quando selecionado.
		 *
		 * @param e o evento de ação que ocorreu
		 */
		tabelaCorridas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dadosCorridas.setVisible(true);
			}
		});
		
		janelaPrincipal.setVisible(true);
	}
}
