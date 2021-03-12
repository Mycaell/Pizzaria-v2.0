package View;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
//import javax.swing.UIManager;
//import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Controller.GerenteController;
import DTO.FuncionarioDTO;
import View_Utilidades.AdicionadorDeComponentes;
import View_Utilidades.Icones;


public abstract class TelaPadrao extends JFrame{

	public TelaPadrao(String titulo) {
		super(titulo);
		this.setSize(800,440);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		
		this.setResizable(false);
		
		this.setIconImage(Icones.ICONE_JANELA.getImage());
		this.getContentPane().setBackground(Color.lightGray);
		
//		this.getContentPane().setBackground(Color.DARK_GRAY);
//		this.getContentPane().setBackground(Color.orange);

		JLabel label = new JLabel(Icones.FUNDO_LISO);
		label.setBounds(0, 0, 800, 400);
		setContentPane(label);
		
		
//		adicionarComponentesGraficos();
//		this.setVisible(true);
		
////        // SETA LOOK AND FEEL
//        try {
//            // AQUI VOCÊ SETA O NOME DA CLASSE REFERENTE A CADA TEMA !
//		String tema = "com.jtattoo.plaf.Acryl.AcrylLookAndFeel";
//		
//            // AQUI VC SETA O LOOK AND FEEL
//            UIManager.setLookAndFeel(tema);
//        } catch (InstantiationException | IllegalAccessException  |
//                     UnsupportedLookAndFeelException | ClassNotFoundException e) {
//            System.out.println("Erro LAF : " + e.getMessage());
//        }

		
	}



	
	
	
//	public abstract void adicionarComponentesGraficos();
	
	
	
	
	
	
	
	
	
	
	
//	public void adicionarIdentificacao(FuncionarioDTO funcionarioDTO) {
//		GerenteController gerenteController = new GerenteController();
//		
//		AdicionadorDeComponentes.adicionarJLabel(this, "<html><u>"+gerenteController.recuperarCargo(funcionarioDTO).getCargo()+" ("+gerenteController.recuperarNome(funcionarioDTO).getNome()+" : "+funcionarioDTO.getCodigo()+")</u></html>", 5, 5, 1000, 25);
//	}
	
	
	
	
	
	
	
	
}
