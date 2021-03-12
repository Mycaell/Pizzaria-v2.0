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
import DTO.FuncionarioDTO;
import View_Ouvintes.OuvinteQueLevaParaTelaDoGerente;
import View_Utilidades.AdicionadorDeComponentes;
import View_Utilidades.Icones;

public class TelaDeIngredientes extends TelaComMenu{

	private int codigoDoGerenteLogado;
	
	private JTable tabela;
	private DefaultTableModel modelo;
	
	private JTabbedPane aba;
	private JPanel painelIngredientes;
	
	public TelaDeIngredientes(int codigoDoGerenteLogado) {
		super("Ingredientes");

		this.codigoDoGerenteLogado = codigoDoGerenteLogado;
	
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
		funcionarioDTO.setCodigo(codigoDoGerenteLogado);
		AdicionadorDeComponentes.adicionarIdentificacao(this, funcionarioDTO);

		JLabel L = AdicionadorDeComponentes.adicionarJLabel(this, "INGREDIENTES", 150, 40, 590, 60);
		L.setFont(new Font("Times new Roman", Font.BOLD, 65));
		L.setForeground(Color.DARK_GRAY);
		
//		FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
//		funcionarioDTO.setCodigo(codigoDoGerenteLogado);
//		AdicionadorDeComponentes.adicionarIdentificacao(this, funcionarioDTO);
//		
	}



	private void adicionarAba() {
		adicionarTabela();
		adicionarBotoes();

		aba.add("Dados dos ingredientes", painelIngredientes);
		aba.setBounds(80, 110, 625, 245);
		add(aba);
		
	}
	
	private void adicionarBotoes() {

		JButton botaoVoltar = AdicionadorDeComponentes.adicionarJButton(painelIngredientes, "Voltar", 0, 185, 90, 20);
		botaoVoltar.setIcon(Icones.ICONE_VOLTAR);
		botaoVoltar.addActionListener(new OuvinteQueLevaParaTelaDoGerente(this, codigoDoGerenteLogado));
		
		OuvinteBotoesTelaDeIngredientes ouvinte = new OuvinteBotoesTelaDeIngredientes(this);

		JButton botaoEditar = AdicionadorDeComponentes.adicionarJButton(painelIngredientes, "Editar", 220, 185, 90, 20);
		botaoEditar.setIcon(Icones.ICONE_EDICAO);
		botaoEditar.addActionListener(ouvinte);
		
		JButton adicionarSabor = AdicionadorDeComponentes.adicionarJButton(painelIngredientes, "Adicionar", 470, 170, 149, 20);
		adicionarSabor.setIcon(Icones.ICONE_MAIS);
		adicionarSabor.addActionListener(ouvinte);
	
		JButton removerSabor = AdicionadorDeComponentes.adicionarJButton(painelIngredientes, "Remover", 470, 195, 150, 20);
		removerSabor.setIcon(Icones.ICONE_MENOS);
		removerSabor.addActionListener(ouvinte);
	}

	private void adicionarTabela() {
		

		GerenteController gerenteController = new GerenteController();
	
		
		modelo = new DefaultTableModel();		
		
		tabela = new JTable(modelo);

		painelIngredientes = new JPanel(null);
		
		ArrayList<String[]> ingredientes = gerenteController.recuperarIngredientes().getIngredientes();
		
		if(ingredientes.size() != 0) {

			modelo.addColumn("ID");
			modelo.addColumn("Nome");
			modelo.addColumn("Preço");

//			escondendo a coluda ID
			tabela.getColumnModel().getColumn(0).setMinWidth(0);
			tabela.getColumnModel().getColumn(0).setMaxWidth(0);

			for (String[] i : ingredientes) {
				Object[] linha = {i[0], i[1], i[2]};
				modelo.addRow(linha);
			}
			
			tabela.addRowSelectionInterval(0, 0);		
		}else {
			modelo.addColumn("Não há Ingredientes");
		}
		
		
		JScrollPane scroll = new JScrollPane(tabela);
		scroll.setBounds(0, 0, 620, 167);
		painelIngredientes.add(scroll);
		
	}

	
	private class OuvinteBotoesTelaDeIngredientes implements ActionListener{

		
		private TelaDeIngredientes telaDeIngredientes;
		
		public OuvinteBotoesTelaDeIngredientes(TelaDeIngredientes telaDeIngredientes) {
			this.telaDeIngredientes = telaDeIngredientes;
		}
		
		
		@Override
		public void actionPerformed(ActionEvent e) {

			String botao = e.getActionCommand();
			
			
			int linhaSelecionada = tabela.getSelectedRow();

			
			if(botao.equals("Editar")) {

				if(linhaSelecionada != -1) {
					JOptionPane.showMessageDialog(null, "Lógica ainda não implementada");
//					LÓGICA DE EDIÇÃO
					
				}else {
					JOptionPane.showMessageDialog(telaDeIngredientes, "Selecione um ingrediente!", "Nenhum ingrediente selecionado", JOptionPane.ERROR_MESSAGE);
				}
				
			}else if(botao.equals("Adicionar")) {
				TelaNovoIngrediente telaNovoIngrediente = new TelaNovoIngrediente(telaDeIngredientes, codigoDoGerenteLogado);
				telaNovoIngrediente.setLocationRelativeTo(telaDeIngredientes);
				
			}else if(botao.equals("Remover")) {
				
				if(linhaSelecionada != -1) {
					int opcao = JOptionPane.showConfirmDialog(telaDeIngredientes, "Você tem certeza que quer excluir esse ingrediente?","Excluir ingrediente",JOptionPane.YES_NO_OPTION);
					
					if(opcao == JOptionPane.YES_OPTION) {
//						int IdDoIngredienteSelecionado = Integer.parseInt((String) tabela.getValueAt(linhaSelecionada, 0));
						
						JOptionPane.showMessageDialog(null, "Lógica ainda não implementada");
//						LÓGICA DE REMOÇÃO
						
//						JOptionPane.showMessageDialog(telaDeIngredientes, "Ingrediente removido!");
						
//						modelo.removeRow(linhaSelecionada);
					}
					
				}else {
					JOptionPane.showMessageDialog(telaDeIngredientes, "Selecione um ingrediente!", "Nenhum ingrediente selecionado", JOptionPane.ERROR_MESSAGE);
				}
			}			
		}
	}



	
	
}
