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
import DTO.ClienteDTO;
import DTO.FuncionarioDTO;
import View_Ouvintes.OuvinteQueLevaParaTelaDoGerente;
import View_Utilidades.AdicionadorDeComponentes;
import View_Utilidades.Icones;
import View_Utilidades.Iterator;
import View_Utilidades.IteratorConcreto;

public class TelaDeClientes extends TelaComMenu {

	private JTable tabela;
	
	private JTabbedPane aba;
	private JPanel painelClientes;
	
	private int codigoDoGerenteLogado;
	public TelaDeClientes(int codigoDoGerenteLogado) {
		super("Clientes");
		
		this.codigoDoGerenteLogado = codigoDoGerenteLogado;


		adicionarLabels();
		aba = new JTabbedPane();
		adicionarAba();
		this.setVisible(true);
	}

	

//	@Override
//	public void adicionarComponentesGraficos() {
//		adicionarLabels();
//		aba = new JTabbedPane();
//		adicionarAba();
//	}

	private void adicionarLabels() {
		
		FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
		funcionarioDTO.setCodigo(codigoDoGerenteLogado);;
		AdicionadorDeComponentes.adicionarIdentificacao(this, funcionarioDTO);

		
		JLabel L = AdicionadorDeComponentes.adicionarJLabel(this, "CLIENTES", 200, 40, 450, 60);
		L.setFont(new Font("Times new Roman", Font.BOLD, 75));
		L.setForeground(Color.DARK_GRAY);
//		
//		FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
//		funcionarioDTO.setCodigo(codigoDoGerenteLogado);;
//		AdicionadorDeComponentes.adicionarIdentificacao(this, funcionarioDTO);
//			
		
	}

	private void adicionarAba() {
		adicionarTabela();
		adicionarBotoes();
		
//		\/ linha responsável por adicionar a aba "dados dos clientes"
		aba.addTab("Dados dos clientes", painelClientes);
		aba.setBounds(80, 115, 625, 240);
		add(aba);
		
	}
	
	private DefaultTableModel modelo;
	
	private void adicionarTabela() {
		
		GerenteController gerenteController = new GerenteController();
		
		painelClientes = new JPanel(null);
		
		modelo = new DefaultTableModel();
		
		tabela = new JTable(modelo);
		
		Iterator clientes = new IteratorConcreto(gerenteController.recuperarDadosDeTodosOsClientes().getDadosDosClientes());
		
		modelo.addColumn("Nome");
		modelo.addColumn("CPF");
		modelo.addColumn("Telefone");
		modelo.addColumn("Endereço (B,R,N)");
		
		while(clientes.hasNext()) {
			
			ClienteDTO cli = (ClienteDTO) clientes.next();

			Object[] linha = {cli.getNome(), cli.getCPF(), cli.getTelefone(), cli.getEndereço()};
			modelo.addRow(linha);
			
			tabela.addRowSelectionInterval(0, 0);		
			
		}
		
		
		JScrollPane scroll = new JScrollPane(tabela);
		scroll.setBounds(0, 0, 620, 165);
		painelClientes.add(scroll);
		
		
//		ArrayList<String[]> clientes = gerenteController.recuperarDadosDeTodosOsClientes().getDadosDosClientes();
		
//		if(clientes.size() != 0) {
//			modelo.addColumn("Nome");
//			modelo.addColumn("CPF");
//			modelo.addColumn("Telefone");
//			modelo.addColumn("Endereço (B,R,N)");
//			
//			for (String[] dadosDoCliente : clientes) {
//				Object[] linha = {dadosDoCliente[0], dadosDoCliente[1], dadosDoCliente[2], dadosDoCliente[3]};
//				modelo.addRow(linha);
//			}
//			
//			tabela.addRowSelectionInterval(0, 0);		
//		}else {
//			modelo.addColumn("Não há clientes");
//		}
//
//		
//		JScrollPane scroll = new JScrollPane(tabela);
//		scroll.setBounds(0, 0, 620, 165);
//		painelClientes.add(scroll);
		
	}
	
	private void adicionarBotoes() {
		
		JButton botaoVoltar = AdicionadorDeComponentes.adicionarJButton(painelClientes, "Voltar", 0, 180, 90, 20);
		botaoVoltar.setIcon(Icones.ICONE_VOLTAR);
		botaoVoltar.addActionListener(new OuvinteQueLevaParaTelaDoGerente(this, codigoDoGerenteLogado));
		
		Ouvint ouvint = new Ouvint(this);

		JButton botaoExcluir = AdicionadorDeComponentes.adicionarJButton(painelClientes, "Excluir", 210, 180, 95, 20);
		botaoExcluir.setIcon(Icones.ICONE_LIXO);
		botaoExcluir.addActionListener(ouvint);
	
		JButton botaoHistoricoDePedidos = AdicionadorDeComponentes.adicionarJButton(painelClientes, "Histórico de Pedidos", 438, 180, 175, 20);
		botaoHistoricoDePedidos.setIcon(Icones.ICONE_HISTORICO);
		botaoHistoricoDePedidos.addActionListener(ouvint);
				
	}
	
	private class Ouvint implements ActionListener{

		private TelaDeClientes telaDeClientes;
		
		public Ouvint(TelaDeClientes telaDeClientes) {
			this.telaDeClientes = telaDeClientes;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {

			String botao = e.getActionCommand();

			int linha = tabela.getSelectedRow();
			
			if(botao.equals("Excluir")){
				
				if(linha != -1) {
					String cpf = (String)tabela.getValueAt(linha, 1);
					
					GerenteController gerenteController = new GerenteController();

					int opcao = JOptionPane.showConfirmDialog(telaDeClientes, "Você tem certeza que quer excluir esse cliente?","Excluir Cliente",JOptionPane.YES_NO_OPTION);
					
					if(opcao == JOptionPane.YES_OPTION) {
						
						ClienteDTO clienteDTO = new ClienteDTO();
						clienteDTO.setCPF(cpf);
						gerenteController.excluirCliente(clienteDTO);
						
						JOptionPane.showMessageDialog(telaDeClientes, "Cliente excluído!");
						
						modelo.removeRow(linha);
					}
					
				}else {
					JOptionPane.showMessageDialog(telaDeClientes, "Selecione um cliente!", "Nenhum cliente selecionado", JOptionPane.ERROR_MESSAGE);
				}
					
			}else if(botao.equals("Histórico de Pedidos")) {

				if(linha != -1) {
					String nomeDoCliente = (String) tabela.getValueAt(linha, 0);
					String cpfDoCliente = (String) tabela.getValueAt(linha, 1);
					
					TelaDeHistoricoDePedidos telaDeHistoricoDePedidos = new TelaDeHistoricoDePedidos(codigoDoGerenteLogado, nomeDoCliente, cpfDoCliente);
					telaDeHistoricoDePedidos.setLocationRelativeTo(telaDeClientes);
					telaDeClientes.dispose();
				}else {
					JOptionPane.showMessageDialog(telaDeClientes, "Selecione um cliente!", "Nenhum cliente selecionado", JOptionPane.ERROR_MESSAGE);
				}
				
			}
			
		}
		
	}

	
}


