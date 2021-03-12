package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

import Controller.GerenteController;
import DTO.FuncionarioDTO;
import DTO.PizzaDTO;
import View_Utilidades.AdicionadorDeComponentes;
import View_Utilidades.Icones;

public class TelaDeEdicaoDePizza extends TelaDeCriacaoDePizza{

	private int codigoDoGerenteLogado;
	private int idDaPizza;
	private String saborAtual;
	private float precoFatia;
	private float custoPreparo;
	private String modoDePreparo;
	
	
	private JButton botaoConcluir;
	
	public TelaDeEdicaoDePizza(int codigoDoGerenteLogado, int idDaPizza, String saborAtual, float precoFatia, String modoDePreparo) {
		super(codigoDoGerenteLogado);
		this.setTitle("Edição de Pizza");
	
		this.codigoDoGerenteLogado = codigoDoGerenteLogado;
		this.idDaPizza = idDaPizza;
		this.saborAtual = saborAtual;
		this.precoFatia = precoFatia;
		this.modoDePreparo = modoDePreparo;
		
		
		preencherCampos();
		removerComponentes();
		
		adicionarComponentes();
		adicionarIngredientesAtuaisDaPizza();	
		this.setVisible(true);
	}
	
	private void removerComponentes() {
		super.remove(super.getBotaoConcluir());
		super.remove(super.getLabelCentral());		
	}

	private void preencherCampos() {
		GerenteController gerenteController = new GerenteController();
		
		FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
		funcionarioDTO.setCodigo(codigoDoGerenteLogado);
		AdicionadorDeComponentes.adicionarIdentificacao(this, funcionarioDTO);
				
		
		
		super.getCampoNome().setText(saborAtual);
		super.getCampoValorFatia().setText(Float.toString(precoFatia));
		super.getModoDePreparo().setText(modoDePreparo);
		
		
		PizzaDTO pizzaDTO = new PizzaDTO();
		pizzaDTO.setIdDaPizza(idDaPizza);
		
		custoPreparo = gerenteController.recuperarCustoPreparoDePizza(pizzaDTO).getCustoPreparo();
		
		super.getCampoPreco().setText(Float.toString(custoPreparo));
		
	}

	
	private void adicionarComponentes() {
		
		JLabel labelCentral = AdicionadorDeComponentes.adicionarJLabel(this, "EDIÇÃO de PIZZA", 150, 25, 590, 90);
		labelCentral.setFont(new Font("Times new Roman", Font.BOLD, 60));
		labelCentral.setForeground(Color.DARK_GRAY);
		
		
		
		JLabel labelBotaoFinalizar = new JLabel("Finalizar");
		labelBotaoFinalizar.setFont(new Font("Comic Sans", Font.BOLD, 18));
		labelBotaoFinalizar.setForeground(Color.BLACK);
	
		
		botaoConcluir = AdicionadorDeComponentes.adicionarJButton(this, "", 655, 290, 115, 60);
//		botaoConcluir.setIcon(Icones.ICONE_CERTO);
		botaoConcluir.add(labelBotaoFinalizar);
		botaoConcluir.addActionListener(new OuvinteBotaoFinalizar(this));
	}

	
	public void adicionarIngredientesAtuaisDaPizza() {
		
		GerenteController gerenteController = new GerenteController();
		
		DefaultTableModel modelo = super.getModelo1();

		PizzaDTO pizzaDTO = new PizzaDTO();
		pizzaDTO.setIdDaPizza(idDaPizza);
		
		ArrayList<String[]> ingredientes = gerenteController.recuperarIngredientesDeUmaPizza(pizzaDTO).getIngredientes();
		
		for (String[] i : ingredientes) {
			Object[] linha = {i[0], i[1], i[2]};
			modelo.addRow(linha);
		}
	
		
	}
	

	
	
	
	private class OuvinteBotaoFinalizar implements ActionListener{

		private TelaDeEdicaoDePizza telaDeEdicaoDePizza;
		
		public OuvinteBotaoFinalizar(TelaDeEdicaoDePizza telaDeEdicaoDePizza) {
			this.telaDeEdicaoDePizza = telaDeEdicaoDePizza;
		}
		
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			
			
			String sabor = saborAtual;
			float precoDaFatia = precoFatia;
			float precoDePreparo = custoPreparo;
			String modoPreparo = modoDePreparo;
			String botao = e.getActionCommand();

		
			Object botaoFinalizar = e.getSource();
//			!!!!!!!!!!!!
//			FAZER A LÓGICA DE ATUALIZAÇÃO DOS INGREDIENTES
//			!!!!!!!!!!!!
			
			
			
			
//			if desnecessário pois o ouvinte estará ouvindo apenas um único botão
//			if(botao.equals("Finalizar")) {
//			FAZER A LÓGICA DE ATUALIZAÇÃO DOS INGREDIENTES
//				
//			}
//			.
			
		}
		
		
		
	}
	
	
	
	
}
