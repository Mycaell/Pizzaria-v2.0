package View;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controller.GerenteController;
import DTO.FuncionarioDTO;
import View_Ouvintes.OuvinteQueLevaParaTelaDoGerente;
import View_Utilidades.AdicionadorDeComponentes;
import View_Utilidades.Icones;

public class TelaDeContabilidade extends TelaComMenu {

	private JTable tabela;

	private int codigoDoGerenteLogado;
	
	public TelaDeContabilidade(int codigoDoGerenteLogado) {
		super("Contabilidade");
		
		this.codigoDoGerenteLogado = codigoDoGerenteLogado;
	

	
		adicionarLabels();
		adicionarTabela();
		adicionarBotoes();
	
		this.setVisible(true);

		
	}
	

//	@Override
//	public void adicionarComponentesGraficos() {
//		adicionarLabels();
//		adicionarTabela();
//		adicionarBotoes();
//		
//	}
//	
	
	private void adicionarLabels() {

		
		FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
		funcionarioDTO.setCodigo(codigoDoGerenteLogado);
		AdicionadorDeComponentes.adicionarIdentificacao(this, funcionarioDTO);
	
		
		JLabel L = AdicionadorDeComponentes.adicionarJLabel(this, "CONTABILIDADE", 140, 40, 590, 60);
		L.setFont(new Font("Times new Roman", Font.BOLD, 60));
		L.setForeground(Color.DARK_GRAY);
//		
//		FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
//		funcionarioDTO.setCodigo(codigoDoGerenteLogado);
//		AdicionadorDeComponentes.adicionarIdentificacao(this, funcionarioDTO);
	}



	private void adicionarTabela() {

		GerenteController gerenteController = new GerenteController();
		
		DefaultTableModel modelo = new DefaultTableModel();		
		
		tabela = new JTable(modelo);

		
		ArrayList<String[]> pizzas = gerenteController.recuperarDadosDeTodasPizzas().getDadosDasPizzas();

		String[] pizzaMaisVendida = new String[2];
		
		boolean vendeuAlgumaPizza = false;
		
		
		if(pizzas.size() != 0) {
			
			String[] pizzaDaPosicaoZero = pizzas.get(0);
			pizzaMaisVendida[0] = pizzaDaPosicaoZero[0];
			pizzaMaisVendida[1] = pizzaDaPosicaoZero[2];

			
			for (String[] pizza : pizzas) {
				if((Integer.parseInt(pizza[2]) != 0)) {
					vendeuAlgumaPizza = true;
				}
				if(Integer.parseInt(pizza[2]) >= Integer.parseInt(pizzaMaisVendida[1])) {
					pizzaMaisVendida[0] = pizza[0];
					pizzaMaisVendida[1] = pizza[2];
				}
			}	
		}

		
		
		if(vendeuAlgumaPizza) {
			modelo.addColumn("pizzas vendidas");
			modelo.addColumn("Lucro");
			modelo.addColumn("sabor mais vendido");
			
			Object[] linha = {gerenteController.getQtdVendas().getVendas(), gerenteController.getLucro().getLucro(), pizzaMaisVendida[0]};
			modelo.addRow(linha);
		}else {
			modelo.addColumn("Nenhuma pizza foi vendida!");
		}
		
		
		
		JScrollPane scroll = new JScrollPane(tabela);
		scroll.setBounds(80, 135, 600, 38);
		add(scroll);
	
		
		
	}



	private void adicionarBotoes() {
	
		
		JButton voltar = AdicionadorDeComponentes.adicionarJButton(this, "Voltar", 80, 330, 90, 20);
		voltar.setIcon(Icones.ICONE_VOLTAR);
		voltar.addActionListener(new OuvinteQueLevaParaTelaDoGerente(this, codigoDoGerenteLogado));
	}





	
	
	
}
