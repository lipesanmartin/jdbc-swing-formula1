package windows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.WindowConstants;

public class JanelaPrincipal {
	public static void apresentarMenu() {
		// define a janela
		JFrame janelaPrincipal = new JFrame("Administração F1");
		janelaPrincipal.setTitle("Seleção de categoria");
		janelaPrincipal.setResizable(false);
		janelaPrincipal.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		janelaPrincipal.setSize(400, 300);
		// cria o menu da janela principal
		JMenuBar menuBar = new JMenuBar();
		// adiciona a barra de menu ao frame
		janelaPrincipal.setJMenuBar(menuBar);
		// define e adiciona menu na barra de menu
		JMenu menuAtualizar = new JMenu("Atualizar");
		menuBar.add(menuAtualizar);
		// cria e adiciona os itens
		JMenuItem menuPiloto = new JMenuItem("Piloto");
		menuAtualizar.add(menuPiloto);
		JMenuItem menuEquipe = new JMenuItem("Equipe");
		menuAtualizar.add(menuEquipe);
		JMenuItem menuCorrida = new JMenuItem("Corrida");
		menuAtualizar.add(menuCorrida);
		// cria as janelas de atualização
		JFrame janelaPiloto = JanelaPiloto.criarJanelaPiloto();
		JFrame janelaEquipe = JanelaEquipe.criarJanelaEquipe();
		JFrame janelaCorrida = JanelaCorrida.criarJanelaCorrida();
		// adiciona açao para o item de menu
		menuPiloto.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				janelaPiloto.setVisible(true);
			}
		});
		menuEquipe.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				janelaEquipe.setVisible(true);
			}
		});
		menuCorrida.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				janelaCorrida.setVisible(true);
			}
		});
		janelaPrincipal.setVisible(true);
	}
}
