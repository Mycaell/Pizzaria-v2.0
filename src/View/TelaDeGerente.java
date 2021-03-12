package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import DTO.FuncionarioDTO;
import View_Utilidades.AdicionadorDeComponentes;
import View_Utilidades.Icones;

public class TelaDeGerente extends TelaComMenu{

	
	private int codigoDoGerenteLogado;
	
	public TelaDeGerente(int codigoDoGerenteLogado) {
		super("Gerenciamento");
	
		this.codigoDoGerenteLogado = codigoDoGerenteLogado;
		
		adicionarLabels();
		adicionarBotoes();
		
		this.setVisible(true);
	}
	
	
	
//	@Override
//	public void adicionarComponentesGraficos() {
//		adicionarLabels();
//		adicionarBotoes();
//	}

	private void adicionarLabels() {
		
		

		FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
		funcionarioDTO.setCodigo(codigoDoGerenteLogado);
		AdicionadorDeComponentes.adicionarIdentificacao(this, funcionarioDTO);
		
		
		JLabel L = AdicionadorDeComponentes.adicionarJLabel(this, "GERÊNCIA", 195, 15, 450, 100);
		L.setFont(new Font("Times new Roman", Font.BOLD, 75));
		L.setForeground(Color.DARK_GRAY);
		
//		FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
//		funcionarioDTO.setCodigo(codigoDoGerenteLogado);
//		
//
//		System.out.println("gerente: "+funcionarioDTO.getCodigo());
//		AdicionadorDeComponentes.adicionarIdentificacao(this, funcionarioDTO);
//		
	}

	
	private void adicionarBotoes() {		
		OuvinteTelaDeGerente ouvinteTelaDeGerente = new OuvinteTelaDeGerente(this);
		
		
		JPanel painel = new JPanel(new GridLayout(2, 4, 1, 1));
		
		painel.setBackground(Color.orange);
			
		JButton botaoClientes = AdicionadorDeComponentes.adicionarJButton(painel, "Clientes", 0, 0, 0, 0);
		botaoClientes.setToolTipText(botaoClientes.getText());
		botaoClientes.setIcon(Icones.ICONE_CLIENTES);
		botaoClientes.addActionListener(ouvinteTelaDeGerente);
//		deixando o texto do botão na parte SUL
		botaoClientes.setVerticalTextPosition(SwingConstants.BOTTOM);
//		deixando o icon do botão no CENTRO
		botaoClientes.setHorizontalTextPosition(SwingConstants.CENTER);
		
		
		JButton botaoFuncionarios = AdicionadorDeComponentes.adicionarJButton(painel, "Funcionarios", 0, 0, 0, 0);
		botaoFuncionarios.setToolTipText(botaoFuncionarios.getText());
		botaoFuncionarios.setIcon(Icones.ICONE_FUNCIONARIOS);
		botaoFuncionarios.addActionListener(ouvinteTelaDeGerente);
		botaoFuncionarios.setVerticalTextPosition(SwingConstants.BOTTOM);
		botaoFuncionarios.setHorizontalTextPosition(SwingConstants.CENTER);
		
		JButton botaoContabilidade = AdicionadorDeComponentes.adicionarJButton(painel, "Contabilidade", 0, 0, 0, 0);
		botaoContabilidade.setToolTipText(botaoContabilidade.getText());
		botaoContabilidade.setIcon(Icones.ICONE_REAIS);
		botaoContabilidade.addActionListener(ouvinteTelaDeGerente);
		botaoContabilidade.setVerticalTextPosition(SwingConstants.BOTTOM);
		botaoContabilidade.setHorizontalTextPosition(SwingConstants.CENTER);
		
		JButton botaoAtendimento = AdicionadorDeComponentes.adicionarJButton(painel, "Atendimento", 0, 0, 0, 0);
		botaoAtendimento.setToolTipText(botaoAtendimento.getText());
		botaoAtendimento.setIcon(Icones.ICONE_ATENDIMENTO);
		botaoAtendimento.addActionListener(ouvinteTelaDeGerente);
		botaoAtendimento.setVerticalTextPosition(SwingConstants.BOTTOM);
		botaoAtendimento.setHorizontalTextPosition(SwingConstants.CENTER);
		
		JButton botaoIngredientes = AdicionadorDeComponentes.adicionarJButton(painel, "Ingredientes", 0, 0, 0, 0);
		botaoIngredientes.setToolTipText(botaoIngredientes.getText());
		botaoIngredientes.setIcon(Icones.ICONE_INGREDIENTES);
		botaoIngredientes.addActionListener(ouvinteTelaDeGerente);
		botaoIngredientes.setVerticalTextPosition(SwingConstants.BOTTOM);
		botaoIngredientes.setHorizontalTextPosition(SwingConstants.CENTER);
		
		JButton botaoPizzas = AdicionadorDeComponentes.adicionarJButton(painel, "Pizzas", 0, 0, 0, 0);
		botaoPizzas.setToolTipText(botaoPizzas.getText());
		botaoPizzas.setIcon(Icones.ICONE_PIZZA);
		botaoPizzas.addActionListener(ouvinteTelaDeGerente);
		botaoPizzas.setVerticalTextPosition(SwingConstants.BOTTOM);
		botaoPizzas.setHorizontalTextPosition(SwingConstants.CENTER);
		
		JButton botaoCozinha = AdicionadorDeComponentes.adicionarJButton(painel, "Cozinha", 0, 0, 0, 0);
		botaoCozinha.setToolTipText(botaoCozinha.getText());
		botaoCozinha.setIcon(Icones.ICONE_PIZZAIOLO);
		botaoCozinha.addActionListener(ouvinteTelaDeGerente);
		botaoCozinha.setVerticalTextPosition(SwingConstants.BOTTOM);
		botaoCozinha.setHorizontalTextPosition(SwingConstants.CENTER);
		
		JButton botaoEntrega = AdicionadorDeComponentes.adicionarJButton(painel, "Entrega", 0, 0, 0, 0);
		botaoEntrega.setToolTipText(botaoEntrega.getText());
		botaoEntrega.setIcon(Icones.ICONE_MOTOBOY);
		botaoEntrega.addActionListener(ouvinteTelaDeGerente);
		botaoEntrega.setVerticalTextPosition(SwingConstants.BOTTOM);
		botaoEntrega.setHorizontalTextPosition(SwingConstants.CENTER);
		
		painel.setBounds(130, 135, 528, 240);
		add(painel);
	}
 
		
	private class OuvinteTelaDeGerente implements ActionListener{

		private JFrame janela;
		
		public OuvinteTelaDeGerente (JFrame janela) {
			this.janela = janela;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			String botao = e.getActionCommand();
			
			if(botao.equals("Clientes")) {
				TelaDeClientes telaDeClientes = new TelaDeClientes(codigoDoGerenteLogado);
				telaDeClientes.setLocationRelativeTo(janela);
				janela.dispose();
			}else if(botao.equals("Funcionarios")) {
				TelaDeFuncionarios telaDeFuncionarios = new TelaDeFuncionarios(codigoDoGerenteLogado);
				telaDeFuncionarios.setLocationRelativeTo(janela);
				janela.dispose();
			}else if(botao.equals("Contabilidade")){
				TelaDeContabilidade telaDeContabilidade = new TelaDeContabilidade(codigoDoGerenteLogado);
				telaDeContabilidade.setLocationRelativeTo(janela);
				janela.dispose();
			}else if(botao.equals("Pizzas")){
				TelaDePizzas telaDeSaboresDePizzas = new TelaDePizzas(codigoDoGerenteLogado, false);
				telaDeSaboresDePizzas.setLocationRelativeTo(janela);
				janela.dispose();
			}else if(botao.equals("Atendimento")){
				TelaDeAtendimento telaDeAtendimento = new TelaDeAtendimento(codigoDoGerenteLogado);
				telaDeAtendimento.setLocationRelativeTo(janela);
				janela.dispose();
			}else if(botao.equals("Cozinha")){
				TelaDePizzaiolo telaDePizzaiolo = new TelaDePizzaiolo(codigoDoGerenteLogado);
				telaDePizzaiolo.setLocationRelativeTo(janela);
				janela.dispose();
			}else if(botao.equals("Entrega")){
				TelaDeMotoboy telaDeMotoboy = new TelaDeMotoboy(codigoDoGerenteLogado);
				telaDeMotoboy.setLocationRelativeTo(janela);
				janela.dispose();
			}else if(botao.equals("Ingredientes")) {
				TelaDeIngredientes telaDeIngredientes = new TelaDeIngredientes(codigoDoGerenteLogado);
				telaDeIngredientes.setLocationRelativeTo(janela);
				janela.dispose();
				
			}
			
			
			
		}
		
	}






	
}
