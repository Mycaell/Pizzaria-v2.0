package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controller.AtendenteController;
import Controller.GerenteController;
import DTO.ClienteDTO;
import DTO.FuncionarioDTO;
import DTO.PedidoDTO;
import DTO.PizzaDTO;
import View_Ouvintes.OuvinteQueLevaParaTelaDoGerente;
import View_Utilidades.AdicionadorDeComponentes;
import View_Utilidades.Icones;
import View_excecoes.CPFInexistenteException;
import View_excecoes.CampoVazioException;

public class TelaDeAtendimento extends TelaComMenu{

	private JFormattedTextField cpf;
	
	private JLabel LabelPreco;
	private JLabel saboresQueIraoComporAPizza;
	
	
	private JRadioButton saborUnico;
	private JRadioButton saboresMistos;
	
	private JTable tabela;
	private DefaultTableModel modelo;
	
	private int codigoDoAtendenteLogado;
	
	private JComboBox<String> tamanho;
	
	private JComboBox<Object> sabor1;
	private JComboBox<Object> sabor2;
	private JComboBox<Object> sabor3;
	
	private Object[] sabores;
	
	private DefaultListModel modeloLista;
	private JList<String> lista;
	private JScrollPane scroll;
	
	
	private boolean vimDaTelaDeAtendimento;

	
	public TelaDeAtendimento(int codigoDoAtendenteLogado) {
		super("Atendimento");

		this.setSize(990,700);
		
		this.codigoDoAtendenteLogado = codigoDoAtendenteLogado;
	
		this.vimDaTelaDeAtendimento = true;

		adicionarMenu();
		adicionarLabels();
		adicionarFields();
		adicionarComboBoxes();
		adicionarBotoes();
		adicionarTabelaDePedidos();
		this.setLocationRelativeTo(null);
		
		this.setVisible(true);
	}

	
//	@Override
//	public void adicionarComponentesGraficos() {
//		adicionarMenu();
//		adicionarLabels();
//		adicionarFields();
//		adicionarComboBoxes();
//		adicionarBotoes();
//		adicionarTabelaDePedidos();
//		this.setLocationRelativeTo(null);
//	}
	
	
	public JFormattedTextField getCpf() {
		return cpf;
	}

	public void setCpf(JFormattedTextField cpf) {
		this.cpf = cpf;
	}


	private void adicionarMenu() {
		JMenuBar barraDeMenu = this.getJMenuBar();

//		JMenu menu = barraDeMenu.getMenu(0);
		
		JMenu menuCliente = new JMenu("Novo Cliente");
		menuCliente.setIcon(Icones.ICONE_USUARIO);
		

		JMenu menuPizza = new JMenu("Pizzas da Casa");
		menuPizza.setIcon(Icones.ICONE_PIZZA3);
		
		
		
		JMenuItem itemNovoCliente = new JMenuItem("Cadastrar Cliente");
		itemNovoCliente.setIcon(Icones.ICONE_MAIS);
		itemNovoCliente.addActionListener(new OuvinteBotoesTelaDeAtendimento(this));
		menuCliente.add(itemNovoCliente);
		
		
		JMenuItem itemPizzas = new JMenuItem("Pizzas");
		itemPizzas.setIcon(Icones.ICONE_PIZZA4);
		itemPizzas.addActionListener(new OuvinteBotoesTelaDeAtendimento(this));
		menuPizza.add(itemPizzas);
		
		
		
		barraDeMenu.add(menuCliente);
		barraDeMenu.add(menuPizza);
	}

	
	private void adicionarLabels() {
		
		


		
//		"<html><u> MEU TEXTO </u></html>"
//		GerenteController gerenteController = new GerenteController();

		JLabel label = new JLabel(Icones.FUNDO_ATENDIMENTO);
		label.setBounds(0, 0, 990, 600);
		setContentPane(label);

		FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
		funcionarioDTO.setCodigo(codigoDoAtendenteLogado);
		AdicionadorDeComponentes.adicionarIdentificacao(this, funcionarioDTO);

		JLabel L = AdicionadorDeComponentes.adicionarJLabel(this, "ATENDIMENTO", 170, 40, 590, 60);
		L.setFont(new Font("Times new Roman", Font.BOLD, 75));
		L.setForeground(Color.DARK_GRAY);
		
		
		modeloLista = new DefaultListModel();
				
		lista = new JList<String>(modeloLista);
		
		
		lista.addMouseListener(new OuvinteDeCliqueDaListaDeCPF(this));
		
		scroll = new JScrollPane(lista);
		scroll.setBounds(680, 190, 110, 75);
		scroll.setVisible(false);
		add(scroll);
		
		
//		
//		FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
//		funcionarioDTO.setCodigo(codigoDoAtendenteLogado);
//		AdicionadorDeComponentes.adicionarIdentificacao(this, funcionarioDTO);
		
		
		AdicionadorDeComponentes.adicionarJLabel(this, "<html><u>Histórico de Pedidos</u></html>", 205, 130, 195, 20);
		

		AdicionadorDeComponentes.adicionarJLabel(this, "<html><u>Pedido</u></html>", 775, 130, 80, 20);
		
		AdicionadorDeComponentes.adicionarJLabel(this, "CPF", 635, 170, 50, 20);
		
		AdicionadorDeComponentes.adicionarJLabel(this, "Tamanho", 635, 195, 90, 20);
		
		JLabel labelTamanho = AdicionadorDeComponentes.adicionarJLabel(this, "Preço: R$", 690, 545, 95, 20);
		labelTamanho.setOpaque(false);
		
		LabelPreco = AdicionadorDeComponentes.adicionarJLabel(this, "00,00", 790, 545, 140, 20); 
		
		saboresQueIraoComporAPizza = AdicionadorDeComponentes.adicionarJLabel(this, "Sabores que irão compor a pizza:", 650, 250, 310, 20);
		saboresQueIraoComporAPizza.setVisible(false);
	}

	
	private void adicionarFields() {
		try {
			cpf = AdicionadorDeComponentes.adicionarJFormattedTextFiel(this, "###.###.###-##", 680, 170, 110, 20);
			cpf.addKeyListener(new KeyListenerCampoCPF(this));
			cpf.addFocusListener(new OuvinteDeFocoCampoCPF(this));
		}catch (ParseException e) {
		}
		
		
	}

	private void adicionarComboBoxes() {
		String[] tam = {"Pequeno (4 fatias)","Médio (8 fatias)","Grande (12 fatias)"};
		tamanho = new JComboBox<>(tam);
		tamanho.setBounds(725, 195, 130, 20);
		add(tamanho);
		
		AtendenteController atendenteController = new AtendenteController();

		sabores = atendenteController.recuperarSaboresDePizzas().getSaboresDePizzas().toArray();
		
		if(sabores.length != 0) {
			
			OuvinteComboBoxes ouvinteComboBoxes = new OuvinteComboBoxes(this);
			
			sabor1 = new JComboBox<>(sabores);
			sabor1.setBounds(630, 275, 90, 20);
			add(sabor1);
			sabor1.setVisible(false);
			
			sabor2 = new JComboBox<>(sabores);
			sabor2.setBounds(755, 275, 90, 20);
			add(sabor2);
			sabor2.setVisible(false);
			
			sabor3 = new JComboBox<>(sabores);
			sabor3.setBounds(875, 275, 90, 20);
			add(sabor3);
			sabor3.setVisible(false);

		
			sabor1.addActionListener(ouvinteComboBoxes);
			sabor2.addActionListener(ouvinteComboBoxes);
			sabor3.addActionListener(ouvinteComboBoxes);
		}
	}

	
	private void adicionarBotoes() {
		
		ButtonGroup grupo = new ButtonGroup();
		
		OuvinteRadioButtons ouvinteRadioButtons = new OuvinteRadioButtons(this);
		saborUnico = new JRadioButton("Sabor Único");
		saborUnico.addActionListener(ouvinteRadioButtons);
		saborUnico.setBounds(635, 225, 95, 20);
//		saborUnico.setBackground(null);
		saborUnico.setForeground(Color.BLACK);
		saborUnico.setOpaque(false);
		add(saborUnico);
		
		saboresMistos = new JRadioButton("Sabores Mistos");
		saboresMistos.addActionListener(ouvinteRadioButtons);
		saboresMistos.setBounds(755, 225, 120, 20);
//		saboresMistos.setBackground(null);
		saboresMistos.setForeground(Color.BLACK);
		saboresMistos.setOpaque(false);
		add(saboresMistos);
		
		grupo.add(saborUnico);
		grupo.add(saboresMistos);
		
		OuvinteBotoesTelaDeAtendimento ouvinteBotoes = new OuvinteBotoesTelaDeAtendimento(this);
		JButton botaoFinalizarPedido = AdicionadorDeComponentes.adicionarJButton(this, "Finalizar Pedido", 825, 600, 145, 20);
		botaoFinalizarPedido.setIcon(Icones.ICONE_CERTO);
		botaoFinalizarPedido.addActionListener(ouvinteBotoes);
		
		FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
		funcionarioDTO.setCodigo(codigoDoAtendenteLogado);
		
		if(new GerenteController().recuperarCargo(funcionarioDTO).getCargo().equals("Gerente")) {
			JButton botaoVoltar = AdicionadorDeComponentes.adicionarJButton(this, "Voltar", 10, 600, 90, 20);
			botaoVoltar.setIcon(Icones.ICONE_VOLTAR);
			botaoVoltar.addActionListener(new OuvinteQueLevaParaTelaDoGerente(this, codigoDoAtendenteLogado));
		}
		
	}
	
	
	private void adicionarTabelaDePedidos() {
		
		modelo = new DefaultTableModel();
		tabela = new JTable(modelo);
	

		AtendenteController atendenteController = new AtendenteController();
		
		ArrayList<String[]> pedidos = atendenteController.recuperarDadosDeTodosPedidos().getPedidos();
		
		if(pedidos.size() == 0) {
			modelo.addColumn("Não há pedidos");
		}else {
			modelo.addColumn("Nº do pedido");
			modelo.addColumn("CPF do cliente");
			modelo.addColumn("Tamanho");
			modelo.addColumn("Fatias");
			modelo.addColumn("Sabores");
			modelo.addColumn("Preço");
			modelo.addColumn("Pronto");
			modelo.addColumn("Entregue");
			modelo.addColumn("Data do Pedido");
			
			
//		\/ altera o tamanho de uma determinada célula do JTable
			tabela.getColumnModel().getColumn(0).setPreferredWidth(85);
			tabela.getColumnModel().getColumn(1).setPreferredWidth(103);
			tabela.getColumnModel().getColumn(3).setPreferredWidth(40);
			tabela.getColumnModel().getColumn(5).setPreferredWidth(49);
			tabela.getColumnModel().getColumn(6).setPreferredWidth(55);
			tabela.getColumnModel().getColumn(7).setPreferredWidth(58);
			tabela.getColumnModel().getColumn(8).setPreferredWidth(116);

			GerenteController gerenteController = new GerenteController();
			
//			PERCORRE O ARRAY DE PEDIDOS DE TRÁS PARA FRENTE
			for (int i = pedidos.size()-1; i >= 0; i--) {
				String[] pedido = pedidos.get(i);
				
//				trocando os ids referentes aos sabores pelo nomes dos respectivos sabores para exibição na tabela da atendente
				PizzaDTO pizzaDTO = new PizzaDTO();
				pizzaDTO.setIds(pedido[4]);
				String sabores = gerenteController.getSabores(pizzaDTO).getSabores();
				
//				adicionando a linha na tabela
				Object[] linha = {pedido[0], pedido[1], pedido[2], pedido[3], sabores, pedido[5], pedido[6], pedido[7], pedido[8]};
				modelo.addRow(linha);
			}
			
			
			tabela.addRowSelectionInterval(0, 0);
		}
		
		JScrollPane scroll = new JScrollPane(tabela);
		scroll.setBounds(10, 170, 600, 405);
		add(scroll);
		
	}
	
	
	

	
	private class KeyListenerCampoCPF implements KeyListener{

		private TelaDeAtendimento telaDeAtendimento;
		
		public KeyListenerCampoCPF(TelaDeAtendimento telaDeAtendimento) {
			this.telaDeAtendimento = telaDeAtendimento;
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
//			System.out.println(e.getKeyChar());
			// TODO Auto-generated method stub

		}

		@Override
		public void keyReleased(KeyEvent e) {

//			retirando a mascara de um JFormattedTextField
//			seuJFormattedTextField.setFormatterFactory(null);

			String cpfDigitado = cpf.getText().replace(".","").replace("-","").trim();

//			System.out.println("CPF: "+cpfDigitado);
			
			AtendenteController atendenteController = new AtendenteController();
			
			ClienteDTO clienteDTO = new ClienteDTO();
			clienteDTO.setCPF(cpfDigitado);
			
			ArrayList<String> cpfs = atendenteController.recuperarCPFDeTodosClientes(clienteDTO).getCpfDeTodosClientes();

			modeloLista.removeAllElements();
			scroll.setVisible(false);
			
			for(String cpf : cpfs) {
				modeloLista.addElement(cpf);
			}

			if(cpfs.size() != 0) {
				scroll.setVisible(true);
			}
		}
	}

	
	private class OuvinteDeFocoCampoCPF implements FocusListener{

		private TelaDeAtendimento telaDeAtendimento;

		public OuvinteDeFocoCampoCPF(TelaDeAtendimento telaDeAtendimento) {
			this.telaDeAtendimento = telaDeAtendimento;
		}

		@Override
		public void focusGained(FocusEvent e) {
				
		}

		@Override
		public void focusLost(FocusEvent e) {
			scroll.setVisible(false);
		}
		
	}
	
	private class OuvinteDeCliqueDaListaDeCPF implements MouseListener{

		private TelaDeAtendimento telaDeAtendimento;
		
		public OuvinteDeCliqueDaListaDeCPF(TelaDeAtendimento telaDeAtendimento) {
			this.telaDeAtendimento = telaDeAtendimento;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
//			 TODO Auto-generated method stub	
			 cpf.setText(lista.getSelectedValue());
//			System.out.println("CPF selecionado: "+lista.getSelectedValue());
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class OuvinteRadioButtons implements ActionListener{
		
		private TelaDeAtendimento telaDeAtendimento;
		
		public OuvinteRadioButtons(TelaDeAtendimento telaDeAtendimento) {
			this.telaDeAtendimento = telaDeAtendimento;
		}

		AtendenteController atendenteController = new AtendenteController();
//		Object[] sabores = atendenteController.recuperarSaboresDePizzas().getSaboresDePizzas().toArray();

		@Override
		public void actionPerformed(ActionEvent e) {
			
//			JLabel a = AdicionadorDeComponentes.adicionarJLabel(telaDeAtendimento, "Sabores que irão compor a pizza:", 650, 250, 310, 20);
//			a.repaint();
			saboresQueIraoComporAPizza.setVisible(true);
			
			int tamanhoDaPizza = tamanho.getSelectedIndex();

			if(sabores.length != 0) {
				String sabor1Selecionado = null;
				String sabor2Selecionado = null;
				String sabor3Selecionado = null;
				
						
				if(e.getActionCommand().equals("Sabor Único")) {
					sabor1.setVisible(true);
					sabor2.setVisible(false);
					sabor3.setVisible(false);
					
					
					if(tamanhoDaPizza == 0) {
						sabor1Selecionado = (String) sabor1.getSelectedItem();
					}else if(tamanhoDaPizza == 1) {
						sabor1Selecionado = (String) sabor1.getSelectedItem();
						sabor2Selecionado = (String) sabor1.getSelectedItem();						
					}else if(tamanhoDaPizza == 2) {
						sabor1Selecionado = (String) sabor1.getSelectedItem();
						sabor2Selecionado = (String) sabor1.getSelectedItem();
						sabor3Selecionado = (String) sabor1.getSelectedItem();			
					}
					
				}else if(e.getActionCommand().equals("Sabores Mistos")) {
					
					if(tamanhoDaPizza == 0) {
						sabor1.setVisible(true);
						sabor2.setVisible(false);
						sabor3.setVisible(false);
						
						sabor1Selecionado = (String) sabor1.getSelectedItem();
					}else if(tamanhoDaPizza == 1) {
						sabor1.setVisible(true);
						sabor2.setVisible(true);
						sabor3.setVisible(false);
						
						sabor1Selecionado = (String) sabor1.getSelectedItem();
						sabor2Selecionado = (String) sabor2.getSelectedItem();
					}else if(tamanhoDaPizza == 2) {
						sabor1.setVisible(true);
						sabor2.setVisible(true);
						sabor3.setVisible(true);
						
						sabor1Selecionado = (String) sabor1.getSelectedItem();
						sabor2Selecionado = (String) sabor2.getSelectedItem();
						sabor3Selecionado = (String) sabor3.getSelectedItem();
					}
				}
				
				PedidoDTO pedidoDTO = new PedidoDTO(sabor1Selecionado, sabor2Selecionado, sabor3Selecionado);

				LabelPreco.setText(Float.toString(atendenteController.calcularPrecoDoPedido(pedidoDTO).getPreco()));
			}else {
				JLabel labelNenhumSabor = AdicionadorDeComponentes.adicionarJLabel(telaDeAtendimento, "Nenhum sabor foi cadastrado!",660, 280, 330, 20);
				labelNenhumSabor.setForeground(Color.RED);
				
			}
		}
	}

	
	private class OuvinteComboBoxes implements ActionListener{

		private TelaDeAtendimento telaDeAtendimento;
		
		public OuvinteComboBoxes(TelaDeAtendimento telaDeAtendimento) {
			this.telaDeAtendimento = telaDeAtendimento;
		}
		
		AtendenteController atendenteController = new AtendenteController();
		
		@Override
		public void actionPerformed(ActionEvent arg0) {

			String sabor1Selecionado = null;
			String sabor2Selecionado = null;
			String sabor3Selecionado = null;
			
			if(sabor1.isVisible() && sabor2.isVisible() && sabor3.isVisible()){
				sabor1Selecionado = (String) sabor1.getSelectedItem();
				sabor2Selecionado = (String) sabor2.getSelectedItem();
				sabor3Selecionado = (String) sabor3.getSelectedItem();
			}else if(sabor1.isVisible() && sabor2.isVisible()) {
				sabor1Selecionado = (String) sabor1.getSelectedItem();
				sabor2Selecionado = (String) sabor2.getSelectedItem();
			}else if(sabor1.isVisible() && tamanho.getSelectedIndex() == 0) {
				sabor1Selecionado = (String) sabor1.getSelectedItem();
			}else if(sabor1.isVisible() && tamanho.getSelectedIndex() == 1) {
				sabor1Selecionado = (String) sabor1.getSelectedItem();
				sabor2Selecionado = (String) sabor1.getSelectedItem();
			}else if(sabor1.isVisible() && tamanho.getSelectedIndex() == 2) {
				sabor1Selecionado = (String) sabor1.getSelectedItem();
				sabor2Selecionado = (String) sabor1.getSelectedItem();
				sabor3Selecionado = (String) sabor1.getSelectedItem();
			}
			
			
			PedidoDTO pedidoDTO = new PedidoDTO(sabor1Selecionado, sabor2Selecionado, sabor3Selecionado);
			
			LabelPreco.setText(Float.toString(atendenteController.calcularPrecoDoPedido(pedidoDTO).getPreco()));
			
		}
		
	}
	
	
	private class OuvinteBotoesTelaDeAtendimento implements ActionListener{

		private TelaDeAtendimento telaDeAtendimento;
		
		public OuvinteBotoesTelaDeAtendimento(TelaDeAtendimento telaDeAtendimento) {
			this.telaDeAtendimento = telaDeAtendimento;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {

			AtendenteController atendenteController = new AtendenteController();
			
			String botao = e.getActionCommand();
			
			if(botao.equals("Finalizar Pedido")) {
				
				try {
					ClienteDTO clienteDTO = new ClienteDTO();
					clienteDTO.setCPF(cpf.getText());
					atendenteController.validarCPF(clienteDTO);
					
					if(saborUnico.isSelected() || saboresMistos.isSelected()) {
						
						
						int qtdFatias = 0;
						float preco = 0;

						if(tamanho.getSelectedIndex() == 0) {
							qtdFatias = 4;
						}else if(tamanho.getSelectedIndex() == 1) {
							qtdFatias = 8;
						}else {
							qtdFatias = 12;
						}
						
						ArrayList<String> saboresQueCompoemAPizza = new ArrayList<>();
						
						PizzaDTO pizzaDTO1 = new PizzaDTO();
						PizzaDTO pizzaDTO2 = new PizzaDTO();
						
//						valor definido por mim (o dono)
						int qtdDeFatiasDeCadaSabor = 4;
						
						
						if(saborUnico.isSelected()) {

							String saborUnicoString = (String)sabor1.getSelectedItem();
							String [] saborUnico = saborUnicoString.split("-");
							saboresQueCompoemAPizza.add(saborUnico[0]);
							pizzaDTO1.setIdDaPizza(Integer.parseInt(saborUnico[0]));
							
							float precoDaFatia = atendenteController.recuperarPrecoDaFatia(pizzaDTO1).getPrecoProFatia();
							
							
							if(qtdFatias == 4) {
								preco += qtdFatias * precoDaFatia;
							}else if(qtdFatias == 8) {
								preco += qtdFatias * precoDaFatia;
							}else if(qtdFatias == 12) {
								preco += qtdFatias * precoDaFatia;
							}
							
						}else if(saboresMistos.isSelected()) {
							if(qtdFatias == 4) {
							
								String saborUnicoString = (String)sabor1.getSelectedItem();
								
								String[] saborUnico = saborUnicoString.split("-");
									
								saboresQueCompoemAPizza.add(saborUnico[0]);
								
								
								pizzaDTO1.setIdDaPizza(Integer.parseInt(saborUnico[0]));
								preco += qtdDeFatiasDeCadaSabor * atendenteController.recuperarPrecoDaFatia(pizzaDTO1).getPrecoProFatia();

							}else if(qtdFatias == 8) {
								
								
								String sabor1String = (String) sabor1.getSelectedItem();
								String sabor2String = (String) sabor2.getSelectedItem();
										
								String[] sabor1 = sabor1String.split("-");
								String[] sabor2 = sabor2String.split("-");
										
								saboresQueCompoemAPizza.add(sabor1[0]);
								saboresQueCompoemAPizza.add(sabor2[0]);
								
								
								pizzaDTO1.setIdDaPizza(Integer.parseInt(sabor1[0]));
								float preco1 = atendenteController.recuperarPrecoDaFatia(pizzaDTO1).getPrecoProFatia();
								
								pizzaDTO2.setIdDaPizza(Integer.parseInt(sabor2[0]));
								float preco2 = atendenteController.recuperarPrecoDaFatia(pizzaDTO2).getPrecoProFatia();	
								
								preco += (qtdDeFatiasDeCadaSabor * preco1) + (qtdDeFatiasDeCadaSabor * preco2);
								
								
							}else if(qtdFatias == 12) {
								
								String sabor1String = (String) sabor1.getSelectedItem();
								String sabor2String = (String) sabor2.getSelectedItem();
								String sabor3String = (String) sabor3.getSelectedItem();
								
								String[] sabor1 = sabor1String.split("-");
								String[] sabor2 = sabor2String.split("-");
								String[] sabor3 = sabor3String.split("-");
								
								saboresQueCompoemAPizza.add(sabor1[0]);
								saboresQueCompoemAPizza.add(sabor2[0]);
								saboresQueCompoemAPizza.add(sabor3[0]);
								
								pizzaDTO1.setIdDaPizza(Integer.parseInt(sabor1[0]));	
								float preco1 = atendenteController.recuperarPrecoDaFatia(pizzaDTO1).getPrecoProFatia();
							
								pizzaDTO2.setIdDaPizza(Integer.parseInt(sabor2[0]));
								float preco2 = atendenteController.recuperarPrecoDaFatia(pizzaDTO2).getPrecoProFatia();	
								
								
								PizzaDTO pizzaDTO3 = new PizzaDTO();
								pizzaDTO3.setIdDaPizza(Integer.parseInt(sabor3[0]));
								float preco3 = atendenteController.recuperarPrecoDaFatia(pizzaDTO3).getPrecoProFatia();
								
								preco += (qtdDeFatiasDeCadaSabor * preco1) + (qtdDeFatiasDeCadaSabor * preco2) + (qtdDeFatiasDeCadaSabor * preco3);
							}
							
						}

						int idDoPedido = atendenteController.recuperarDadosDeTodosPedidos().getPedidos().size() + 1;
						
						atendenteController.adicionarPedido(new PedidoDTO(idDoPedido, cpf.getText(), qtdFatias, saboresQueCompoemAPizza, preco, atendenteController.recuperarEndereco(clienteDTO).getEndereço(), codigoDoAtendenteLogado));
						
		// gambiarra para atualizar a página
						JOptionPane.showMessageDialog(telaDeAtendimento, "Pedido Finalizado!");
						TelaDeAtendimento telaDeAtendimentoAtualizada = new TelaDeAtendimento(codigoDoAtendenteLogado);
						telaDeAtendimentoAtualizada.setLocationRelativeTo(telaDeAtendimento);
						telaDeAtendimento.dispose();
						
					}else {
						JOptionPane.showMessageDialog(telaDeAtendimento, "Escolha o(s) sabor(es) da pizza!", "Nenhum sabor foi selecionado", JOptionPane.ERROR_MESSAGE);
					}
				}catch(CPFInexistenteException er) {
					JOptionPane.showMessageDialog(telaDeAtendimento, er.getMessage(), "CPF inválido", JOptionPane.ERROR_MESSAGE);
				}catch(CampoVazioException er) {
					JOptionPane.showMessageDialog(telaDeAtendimento, "Preencha o campo CPF!", "CPF vazio", JOptionPane.ERROR_MESSAGE);
				}

				
			}else if(botao.equals("Cadastrar Cliente")) {
				TelaDeCadastroDeCliente telaDeCadastroDeCliente = new TelaDeCadastroDeCliente(codigoDoAtendenteLogado, telaDeAtendimento);
				telaDeCadastroDeCliente.setLocationRelativeTo(telaDeAtendimento);
			}else if(botao.equals("Pizzas")) {
				TelaDePizzas telaDePizzas = new TelaDePizzas(codigoDoAtendenteLogado, vimDaTelaDeAtendimento);
				telaDePizzas.setLocationRelativeTo(telaDeAtendimento);
			}
			
		}
		
	}
	

	
	
	
	public static void main(String[] args) {
		new TelaDeAtendimento(23);
//		new TelaDeAtendimento(25);
		
	}



	
}
