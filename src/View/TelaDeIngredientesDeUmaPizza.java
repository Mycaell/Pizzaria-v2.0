package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controller.GerenteController;
import DTO.PizzaDTO;
import View_Utilidades.Icones;

public class TelaDeIngredientesDeUmaPizza extends JDialog{

	
	private int idDaPizza;
	private String sabor;
	
	private JPanel painelIngredientes;
	
	public TelaDeIngredientesDeUmaPizza(int idDaPizza, String sabor) {
		setTitle("Ingredientes da Pizza");
		setSize(300,201);
		setResizable(false);
		setLayout(null);
		
		
		
		
		this.idDaPizza = idDaPizza;
		this.sabor = sabor;
		
		adicionarComponentes();
		
		setVisible(true);
	}

	
	public void adicionarComponentes() {
		JLabel label = new JLabel(Icones.FUNDO_LISO);
		label.setBounds(0, 0, 270, 190);
		setContentPane(label);

		JLabel L = new JLabel(sabor);
		L.setBounds(60, 5, 270, 30);
		L.setFont(new Font("Comic Sans", Font.BOLD, 18));
		add(L);

		
		painelIngredientes = new JPanel(null);

		painelIngredientes.setBounds(10, 40, 270, 125);
		painelIngredientes.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		
		DefaultTableModel modelo = new DefaultTableModel();
		
		JTable tabela = new JTable(modelo);
		
		
		modelo.addColumn("Ingredientes");

		PizzaDTO pizzaDTO = new PizzaDTO();
		pizzaDTO.setIdDaPizza(idDaPizza);
		
		GerenteController gerenteController = new GerenteController();

		ArrayList<String[]> ingredientes = gerenteController.recuperarIngredientesDeUmaPizza(pizzaDTO).getIngredientes();
		
		for (String[] i : ingredientes) {
			Object[] linha = {i[1]};
			modelo.addRow(linha);
		}
		
		JScrollPane scroll = new JScrollPane(tabela);
		scroll.setBounds(0, 0, 275, 95);
		painelIngredientes.add(scroll);
		
		JButton botaoOk = new JButton("OK");
		botaoOk.setBounds(103, 100, 80, 20);
		botaoOk.setFocusPainted(false);
		botaoOk.addActionListener(new OuvinteBotaoOK(this));
		painelIngredientes.add(botaoOk);
		
		
		this.add(painelIngredientes);
		
	}
	

	private class OuvinteBotaoOK implements ActionListener{

		private TelaDeIngredientesDeUmaPizza telaDeIngredientesDeUmaPizza;
		
		public OuvinteBotaoOK(TelaDeIngredientesDeUmaPizza telaDeIngredientesDeUmaPizza) {
			this.telaDeIngredientesDeUmaPizza = telaDeIngredientesDeUmaPizza;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			telaDeIngredientesDeUmaPizza.dispose();
		}
		
	}
	
}
