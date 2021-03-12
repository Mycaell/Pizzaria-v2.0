package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class TelaDeFuncionarios extends TelaComMenu{

	private DefaultTableModel modelo;
	private JTable tabela;

	private int codigoDoGerenteLogado;
	
	
	private JTabbedPane aba;
	private JPanel painelFuncionarios;
	
	public TelaDeFuncionarios(int codigoDoGerenteLogado) {
		super("Funcionários");
//		this.setSize(655,300);
		
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
//				
//	}
	
	
	private void adicionarLabels() {

		
		FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
		funcionarioDTO.setCodigo(codigoDoGerenteLogado);
		AdicionadorDeComponentes.adicionarIdentificacao(this, funcionarioDTO);

		
//		JLabel label = new JLabel(Icones.FUNDO_FUNCIONARIOS);
//		label.setBounds(0, 0, 800, 400);
//		setContentPane(label);
		JLabel L = AdicionadorDeComponentes.adicionarJLabel(this, "FUNCIONÁRIOS", 140, 25, 590, 90);
		L.setFont(new Font("Times new Roman", Font.BOLD, 65));
		L.setForeground(Color.DARK_GRAY);
		
//		
//		FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
//		funcionarioDTO.setCodigo(codigoDoGerenteLogado);
////		AdicionadorDeComponentes.adicionarJLabel(this, "<html><u>"+gerenteController.recuperarCargo(funcionarioDTO).getCargo()+" ("+gerenteController.recuperarNome(funcionarioDTO).getNome()+" : "+codigoDoGerenteLogado+")</u></html>", 5, 5, 655, 20);
//		AdicionadorDeComponentes.adicionarIdentificacao(this, funcionarioDTO);
		
	}

	private void adicionarAba() {

		adicionarTabela();
		adicionarBotoes();
		
		aba.addTab("Dados dos funcionários", painelFuncionarios);
		
		aba.setBounds(80, 115, 625, 240);
		add(aba);
	}
	
	
	private void adicionarTabela() {

//		JLabel L = AdicionadorDeComponentes.adicionarJLabel(this, "Funcionários", 250, 20, 350, 30);
//		L.setFont(new Font("Comic Sans", Font.BOLD, 18));
//		
		GerenteController gerenteController = new GerenteController();
	
		
		painelFuncionarios = new JPanel(null);

		modelo = new DefaultTableModel();
		
		tabela = new JTable(modelo);
		
		modelo.addColumn("Nome");
		modelo.addColumn("Código de Identificação");
		modelo.addColumn("Cargo");
		modelo.addColumn("Telefone");
		
		
		
		for (String[] dadosDoFuncionario : gerenteController.recuperarDadosDeTodosFuncionarios().getDadosDosFuncionarios()) {
			Object[] linha = {dadosDoFuncionario[0], dadosDoFuncionario[1], dadosDoFuncionario[2], dadosDoFuncionario[3]};
			modelo.addRow(linha);
		}
		
		tabela.addRowSelectionInterval(0, 0);		

		JScrollPane scroll = new JScrollPane(tabela);
		scroll.setBounds(0, 0, 620, 165);
//		add(scroll);
		painelFuncionarios.add(scroll);
	}

	private void adicionarBotoes() {

		JButton botaoVoltar = AdicionadorDeComponentes.adicionarJButton(painelFuncionarios, "Voltar", 0, 180, 90, 20);
		botaoVoltar.setIcon(Icones.ICONE_VOLTAR);
		botaoVoltar.addActionListener(new OuvinteQueLevaParaTelaDoGerente(this, codigoDoGerenteLogado));
		
		OuvinteTelaDeFuncionarios ouvint = new OuvinteTelaDeFuncionarios(this);

		JButton botaoExcluir = AdicionadorDeComponentes.adicionarJButton(painelFuncionarios, "Excluir", 140, 180, 95, 20);
		botaoExcluir.setIcon(Icones.ICONE_LIXO);
		botaoExcluir.addActionListener(ouvint);
		

		JButton botaoDetalhes = AdicionadorDeComponentes.adicionarJButton(painelFuncionarios, "Detalhes", 285, 180, 115, 20);
		botaoDetalhes.setIcon(Icones.ICONE_DETALHES);
		botaoDetalhes.addActionListener(ouvint);

		
		
		JButton botaoAdicionarFuncionario = AdicionadorDeComponentes.adicionarJButton(painelFuncionarios, "Cadastrar Funcionário", 434, 180, 180, 20);
		botaoAdicionarFuncionario.setIcon(Icones.ICONE_USUARIO);
		botaoAdicionarFuncionario.addActionListener(ouvint);

		
	}

	private class OuvinteTelaDeFuncionarios implements ActionListener{

		private TelaDeFuncionarios telaDeFuncionarios;
		
		public OuvinteTelaDeFuncionarios(TelaDeFuncionarios telaDeFuncionarios) {
			this.telaDeFuncionarios = telaDeFuncionarios;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {

			String botao = e.getActionCommand();
			
			int linha = tabela.getSelectedRow();

			if (botao.equals("Excluir")){
				
				if(linha != -1) {
					
					String codigoString = (String) tabela.getValueAt(linha, 1);
					
					int codigo = Integer.parseInt(codigoString);
					
					
					GerenteController gerenteController = new GerenteController();
					
					FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
					funcionarioDTO.setCodigo(codigo);
					
					if(codigoDoGerenteLogado == codigo) {
						int opcao = JOptionPane.showConfirmDialog(telaDeFuncionarios, "Você concorda em excluir sua própria conta?","Excluir Conta",JOptionPane.YES_NO_OPTION);
					
						if(opcao == JOptionPane.YES_OPTION) {
							
							gerenteController.excluirFuncionario(funcionarioDTO);
							
							JOptionPane.showMessageDialog(telaDeFuncionarios, "Conta excluída!");
							
							TelaDeLogin telaDeLogin = new TelaDeLogin();
							telaDeLogin.setLocationRelativeTo(telaDeFuncionarios);
							telaDeFuncionarios.dispose();
						}
						
					}else {
						int opcao = JOptionPane.showConfirmDialog(telaDeFuncionarios, "Tem certeza que quer excluir esse funcionário?","Excluir Funionário",JOptionPane.YES_NO_OPTION);
						
						if(opcao == JOptionPane.YES_OPTION) {
							gerenteController.excluirFuncionario(funcionarioDTO);
							
							JOptionPane.showMessageDialog(telaDeFuncionarios, "Funcionário excluído!");
							
							modelo.removeRow(linha);
						}	
					}					
				}else {
					JOptionPane.showMessageDialog(telaDeFuncionarios, "Selecione um funcionário!", "Nenhum funcionário selecionado", JOptionPane.ERROR_MESSAGE);
				}
				
			}else if(botao.equals("Detalhes")){
				int codigo = Integer.parseInt((String)tabela.getValueAt(linha, 1));
				TelaDeDetalhesDeFuncionario telaDeDetalhesDeFuncionario = new TelaDeDetalhesDeFuncionario(codigoDoGerenteLogado, codigo);
				telaDeDetalhesDeFuncionario.setLocationRelativeTo(telaDeFuncionarios);
				telaDeFuncionarios.dispose();
			}else {
//				lógica que será executada quando o botão "cadastrar funcionários for clicado
				TelaDeCadastro telaDeCadastro = new TelaDeCadastro(codigoDoGerenteLogado);
				telaDeCadastro.setLocationRelativeTo(telaDeFuncionarios);
				telaDeFuncionarios.dispose();
				
			}
			
			
		}
		
	}


	

}
