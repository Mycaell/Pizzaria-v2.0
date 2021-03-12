package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import View_Utilidades.Icones;

public abstract class TelaComMenu extends TelaPadrao{


	public TelaComMenu(String titulo) {
		super(titulo);
		
		adicionarBarraDeMenu();
	}

	private void adicionarBarraDeMenu() {
		
		JMenuBar barra = new JMenuBar();
		
		JMenu opcoes = new JMenu();
		opcoes.setIcon(Icones.ICONE_ENGRENAGEM);
		barra.add(opcoes);
		
		
		JMenuItem itemSair = new JMenuItem("Sair");
		opcoes.add(itemSair);
				
//		itemSair.setMnemonic(KeyEvent.);
		itemSair.addActionListener(new OuvinteMenu(this));
		itemSair.setIcon(Icones.ICONE_SAIR);
		
		this.setJMenuBar(barra);
		
	}

	private class OuvinteMenu implements ActionListener{

		private JFrame janela;
		
		public OuvinteMenu(JFrame janela) {
			this.janela = janela;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {

//			l�gica que ser� executada ao clicar no item sair
			TelaDeLogin telaDeLogin = new TelaDeLogin();
			telaDeLogin.setLocationRelativeTo(janela);
			janela.dispose();
		}
	
	}
	
}
