package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import View_Utilidades.Icones;


public class TelaDeModoDePreparo extends JDialog{

	
	private String sabor;
	private String modoDePreparo;
	
	
	private JPanel painelModoPreparo;
	
	public TelaDeModoDePreparo(String sabor, String modoDePreparo) {
		
		setTitle("Modo de preparo");
		setSize(300,201);
		setResizable(false);
		setLayout(null);
		
		this.sabor = sabor;
		this.modoDePreparo = modoDePreparo;
		

		
		adicionarComponentes();
		
		setVisible(true);
	}

	

	private void adicionarComponentes() {

		JLabel label = new JLabel(Icones.FUNDO_LISO);
		label.setBounds(0, 0, 270, 190);
		setContentPane(label);
		
		painelModoPreparo = new JPanel(null);
		painelModoPreparo.setBounds(10, 40, 270, 125);
		painelModoPreparo.setBorder(BorderFactory.createLineBorder(Color.GRAY));
//		painelModoPreparo.setBackground(Color.blue);
		
		JLabel L = new JLabel(sabor);
		L.setBounds(60, 5, 270, 30);
		L.setFont(new Font("Comic Sans", Font.BOLD, 18));
		add(L);
		
		
		
		
		JLabel label2 = new JLabel("Modo de preparo:");
		label2.setBounds(10, 2, 100, 20);
		painelModoPreparo.add(label2);
		
		
		JTextArea modoPreparo = new JTextArea(modoDePreparo);
		modoPreparo.setLineWrap(true);
		modoPreparo.setWrapStyleWord(true);
		modoPreparo.setEnabled(false);
		
		JScrollPane scroll = new JScrollPane(modoPreparo);
		scroll.setBounds(15, 27, 240, 60);
		painelModoPreparo.add(scroll);
		
		
		JButton botaoOk = new JButton("OK");
		botaoOk.setBounds(103, 95, 80, 20);
		botaoOk.setFocusPainted(false);
		botaoOk.addActionListener(new OuvinteBotaoOK(this));
		painelModoPreparo.add(botaoOk);
	
		this.add(painelModoPreparo);
	}
	

	
	
	private class OuvinteBotaoOK implements ActionListener{
		
		private TelaDeModoDePreparo telaDeModoDePreparo;
		
		public OuvinteBotaoOK(TelaDeModoDePreparo telaDeModoDePreparo) {
			this.telaDeModoDePreparo = telaDeModoDePreparo;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			telaDeModoDePreparo.dispose();
		}
		
	}
	
	
}
