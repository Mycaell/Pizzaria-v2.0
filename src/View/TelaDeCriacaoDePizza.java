package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import Controller.GerenteController;
import DTO.FuncionarioDTO;
import DTO.IngredienteDTO;
import DTO.PizzaDTO;
import View_Utilidades.AdicionadorDeComponentes;
import View_Utilidades.Icones;

public class TelaDeCriacaoDePizza extends TelaComMenu{

	private int codigoDoGerenteLogado;
	
	private JTable tabelaIngredientesQueCompoeAPizza;
	private DefaultTableModel modelo1;
	
	
	private JTable tabelaIngredientes;
	
	
	private JTextField campoNome;
	private JTextField campoPreco;
	private JTextField campoValorFatia;
	
	private JTextArea modoDePreparo;	
	
	
	private GerenteController gerenteController = new GerenteController();
	

	private IngredienteDTO ingredientes = new IngredienteDTO();
	
	private JButton botaoConcluir;
	private JLabel labelCentral;
	
	
	public JButton getBotaoConcluir() {
		return botaoConcluir;
	}
	public void setBotaoConcluir(JButton botaoConcluir) {
		this.botaoConcluir = botaoConcluir;
	}
	public JTextField getCampoNome() {
		return campoNome;
	}
	public void setCampoNome(JTextField campoNome) {
		this.campoNome = campoNome;
	}

	public JTextField getCampoPreco() {
		return campoPreco;
	}
	public void setCampoPreco(JTextField campoPreco) {
		this.campoPreco = campoPreco;
	}
	public JTextField getCampoValorFatia() {
		return campoValorFatia;
	}
	public void setCampoValorFatia(JTextField campoValorFatia) {
		this.campoValorFatia = campoValorFatia;
	}
	public DefaultTableModel getModelo1() {
		return modelo1;
	}
	public JTextArea getModoDePreparo() {
		return modoDePreparo;
	}
	public void setModoDePreparo(JTextArea modoDePreparo) {
		this.modoDePreparo = modoDePreparo;
	}
	
	
	
	
	public JLabel getLabelCentral() {
		return labelCentral;
	}
	public void setLabelCentral(JLabel labelCentral) {
		this.labelCentral = labelCentral;
	}
	public TelaDeCriacaoDePizza(int codigoDoGerenteLogado) {
		super("Nova pizza");

		this.codigoDoGerenteLogado = codigoDoGerenteLogado;
		

		adicionarLabels();
		adicionarFields();
		adicionarTabelaDeIngredientesQueEstaoCompondoAPizza();
		adicionarTabelaDeIngredientes();
		adicionarBotoes();		
		
	
		this.setVisible(true);
		
	}

	
//	@Override
//	public void adicionarComponentesGraficos() {
//		adicionarLabels();
//		adicionarFields();
//		adicionarTabelaDeIngredientesQueEstaoCompondoAPizza();
//		adicionarTabelaDeIngredientes();
//		adicionarBotoes();		
//	}
	

	private void adicionarTabelaDeIngredientes() {
		
		DefaultTableModel modelo = new DefaultTableModel();		
		tabelaIngredientes = new JTable(modelo);

		GerenteController gerenteController = new GerenteController();
		
		ArrayList<String[]> ingredientes = gerenteController.recuperarIngredientes().getIngredientes();
		
		if(ingredientes.size() != 0) {

			modelo.addColumn("ID");
			modelo.addColumn("Nome");
			modelo.addColumn("Preço");

//			escondendo a coluda ID
			tabelaIngredientes.getColumnModel().getColumn(0).setMinWidth(0);
			tabelaIngredientes.getColumnModel().getColumn(0).setMaxWidth(0);

			for (String[] i : ingredientes) {
				Object[] linha = {i[0], i[1], i[2]};
				modelo.addRow(linha);
			}
			
			tabelaIngredientes.addRowSelectionInterval(0, 0);		
		}else {
			modelo.addColumn("Não há Ingredientes");
		}
		
		
		JScrollPane scroll = new JScrollPane(tabelaIngredientes);
		scroll.setBounds(450, 145, 190, 135);
		add(scroll);
		
	}


	private void adicionarFields() {
		campoNome = AdicionadorDeComponentes.adicionarJTextField(this, 80, 120, 130, 20);
		campoPreco = AdicionadorDeComponentes.adicionarJTextField(this, 565, 330, 60, 20);

//		*******************************************************************************************************
//		aqui eu deveria pegar o preço da massa e nao colocar um valor estatico (irá facilitar a manutenção futuramente)
		campoPreco.setText("10");
		campoPreco.setEnabled(false);
		campoValorFatia = AdicionadorDeComponentes.adicionarJTextField(this, 365, 120, 70, 20);
	
		modoDePreparo = new JTextArea();

		
		modoDePreparo.setLineWrap(true);
		modoDePreparo.setWrapStyleWord(true);
		
		JScrollPane scroll = new JScrollPane(modoDePreparo);
		scroll.setBounds(655, 145, 115, 135);
//		scroll.setBounds(655, 145, 115, 205);
		add(scroll);
	}


	private void adicionarLabels() {

		labelCentral = AdicionadorDeComponentes.adicionarJLabel(this, "NOVA PIZZA", 165, 40, 500, 60);
		labelCentral.setFont(new Font("Times new Roman", Font.BOLD, 75));
		labelCentral.setForeground(Color.DARK_GRAY);
		
		
		
		AdicionadorDeComponentes.adicionarJLabel(this, "Nome", 20, 120, 70, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "Valor por fatia", 235, 120, 125, 20);
		AdicionadorDeComponentes.adicionarJLabel(this, "Ingredientes:", 470, 120, 130, 25);
		AdicionadorDeComponentes.adicionarJLabel(this, "Ingrediente(s) que compõe a pizza:", 20, 150, 310, 25);
		AdicionadorDeComponentes.adicionarJLabel(this, "Modo de preparo:", 635, 120, 160, 20);
//		AdicionadorDeComponentes.adicionarJLabel(this, "Custo de preparo", 455, 330, 130, 20);

		JLabel label2 = new JLabel("Custo de preparo");
		label2.setFont(new Font("Comic Sans", Font.BOLD, 13));
		label2.setBounds(450, 330, 130, 20);
		label2.setForeground(Color.BLACK);
		add(label2);
		
		
		
		FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
		funcionarioDTO.setCodigo(codigoDoGerenteLogado);
		AdicionadorDeComponentes.adicionarIdentificacao(this, funcionarioDTO);
		
		
	}

	private void adicionarBotoes() {

		OuvinteBotoesTelaDeCriacaoDePizza ouvinte = new OuvinteBotoesTelaDeCriacaoDePizza(this);

		JButton botaoVoltar = AdicionadorDeComponentes.adicionarJButton(this, "Voltar", 30, 360, 90, 20);
		botaoVoltar.setIcon(Icones.ICONE_VOLTAR);
		botaoVoltar.addActionListener(ouvinte);
		
		JButton adicionarSabor = AdicionadorDeComponentes.adicionarJButton(this, "Adicionar", 480, 295, 145, 20);
		adicionarSabor.setIcon(Icones.ICONE_MAIS);
		adicionarSabor.addActionListener(ouvinte);
	
		JButton removerSabor = AdicionadorDeComponentes.adicionarJButton(this, "Remover", 283, 360, 145, 20);
		removerSabor.setIcon(Icones.ICONE_MENOS);
		removerSabor.addActionListener(ouvinte);
		

		
		
		JLabel labelBotaoFinalizar = new JLabel("Finalizar");
		labelBotaoFinalizar.setFont(new Font("Comic Sans", Font.BOLD, 18));
		labelBotaoFinalizar.setForeground(Color.BLACK);
		
//		botaoConcluir = AdicionadorDeComponentes.adicionarJButton(this, "Finalizar", 655, 290, 115, 60);
		botaoConcluir = AdicionadorDeComponentes.adicionarJButton(this, "", 655, 290, 115, 60);
//		botaoConcluir.setIcon(Icones.ICONE_CERTO);
		botaoConcluir.add(labelBotaoFinalizar);
		botaoConcluir.addActionListener(ouvinte);
		
	}


	private void adicionarTabelaDeIngredientesQueEstaoCompondoAPizza() {
		

		modelo1 = new DefaultTableModel();
		modelo1.addColumn("ID");
		modelo1.addColumn("Nome");
		modelo1.addColumn("Preco");

//		escondendo a coluna ID
		tabelaIngredientesQueCompoeAPizza = new JTable(modelo1);
		tabelaIngredientesQueCompoeAPizza.getColumnModel().getColumn(0).setMinWidth(0);
		tabelaIngredientesQueCompoeAPizza.getColumnModel().getColumn(0).setMaxWidth(0);
		
		JScrollPane scroll = new JScrollPane(tabelaIngredientesQueCompoeAPizza);
		scroll.setBounds(30, 180, 400, 170);
		add(scroll);
		
	}

	
	

	private class OuvinteBotoesTelaDeCriacaoDePizza implements ActionListener{
		
		private TelaDeCriacaoDePizza telaDeCriacaoDePizza;

		public OuvinteBotoesTelaDeCriacaoDePizza(TelaDeCriacaoDePizza telaDeSaboresDePizza) {
			this.telaDeCriacaoDePizza = telaDeSaboresDePizza;
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			String botao = e.getActionCommand();
			
			Object botaoFinaliza = e.getSource();
			
			if(botao.equals("Remover")) {
				int linhaSelecionada = tabelaIngredientesQueCompoeAPizza.getSelectedRow();
				
				if(linhaSelecionada != -1) {
					
					float preco = Float.parseFloat((String)tabelaIngredientesQueCompoeAPizza.getValueAt(tabelaIngredientesQueCompoeAPizza.getSelectedRow(), 2));
					
					campoPreco.setText(Float.toString(Float.parseFloat(campoPreco.getText()) - preco));	
					
					modelo1.removeRow(linhaSelecionada);
					
				}else {
					JOptionPane.showMessageDialog(telaDeCriacaoDePizza, "Selecione um ingrediente da pizza!", "Nenhum ingrediente selecionado", JOptionPane.ERROR_MESSAGE);
				}
			}else if(botao.equals("Adicionar")) {
				
 				int linhaSelecionada = tabelaIngredientes.getSelectedRow();

 				modelo1.addRow(new String[] {(String)tabelaIngredientes.getValueAt(linhaSelecionada, 0), (String)tabelaIngredientes.getValueAt(linhaSelecionada, 1), (String)tabelaIngredientes.getValueAt(linhaSelecionada, 2)});
 				
				float valorPizza = Float.parseFloat(campoPreco.getText()) + Float.parseFloat((String) tabelaIngredientes.getValueAt(linhaSelecionada, 2));
				
				campoPreco.setText(Float.toString(valorPizza));	
				
				
			}else if(botaoFinaliza.equals(botaoConcluir)) {
				String nome = campoNome.getText();
				float preco = Float.parseFloat(campoPreco.getText());
				float valorPorFatia = Float.parseFloat(campoValorFatia.getText());
				String modoPreparo = modoDePreparo.getText();
				
				PizzaDTO pizzaDTO = new PizzaDTO(nome, valorPorFatia, preco, modoPreparo, codigoDoGerenteLogado);
						
				for(int i = 0; i < tabelaIngredientesQueCompoeAPizza.getModel().getRowCount(); i++) {
					String[] ingrediente = new String[2];
					
					ingrediente[0] = String.valueOf(tabelaIngredientesQueCompoeAPizza.getValueAt(i, 0)+"-"+tabelaIngredientesQueCompoeAPizza.getValueAt(i, 1));
					ingrediente[1] = String.valueOf(tabelaIngredientesQueCompoeAPizza.getValueAt(i, 2));
					
					ingredientes.getIngredientes().add(ingrediente);
				}
				
				IngredienteDTO ingredienteDTO_decorado = gerenteController.decorar(ingredientes);

				gerenteController.adicionarPizza(pizzaDTO, ingredienteDTO_decorado);
				
				JOptionPane.showMessageDialog(telaDeCriacaoDePizza, "Pizza criada!");
				TelaDePizzas telaDePizzas = new TelaDePizzas(codigoDoGerenteLogado, false);
				telaDePizzas.setLocationRelativeTo(telaDeCriacaoDePizza);
				telaDeCriacaoDePizza.dispose();
				
			}else if(botao.equals("Voltar")) {
				TelaDePizzas telaDePizzas = new TelaDePizzas(codigoDoGerenteLogado, false);
				telaDePizzas.setLocationRelativeTo(telaDeCriacaoDePizza);
				telaDeCriacaoDePizza.dispose();
			}
		}
	}

	public static void main(String[] args) {
		new TelaDeCriacaoDePizza(23);
	}

	
	
	
}
