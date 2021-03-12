package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controller.GerenteController;
import DTO.ClienteDTO;
import DTO.FuncionarioDTO;
import DTO.PizzaDTO;
import View_Utilidades.AdicionadorDeComponentes;
import View_Utilidades.Icones;

public class TelaDeHistoricoDePedidos extends TelaComMenu {

	
	private int codigoDoGerenteLogado;
	private String nomeDoCliente;
	private String cpfDoCliente;
	
	
	private JTabbedPane aba;
	private JPanel painelPedidos;
	
	
//	estou recebendo o nome do cliente pois eu já o tenho na tela tela anterior assim eu não preciso recupera-lo atravez do cpf
	public TelaDeHistoricoDePedidos(int codigoDoGerenteLogado, String nomeDoCliente, String cpfDoCliente) {
		super("Histórico de Pedidos");
		this.setSize(1000, 440);
		

		this.codigoDoGerenteLogado = codigoDoGerenteLogado;
		this.nomeDoCliente = nomeDoCliente;
		this.cpfDoCliente = cpfDoCliente;
		

		aba = new JTabbedPane();
		
		adicionarAba();	
		
		this.setVisible(true);
	}

	
	
//	@Override
//	public void adicionarComponentesGraficos() {
//		aba = new JTabbedPane();
//		
//		adicionarAba();		
//	}
	
	private void adicionarAba() {
		adicionarLabels();
		adicionarTabela();
		adicionarBotoes();
		
		
		aba.setBounds(15, 115, 962, 260);
		add(aba);
	}
	
	private void adicionarBotoes() {
		JButton botaoVoltar = AdicionadorDeComponentes.adicionarJButton(painelPedidos, "voltar", 0, 205, 91, 20);
		botaoVoltar.setIcon(Icones.ICONE_VOLTAR);
		botaoVoltar.addActionListener(new Ouvinte(this));
	}


	private void adicionarLabels() {

		
		
		JLabel label = new JLabel(Icones.FUNDO_HISTORICO_PEDIDOS);
		label.setBounds(0, 0, 990, 600);
		setContentPane(label);

		FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
		funcionarioDTO.setCodigo(codigoDoGerenteLogado);
		AdicionadorDeComponentes.adicionarIdentificacao(this, funcionarioDTO);

		JLabel L = AdicionadorDeComponentes.adicionarJLabel(this, "PEDIDOS de "+nomeDoCliente.toUpperCase(), 80, 40, 900, 60);
		L.setFont(new Font("Times new Roman", Font.BOLD, 75));
		L.setForeground(Color.DARK_GRAY);
		
		
//		FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
//		funcionarioDTO.setCodigo(codigoDoGerenteLogado);
//		AdicionadorDeComponentes.adicionarIdentificacao(this, funcionarioDTO);
		
	}


	public void adicionarTabela() {
		
	
		GerenteController gerenteController = new GerenteController();
	

		painelPedidos = new JPanel(null);
		
		DefaultTableModel modelo = new DefaultTableModel();
		
		JTable tabela = new JTable(modelo);
		
		ClienteDTO clienteDTO = new ClienteDTO();
		clienteDTO.setCPF(cpfDoCliente);
		
		ArrayList<String[]> dadosDeTodosPedidosDeUmCliente = gerenteController.recuperarDadosDeTodosPedidosDeUmCliente(clienteDTO).getDadosDosPedidosDeUmUnicoCliente();
		
		if(dadosDeTodosPedidosDeUmCliente.size() == 0) {
			modelo.addColumn("Não há pedidos");
		}else {
			modelo.addColumn("Nº do pedido");
			modelo.addColumn("CPF do cliente");
			modelo.addColumn("Tamanho");
			modelo.addColumn("Fatias");
			modelo.addColumn("Sabores");
			modelo.addColumn("Preço");
			modelo.addColumn("Endereço (B,R,N)");
			modelo.addColumn("Data do Pedido");
			modelo.addColumn("Data de Preparo");
			modelo.addColumn("Data de Entrega");


			tabela.getColumnModel().getColumn(0).setPreferredWidth(55);
			tabela.getColumnModel().getColumn(2).setPreferredWidth(40);
			tabela.getColumnModel().getColumn(3).setPreferredWidth(40);
			
			for (int i = dadosDeTodosPedidosDeUmCliente.size()-1; i >= 0; i--) {
				
				String[] pedido = dadosDeTodosPedidosDeUmCliente.get(i);

				PizzaDTO pizzaDTO = new PizzaDTO();
				pizzaDTO.setIds(pedido[4]);
				String sabores = gerenteController.getSabores(pizzaDTO).getSabores();
				
				
				
				Object[] linha = {pedido[0], pedido[1], pedido[2], pedido[3], sabores, pedido[5], pedido[6], pedido[7], pedido[8], pedido[9]};
				modelo.addRow(linha);
			}
		}
		
		JScrollPane scroll = new JScrollPane(tabela);
		scroll.setBounds(0, 0, 959, 200);
		painelPedidos.add(scroll);
		
	
		aba.addTab("Pedidos", painelPedidos);
	}
	
	
	
	private class Ouvinte implements ActionListener{

		private TelaDeHistoricoDePedidos telaDeHistoricoDePedidos;
		
		public Ouvinte(TelaDeHistoricoDePedidos telaDeHistoricoDePedidos) {
			this.telaDeHistoricoDePedidos = telaDeHistoricoDePedidos;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {

			TelaDeClientes telaDeClientes = new TelaDeClientes(codigoDoGerenteLogado);
			telaDeClientes.setLocationRelativeTo(telaDeHistoricoDePedidos);
			telaDeHistoricoDePedidos.dispose();
			
		}
		
	}




	
	
	
	
}
