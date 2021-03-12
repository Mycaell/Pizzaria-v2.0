package View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import Controller.GerenteController;
import DTO.IngredienteDTO;
import View_Utilidades.AdicionadorDeComponentes;
import View_Utilidades.Icones;

public class TelaNovoIngrediente extends JDialog{
	
	
	private TelaDeIngredientes telaDeIngredientes;
	
	private int codigoDoGerenteLogado;

	private JTextField campoNome;
	private JTextField campoPreco;
	
	
	private JTabbedPane aba;
	private JPanel painelIngrediente;
	
	public TelaNovoIngrediente(TelaDeIngredientes telaDeIngredientes, int codigoDoGerenteLogado) {
		setTitle("Novo Ingrediente");
		setSize(270, 210);
		setResizable(false);
		setLayout(null);
		
		
		this.telaDeIngredientes = telaDeIngredientes;
		this.codigoDoGerenteLogado = codigoDoGerenteLogado;
		
		aba = new JTabbedPane();
		
		adicionarAba();
		
		
		
		
		
		
		setVisible(true);
	}
	
	
	private void adicionarAba() {
		adicionarJLabels();
		adicionarFields();
		adicionarBotoes();
		
		

		aba.addTab("Ingrediente", painelIngrediente);
		aba.setBounds(15, 40, 236, 135);
		add(aba);
		
	}
	
	public void adicionarJLabels() {
		JLabel label = new JLabel(Icones.FUNDO_LISO);
		label.setBounds(0, 0, 270, 190);
		setContentPane(label);

		
		JLabel L = new JLabel("Insira os dados");
		L.setBounds(60, 5, 140, 30);
		L.setFont(new Font("Comic Sans", Font.BOLD, 18));
		add(L);
		
		painelIngrediente = new JPanel(null);
		
	     
	     
	     AdicionadorDeComponentes.adicionarJLabel(painelIngrediente, "Nome", 5, 5, 40, 20);
	     AdicionadorDeComponentes.adicionarJLabel(painelIngrediente, "Valor", 5, 40, 40, 20);
	     
	     
	     
    
	}
	
	private void adicionarFields() {
	     
	     campoNome = AdicionadorDeComponentes.adicionarJTextField(painelIngrediente, 40, 5, 180, 20);
	     campoPreco = AdicionadorDeComponentes.adicionarJTextField(painelIngrediente, 40, 40, 180, 20);
	     
	     
	}
	
	private void adicionarBotoes() {
		
		Ouvinte ouvinte = new Ouvinte(this);

		JButton botaoAdicionar = AdicionadorDeComponentes.adicionarJButton(painelIngrediente, "Salvar", 0, 80, 110, 20);
		botaoAdicionar.setIcon(Icones.ICONE_SALVAR);
		botaoAdicionar.addActionListener(ouvinte);
     
		JButton botaoCancelar = AdicionadorDeComponentes.adicionarJButton(painelIngrediente, "Cancelar", 125, 80, 105, 20);
		botaoCancelar.setIcon(Icones.ICONE_EXCLUIR);
		botaoCancelar.addActionListener(ouvinte);
		
	}
	
	public class Ouvinte implements ActionListener{

		private TelaNovoIngrediente telaNovoIngrediente;
		
		
		public Ouvinte(TelaNovoIngrediente telaNovoIngrediente) {
				this.telaNovoIngrediente = telaNovoIngrediente;
			}
			 
		public void actionPerformed(ActionEvent e) {
			
			String botao = e.getActionCommand();
			
			
		 	if(botao.equals("Salvar")) {
//		 		fazer validações dos campos
				String nomeDigitado = telaNovoIngrediente.campoNome.getText();
				float valorDigitado = Float.parseFloat(telaNovoIngrediente.campoPreco.getText());							
				
				GerenteController gerenteController = new GerenteController();
				IngredienteDTO ingredienteDTO = new IngredienteDTO(nomeDigitado, valorDigitado);				
				gerenteController.adicionarIngrediente(ingredienteDTO);
				
				JOptionPane.showMessageDialog(telaNovoIngrediente, "Ingrediente adicionado!"); 

				
//				gambiarra pra atualizar a tela
				TelaDeIngredientes telaDeIngredientesAtualizada = new TelaDeIngredientes(codigoDoGerenteLogado);
				telaDeIngredientesAtualizada.setLocationRelativeTo(telaNovoIngrediente);
				telaNovoIngrediente.dispose();
				TelaNovoIngrediente.this.telaDeIngredientes.dispose();

		 	
		 	}else {
				   telaNovoIngrediente.dispose();
			}
		}
	}
	

}
