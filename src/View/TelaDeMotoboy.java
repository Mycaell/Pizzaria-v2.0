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
import Controller.MotoboyController;
import DTO.FuncionarioDTO;
import DTO.PedidoDTO;
import DTO.PizzaDTO;
import View_Ouvintes.OuvinteQueLevaParaTelaDoGerente;
import View_Utilidades.AdicionadorDeComponentes;
import View_Utilidades.Icones;

public class TelaDeMotoboy extends TelaComMenu {

	
	
	private JTable tabela;
	private DefaultTableModel modelo;
	
	private int codigoDoMotoboyLogado;

	private JTabbedPane aba;
	private JPanel painelPedidos;
	
	public TelaDeMotoboy(int codigoDoMotoboyLogado) {
		super("Motoboy");

	
		this.codigoDoMotoboyLogado = codigoDoMotoboyLogado;

		
		adicionarLabels();
		
		aba = new JTabbedPane();
		
		adicionarAba();		

		this.setVisible(true);
	}
	
	

//	@Override
//	public void adicionarComponentesGraficos() {
//		adicionarLabels();
//		
//		aba = new JTabbedPane();
//		
//		adicionarAba();		
//	}
	
	private void adicionarLabels() {
		

		FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
		funcionarioDTO.setCodigo(codigoDoMotoboyLogado);
		AdicionadorDeComponentes.adicionarIdentificacao(this, funcionarioDTO);
		
		JLabel L = AdicionadorDeComponentes.adicionarJLabel(this, "A ENTREGAR", 150, 40, 500, 60);
		L.setFont(new Font("Times new Roman", Font.BOLD, 75));
		L.setForeground(Color.DARK_GRAY);
		
//		
//		FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
//		funcionarioDTO.setCodigo(codigoDoMotoboyLogado);
//		AdicionadorDeComponentes.adicionarIdentificacao(this, funcionarioDTO);
	}
	
	private void adicionarAba() {
		adicionarComponentes();

		adicionarBotoes();
		
		

		aba.setBounds(80, 115, 625, 240);
		add(aba);
		
	}
	
	private void adicionarBotoes() {

		FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
		funcionarioDTO.setCodigo(codigoDoMotoboyLogado);
		
		if(new GerenteController().recuperarCargo(funcionarioDTO).getCargo().equals("Gerente")) {
			JButton botaoVoltar = AdicionadorDeComponentes.adicionarJButton(painelPedidos, "Voltar", 0, 180, 90, 20);
			botaoVoltar.setIcon(Icones.ICONE_VOLTAR);
			botaoVoltar.addActionListener(new OuvinteQueLevaParaTelaDoGerente(this, codigoDoMotoboyLogado));
		}
		
	}

	private void adicionarComponentes() {
	
		painelPedidos = new JPanel(null);
		
		GerenteController gerenteController = new GerenteController();
	
		
		modelo = new DefaultTableModel();
		tabela = new JTable(modelo);
	
		MotoboyController motoboyController = new MotoboyController();

		
		ArrayList<String[]> pedidosProntosParaEntrega = motoboyController.recuperarDadosDetodosPedidosProntosParaEntrega().getDadosDosPedidosProntosParaEntrega();
		
		if(pedidosProntosParaEntrega.size() == 0) {
			modelo.addColumn("Não há pedidos");
		}else {
			
			modelo.addColumn("Nº do pedido");
			modelo.addColumn("CPF do cliente");
			modelo.addColumn("Fatias");
			modelo.addColumn("Sabores");
			modelo.addColumn("Preço");
			modelo.addColumn("Endereço (B,R,N)");
			
			for (String[] dadosDoPedido : pedidosProntosParaEntrega) {
				
				PizzaDTO pizzaDTO = new PizzaDTO();
				pizzaDTO.setIds(dadosDoPedido[3]);
				String sabores = gerenteController.getSabores(pizzaDTO).getSabores();
				
				
				Object[] linha = {dadosDoPedido[0], dadosDoPedido[1], dadosDoPedido[2], sabores, dadosDoPedido[4], dadosDoPedido[5]};
				
				modelo.addRow(linha);
			}
			
			tabela.addRowSelectionInterval(0, 0);	
		}
		
		JScrollPane scroll = new JScrollPane(tabela);
		scroll.setBounds(0, 0, 620, 165);

		painelPedidos.add(scroll);
		
		JButton botaoRealizarEntrega = AdicionadorDeComponentes.adicionarJButton(painelPedidos, "Realizar Entrega", 464, 180, 150, 20);
		botaoRealizarEntrega.setIcon(Icones.ICONE_CERTO);
		botaoRealizarEntrega.addActionListener(new OuvinteTelaDeMotoboy(this));
		
		
		aba.addTab("Pedidos", painelPedidos);
		
	}
	
	private class OuvinteTelaDeMotoboy implements ActionListener{
		
		private TelaDeMotoboy telaDeMotoboy;
		
		public OuvinteTelaDeMotoboy(TelaDeMotoboy telaDeMotoboy) {
			this.telaDeMotoboy = telaDeMotoboy;
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			
			String botao = e.getActionCommand();

			if(botao.equals("Realizar Entrega")) {
				
				int linha = tabela.getSelectedRow();
				
				
				if(linha != -1) {
					
					int opcao = JOptionPane.showConfirmDialog(telaDeMotoboy, "Você quer mesmo realizar a entrega do pedido?","Realizar entrega",JOptionPane.YES_NO_OPTION);
					
					if(opcao == JOptionPane.YES_OPTION) {
						String numeroDoPedidoString = (String) tabela.getValueAt(linha, 0);
						
						int numeroDoPedido = Integer.parseInt(numeroDoPedidoString);
						
						MotoboyController motoboyController = new MotoboyController();
						
						PedidoDTO pedidoDTO = new PedidoDTO();
						pedidoDTO.setNumeroDoPedido(numeroDoPedido);
						pedidoDTO.setCodigoDoMotoboy(codigoDoMotoboyLogado);
						
//						alterações
						float precoPedido = Float.parseFloat((String) tabela.getValueAt(linha, 4));
						pedidoDTO.setPreco(precoPedido);
//						!!!!!!!!!!!!!!!!!!!!
						
						
						motoboyController.realizarEntregaDePedido(pedidoDTO);
						
						modelo.removeRow(linha);
						
						JOptionPane.showMessageDialog(telaDeMotoboy, "Pedido entregue!");
						
					}
					
					
				}else {
					JOptionPane.showMessageDialog(telaDeMotoboy, "Selecione um pedido!", "Nenhum pedido selecionado", JOptionPane.ERROR_MESSAGE);
				}
				
			}
			
			
			
		}
		
	}

	
	
}
