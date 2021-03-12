package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controller.GerenteController;
import DTO.FuncionarioDTO;
import DTO.PizzaDTO;
import View_Ouvintes.OuvinteQueLevaParaTelaDoGerente;
import View_Utilidades.AdicionadorDeComponentes;
import View_Utilidades.Icones;

public class TelaDePizzas extends TelaComMenu{


	private JTable tabela;
	private DefaultTableModel modelo;
	
	private int codigoDoGerenteLogado;
	
	
	private JTabbedPane aba;
	
	private JPanel painelPizzas;
	
	
	private boolean vimDaTelaDeAtendimento;
	
	public TelaDePizzas(int codigoDoGerenteLogado, boolean vimDaTelaDeAtendimento) {
		super("Pizzas da Casa");
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.codigoDoGerenteLogado = codigoDoGerenteLogado;
		this.vimDaTelaDeAtendimento = vimDaTelaDeAtendimento;
		
		aba = new JTabbedPane();
		adicionarAba();
		
		this.setVisible(true);
		
	}

	
//	@Override
//	public void adicionarComponentesGraficos() {
//		aba = new JTabbedPane();
//		adicionarAba();
//	}
	
	
	
	private void adicionarAba() {

		adicionarLabels();
		adicionarTabelaDePizzas();
		adicionarBotoes();
		
		
		
		aba.addTab("Dados das pizzas", painelPizzas);
		aba.setBounds(80, 115, 625, 240);
		add(aba);
		
	}

	

	
	private void adicionarLabels() {
		
		

		FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
		funcionarioDTO.setCodigo(codigoDoGerenteLogado);
		AdicionadorDeComponentes.adicionarIdentificacao(this, funcionarioDTO);
		
		painelPizzas = new JPanel(null);
		

		JLabel L = AdicionadorDeComponentes.adicionarJLabel(this, "PIZZAS", 265, 40, 450, 60);
		L.setFont(new Font("Times new Roman", Font.BOLD, 75));
		L.setForeground(Color.DARK_GRAY);
		
//		FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
//		funcionarioDTO.setCodigo(codigoDoGerenteLogado);
//		AdicionadorDeComponentes.adicionarIdentificacao(this, funcionarioDTO);
	
		
	}

	private void adicionarBotoes() {

		
//		FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
//		funcionarioDTO.setCodigo(codigoDoGerenteLogado);
//
//		GerenteController gerenteController = new GerenteController();
//		String cargo = gerenteController.recuperarCargo(funcionarioDTO).getCargo();
//
		
		OuvinteBotoesTelaDeSaboresDePizza ouvinte = new OuvinteBotoesTelaDeSaboresDePizza(this);
		
		if(vimDaTelaDeAtendimento) {
			JButton verIngredientes = AdicionadorDeComponentes.adicionarJButton(painelPizzas, "Ver Ingredientes", 200, 180, 220, 20);
			verIngredientes.setIcon(Icones.ICONE_OLHO_ABERTO);
			verIngredientes.addActionListener(ouvinte);
			
		}else {
//			cargo = gerente
			JButton botaoVoltar = AdicionadorDeComponentes.adicionarJButton(painelPizzas, "Voltar", 0, 180, 90, 20);
			botaoVoltar.setIcon(Icones.ICONE_VOLTAR);
			botaoVoltar.addActionListener(new OuvinteQueLevaParaTelaDoGerente(this, codigoDoGerenteLogado));
			
			
			JButton botaoEditar = AdicionadorDeComponentes.adicionarJButton(painelPizzas, "Editar", 170, 180, 90, 20);
			botaoEditar.setIcon(Icones.ICONE_EDICAO);
			botaoEditar.addActionListener(ouvinte);
			
			JButton adicionarSabor = AdicionadorDeComponentes.adicionarJButton(painelPizzas, "Adicionar Pizza", 475, 163, 145, 20);
			adicionarSabor.setIcon(Icones.ICONE_MAIS);
			adicionarSabor.addActionListener(ouvinte);
			
			JButton removerSabor = AdicionadorDeComponentes.adicionarJButton(painelPizzas, "Remover Pizza", 475, 188, 145, 20);
			removerSabor.setIcon(Icones.ICONE_MENOS);
			removerSabor.addActionListener(ouvinte);
			
			
			JButton verIngredientes = AdicionadorDeComponentes.adicionarJButton(painelPizzas, "Ver Ingredientes", 290, 180, 170, 20);
			verIngredientes.setIcon(Icones.ICONE_OLHO_ABERTO);
			verIngredientes.addActionListener(ouvinte);
			
		}
		
		
		
	}
	
	private void adicionarTabelaDePizzas() {
//		JLabel L = AdicionadorDeComponentes.adicionarJLabel(this, "Pizzas da Casa", 250, 20, 350, 30);
//		L.setFont(new Font("Comic Sans", Font.BOLD, 18));

		
		GerenteController gerenteController = new GerenteController();
		
		modelo = new DefaultTableModel();
		
		tabela = new JTable(modelo);
		
		ArrayList<String[]> dadosDeTodasPizzas = gerenteController.recuperarDadosDeTodasPizzas().getDadosDasPizzas();
		
		if(dadosDeTodasPizzas.size() != 0) {
			
			modelo.addColumn("ID");
			modelo.addColumn("Sabor");
			modelo.addColumn("Preço por fatia");
			modelo.addColumn("Fatias vendidas");
			modelo.addColumn("Modo de preparo");
			
//			escondendo a coluda ID e modo de preparo
			tabela.getColumnModel().getColumn(0).setMinWidth(0);
			tabela.getColumnModel().getColumn(0).setMaxWidth(0);

			tabela.getColumnModel().getColumn(4).setMinWidth(0);
			tabela.getColumnModel().getColumn(4).setMaxWidth(0);

			
			
			for (String[] dadosDaPizza : dadosDeTodasPizzas) {
				Object[] linha = {dadosDaPizza[3], dadosDaPizza[0], dadosDaPizza[1], dadosDaPizza[2],dadosDaPizza[4]};
				modelo.addRow(linha);
			}
			
			tabela.addRowSelectionInterval(0, 0);		
		}else {
			modelo.addColumn("Não há pizzas");
		}
		
		JScrollPane scroll = new JScrollPane(tabela);
		scroll.setBounds(0, 0, 620, 160);
		painelPizzas.add(scroll);

	}
	
	private class OuvinteBotoesTelaDeSaboresDePizza implements ActionListener{
		
		private TelaDePizzas telaDeSaboresDePizza;

		public OuvinteBotoesTelaDeSaboresDePizza(TelaDePizzas telaDeSaboresDePizza) {
			this.telaDeSaboresDePizza = telaDeSaboresDePizza;
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			String botao = e.getActionCommand();

			
			int linhaSelecionada = tabela.getSelectedRow();

			GerenteController gerenteController = new GerenteController();
			
			if(botao.equals("Editar")) {

				if(linhaSelecionada != -1) {
					
//					JOptionPane.showMessageDialog(null, "Lógica ainda não implementada");
					
					TelaDeEdicaoDePizza telaDeEdicaoDePizza = new TelaDeEdicaoDePizza(codigoDoGerenteLogado, Integer.parseInt((String) tabela.getValueAt(linhaSelecionada, 0)), (String) tabela.getValueAt(linhaSelecionada, 1), Float.parseFloat((String) tabela.getValueAt(linhaSelecionada, 2)), (String) tabela.getValueAt(linhaSelecionada, 4));
					telaDeEdicaoDePizza.setLocationRelativeTo(telaDeSaboresDePizza);
					telaDeSaboresDePizza.dispose();
					
					
					
				}else {
					JOptionPane.showMessageDialog(telaDeSaboresDePizza, "Selecione uma pizza!", "Nenhuma pizza selecionada", JOptionPane.ERROR_MESSAGE);
				}
				
			}else if(botao.equals("Adicionar Pizza")) {
				TelaDeCriacaoDePizza telaDeCriacaoDePizza = new TelaDeCriacaoDePizza(codigoDoGerenteLogado);
				telaDeCriacaoDePizza.setLocationRelativeTo(telaDeSaboresDePizza);
				telaDeSaboresDePizza.dispose();
				
			}else if(botao.equals("Remover Pizza")) {
				
				if(linhaSelecionada != -1) {
					int opcao = JOptionPane.showConfirmDialog(telaDeSaboresDePizza, "Você tem certeza que quer excluir essa pizza?","Excluir Pizza",JOptionPane.YES_NO_OPTION);
					
					if(opcao == JOptionPane.YES_OPTION) {
						int IdDaPizzaSelecionada = Integer.parseInt((String) tabela.getValueAt(linhaSelecionada, 0));
						
						PizzaDTO pizzaDTO = new PizzaDTO();
						
						pizzaDTO.setIdDaPizza(IdDaPizzaSelecionada);
						
						gerenteController.removerPizza(pizzaDTO);

						JOptionPane.showMessageDialog(telaDeSaboresDePizza, "Pizza removida!");
						
						modelo.removeRow(linhaSelecionada);
					}
					
				}else {
					JOptionPane.showMessageDialog(telaDeSaboresDePizza, "Selecione uma pizza!", "Nenhuma pizza selecionada", JOptionPane.ERROR_MESSAGE);
				}
			}else if(botao.equals("Ver Ingredientes")) {
				
				int IdDaPizzaSelecionada = Integer.parseInt((String) tabela.getValueAt(linhaSelecionada, 0));
				String saborDaPizzaSelecionada = (String) tabela.getValueAt(linhaSelecionada, 1);

				TelaDeIngredientesDeUmaPizza telaDeIngredientesDeUmaPizza = new TelaDeIngredientesDeUmaPizza(IdDaPizzaSelecionada, saborDaPizzaSelecionada);
				telaDeIngredientesDeUmaPizza.setLocationRelativeTo(telaDeSaboresDePizza);
				
				
			}
		}
	}


	
}