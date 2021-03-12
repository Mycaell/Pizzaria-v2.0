package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controller.GerenteController;
import Controller.PizzaioloController;
import DTO.FuncionarioDTO;
import DTO.PedidoDTO;
import DTO.PizzaDTO;
import View_Ouvintes.OuvinteQueLevaParaTelaDoGerente;
import View_Utilidades.AdicionadorDeComponentes;
import View_Utilidades.Icones;

public class TelaDePizzaiolo extends TelaComMenu{

	private JTable tabelaPedidos;
	private DefaultTableModel modeloPedidos;
	
	private JTable tabelaPizzas;

	
	private JTabbedPane abas;
	
	private JPanel painelPedidos;
	private JPanel painelPizzas;
	
	
	private int codigoDoPizzaioloLogado;
	
	public TelaDePizzaiolo(int codigoDoPizzaioloLogado) {
		super("Pizzaiolo");

		
		this.codigoDoPizzaioloLogado = codigoDoPizzaioloLogado;

		adicionarLabels();
		
		abas = new JTabbedPane();
		adicionarAba();


		
		this.setVisible(true);
	}

	
	
//	@Override
//	public void adicionarComponentesGraficos() {
//		adicionarLabels();
//		
//		abas = new JTabbedPane();
//		adicionarAba();
//	}
	
	
	private void adicionarLabels() {
		FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
		funcionarioDTO.setCodigo(codigoDoPizzaioloLogado);
		AdicionadorDeComponentes.adicionarIdentificacao(this, funcionarioDTO);
		
		
		JLabel L = AdicionadorDeComponentes.adicionarJLabel(this, "COZINHA", 220, 40, 450, 60);
		L.setFont(new Font("Times new Roman", Font.BOLD, 75));
		L.setForeground(Color.DARK_GRAY);
//		
//		FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
//		funcionarioDTO.setCodigo(codigoDoPizzaioloLogado);
//		AdicionadorDeComponentes.adicionarIdentificacao(this, funcionarioDTO);
		
	}


	private void adicionarAba() {
		
		adicionarComponentesAbaPedidos();
		adicionarComponentesAbaDePizzas();
		adicionarBotoes();
		
		
		abas.setBounds(80, 115, 625, 240);
		add(abas);
	}
	
	private void adicionarBotoes() {

		OuvinteTelaDePizzaiolo ouvinte = new OuvinteTelaDePizzaiolo(this);
		
		JButton botaoModoDePreparo = new JButton("Modo de Preparo");
		botaoModoDePreparo.setBounds(215, 180, 200, 20);
		botaoModoDePreparo.setIcon(Icones.ICONE_OLHO_ABERTO);
		botaoModoDePreparo.addActionListener(ouvinte);
		painelPizzas.add(botaoModoDePreparo);
		

		
		JButton botaoConcluirPedido = new JButton("Concluir Pedido");
		botaoConcluirPedido.setBounds(470, 180, 145, 20);
//		JButton botaoConcluirPedido = AdicionadorDeComponentes.adicionarJButton(this, "Concluir Pedido", 490, 210, 145, 20);
		botaoConcluirPedido.setIcon(Icones.ICONE_CERTO);
		botaoConcluirPedido.addActionListener(ouvinte);

		painelPedidos.add(botaoConcluirPedido);
//		painelPedidos.add(scroll);
		
		
		FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
		funcionarioDTO.setCodigo(codigoDoPizzaioloLogado);

		if(new GerenteController().recuperarCargo(funcionarioDTO).getCargo().equals("Gerente")) {
			
			JButton botaoVoltar = new JButton("Voltar");
			botaoVoltar.setBounds(0, 180, 90, 20);
			botaoVoltar.setIcon(Icones.ICONE_VOLTAR);
			botaoVoltar.addActionListener(new OuvinteQueLevaParaTelaDoGerente(this, codigoDoPizzaioloLogado));
			
			painelPedidos.add(botaoVoltar);
		}
	
		
		
	}


	private void adicionarComponentesAbaDePizzas() {

		DefaultTableModel modeloPizzas = new DefaultTableModel();
		tabelaPizzas = new JTable(modeloPizzas);
		
		painelPizzas = new JPanel(null);
		
		

		GerenteController gerenteController = new GerenteController();
		
		ArrayList<String[]> dadosDeTodasPizzas = gerenteController.recuperarDadosDeTodasPizzas().getDadosDasPizzas();
		
		if(dadosDeTodasPizzas.size() != 0) {
			
			modeloPizzas.addColumn("Sabor");
			modeloPizzas.addColumn("Modo de Preparo");
			
//			escondendo a coluda ID e modo de preparo
			tabelaPizzas.getColumnModel().getColumn(1).setMinWidth(0);
			tabelaPizzas.getColumnModel().getColumn(1).setMaxWidth(0);

			
			
			for (String[] dadosDaPizza : dadosDeTodasPizzas) {
				Object[] linha = {dadosDaPizza[0], dadosDaPizza[4]};
				modeloPizzas.addRow(linha);
			}
			
			tabelaPizzas.addRowSelectionInterval(0, 0);		
		}else {
			modeloPizzas.addColumn("Não há pizzas");
		}
		
		JScrollPane scroll = new JScrollPane(tabelaPizzas);
		scroll.setBounds(0, 0, 620, 165);
//		add(scroll);
		painelPizzas.add(scroll);
		
		
		abas.addTab("Pizzas", painelPizzas);
		
	}

	private void adicionarComponentesAbaPedidos() {
		
		painelPedidos = new JPanel(null);
		

		
		modeloPedidos = new DefaultTableModel();
		tabelaPedidos = new JTable(modeloPedidos);
	
		GerenteController gerenteController = new GerenteController();

		PizzaioloController pizzaioloController = new PizzaioloController();

		
		ArrayList<String[]> pedidosProntosParaPreparo = pizzaioloController.recuperarDadosDeTodosPedidosProntos().getDadosDosPedidosProntosParaPreparo();
		
		if(pedidosProntosParaPreparo.size() == 0) {
			modeloPedidos.addColumn("Não há pedidos");
		}else {
			modeloPedidos.addColumn("Nº do pedido");
			modeloPedidos.addColumn("Tamanho");
			modeloPedidos.addColumn("Fatias");
			modeloPedidos.addColumn("Sabores");
			

			for (String[] dadosDoPedido : pedidosProntosParaPreparo) {
				
				PizzaDTO pizzaDTO = new PizzaDTO();
				pizzaDTO.setIds(dadosDoPedido[3]);
				String sabores = gerenteController.getSabores(pizzaDTO).getSabores();
				
				
				Object[] linha = {dadosDoPedido[0], dadosDoPedido[1], dadosDoPedido[2], sabores};
				modeloPedidos.addRow(linha);
			}
			
			tabelaPedidos.addRowSelectionInterval(0, 0);		
			
		}
		
		JScrollPane scroll = new JScrollPane(tabelaPedidos);
		scroll.setBounds(0, 0, 620, 165);
		painelPedidos.add(scroll);
		
		abas.addTab("Pedidos", painelPedidos);
		
	}
	
	private class OuvinteTelaDePizzaiolo implements ActionListener{

		private TelaDePizzaiolo telaDePizzaiolo;
		
		public OuvinteTelaDePizzaiolo(TelaDePizzaiolo telaDePizzaiolo) {
			this.telaDePizzaiolo = telaDePizzaiolo;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {

			String botao = e.getActionCommand();
			
			PizzaioloController pizzaioloController = new PizzaioloController();

			if(botao.equals("Concluir Pedido")) {
				int linha = tabelaPedidos.getSelectedRow();
				
				if(linha != -1) {
					
					int opcao = JOptionPane.showConfirmDialog(telaDePizzaiolo, "Você quer mesmo concluir o pedido?","Concluir pedido",JOptionPane.YES_NO_OPTION);
					
					if(opcao == JOptionPane.YES_OPTION) {
						String numeroDoPedidoString = (String) tabelaPedidos.getValueAt(linha, 0);
						
						PedidoDTO pedidoDTO = new PedidoDTO();
						pedidoDTO.setNumeroDoPedido(Integer.parseInt(numeroDoPedidoString));
						pedidoDTO.setCodigoDoPizzaiolo(codigoDoPizzaioloLogado);
						
						pizzaioloController.concluirPedido(pedidoDTO);
						modeloPedidos.removeRow(linha);
						
						JOptionPane.showMessageDialog(telaDePizzaiolo, "Pedido concluído!");
						
					}
				}else {
					JOptionPane.showMessageDialog(telaDePizzaiolo, "Selecione um pedido!", "Nenhum pedido selecionado", JOptionPane.ERROR_MESSAGE);	
				}
			}else if(botao.equals("Modo de Preparo")) {
				int linhaSelecionada = tabelaPizzas.getSelectedRow();

				String saborDaPizzaSelecionada = (String) tabelaPizzas.getValueAt(linhaSelecionada, 0);
				String modoDePreparoDaPizzaSelecionada = (String) tabelaPizzas.getValueAt(linhaSelecionada, 1);

				TelaDeModoDePreparo telaDeModoDePreparo = new TelaDeModoDePreparo(saborDaPizzaSelecionada, modoDePreparoDaPizzaSelecionada);
				telaDeModoDePreparo.setLocationRelativeTo(telaDePizzaiolo);
				
			}
		}
	}


	
}
