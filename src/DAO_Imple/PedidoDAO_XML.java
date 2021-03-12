package DAO_Imple;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JOptionPane;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import DAO_Interfaces.ClienteInterface;
import DAO_Interfaces.ContablidadadeInterface;
import DAO_Interfaces.PedidoInterface;
import DAO_Interfaces.PizzaInterface;
import DTO.ClienteDTO;
import DTO.PedidoDTO;
//
public class PedidoDAO_XML implements PedidoInterface{

//	TIRAR TODA LÓGICA QUE NÃO SEJA DE PERSISTÊNCIA E COLOCAR NO MODEL

//	!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//	OBS 1: É POSSÍVEL QUE ALGUMAS DESSAS LÓGICAS NÃO SE ADEQUEM MAIS AO SISTEMA
//	OBS 2: EXISTEM ALGUNS MÉTODOS QUE AINDA NÃO POSSUEM LÓGICA EM XML
//	OBS 3: CÓDIGOS FORAM COMENTADOS COM O INTUITO DE RETIRAR MSGS DE ERRO (ESSA CLASSE ESTÁ INUTIL)
	
	
	private XStream xstream = new XStream(new DomDriver("ISO-8859-1"));
	private File arquivoPedidos = new File("pedidos.xml");

	private ArrayList<String[]> pedidos = this.recuperarPedidos();
	
	private PizzaInterface pizzaDAO = new PizzaDAO_XML();
//	private ArrayList<String[]> pizzas = pizzaDAO.recuperarPizzas();

	
	private ContablidadadeInterface contabilidadeDAO = new ContabilidadeDAO_XML();
//	private double[] contabilidade = contabilidadeDAO.recuperarContabilidade();
	
	
	private ClienteInterface clienteDAO = new ClienteDAO_XML();
//	private ArrayList<String[]> clientes = clienteDAO.recuperarClientes();
	
	
	@Override
	public void adicionarPedido(PedidoDTO pedidoDTO) {
//		String CPFcliente, int qtdFatias, ArrayList<String> saboresQueCompoemAPizza, float preco, String enderecoDoCliente, int codigoDoAtendente, int codigoDoPizzaiolo, int codigoDoMotoboy

//		pedido[0] = NUMERO DO PEDIDO;
//		pedido[1] = CPF DO CLIENTE;
//		pedido[2] = QTD FATIAS;
//		pedido[3] = ARRAY DE SABORES DA PIZZA;
//		pedido[4] = PREÇO;
//		pedido[5] = ENDEREÇO DO CLIENTE;
//		pedido[6] = ABERTO;
//		pedido[7] = PRONTO;
//		pedido[8] = ENTREGUE;
//		pedido[9] = DATA DE CRIAÇÃO;
//		pedido[10] = DATA DE PREPARO;
//		pedido[11] = DATA DE ENTREGA;
//		pedido[12] = codigoDoAtendente;
//		pedido[13] = codigoDoPizzaiolo;
//		pedido[14] = codigoDoMotoboy;
//		pedido[15] = idDoPedido;
		
//		
		String sabores = "";
		for (String sabor : pedidoDTO.getSaboresQueCompoemAPizza()) {
			sabores += sabor + ", "; 
		}

		
		SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		String[] pedido = {Integer.toString(pedidos.size() + 1), pedidoDTO.getCPFcliente(), Integer.toString(pedidoDTO.getQtdFatias()), sabores, Float.toString(pedidoDTO.getPreco()), pedidoDTO.getEnderecoDoCliente(),
				"true", "false","false", formataData.format(Calendar.getInstance().getTime()),null,null, Integer.toString(pedidoDTO.getCodigoDoAtedente()), null, null, Integer.toString(pedidoDTO.getIdDoPedido())};
		
		pedidos.add(pedido);
		this.salvarPedidos(pedidos);
	}
	
	
	@Override
	public void concluirPedido(PedidoDTO pedidoDTO) {
		for (String[] pedido : pedidos) {
			pedido[13] = Integer.toString(pedidoDTO.getCodigoDoPizzaiolo());
			if(Integer.parseInt(pedido[0]) == pedidoDTO.getNumeroDoPedido()) {
				pedido[7] = "true";
				
				SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				pedido[10] = formataData.format(Calendar.getInstance().getTime());
				
//				pedidos.add(pedido);
				this.salvarPedidos(pedidos);
				JOptionPane.showMessageDialog(null, "Pedido Concluido!");
				break;
			}
		}
	}

	@Override
	public void realizarEntregaDePedido(PedidoDTO pedidoDTO) {

		for (String[] pedido : pedidos) {
			pedido[14] = Integer.toString(pedidoDTO.getCodigoDoMotoboy()); 
			if(Integer.parseInt(pedido[0]) == pedidoDTO.getNumeroDoPedido()) {
				String[] saboresQueCompoemAPizza = pedido[3].split(", ");
//				
//				for (String sabor : saboresQueCompoemAPizza) {
//					for (String[] pizza : pizzas) {
//						if(pizza[0].equals(sabor)) {
//							
//							if(saboresQueCompoemAPizza.length == 1) {
//				
//								int fatias = Integer.parseInt(pedido[2]);
////								
//								if(fatias == 4) {
//									pizza[2] = Integer.toString(Integer.parseInt(pizza[2]) + 4);
//									pizzaDAO.salvarPizzas(pizzas);
//									break;
//								}else if(fatias == 8) {
//									pizza[2] = Integer.toString(Integer.parseInt(pizza[2]) + 8);
//									pizzaDAO.salvarPizzas(pizzas);
//									break;
//								}else {
//									pizza[2] = Integer.toString(Integer.parseInt(pizza[2]) + 12);
//									pizzaDAO.salvarPizzas(pizzas);
//									break;
//								}
//								
//							}else {
//								pizza[2] = Integer.toString(Integer.parseInt(pizza[2]) + 4);
//								pizzaDAO.salvarPizzas(pizzas);
//								break;
//							}
//						}
//					}
					
//				}
				
				
//				contabilidade[0] = vendas
//				contabilidade[1] = lucro
								
//				contabilidade[0] += 1;
//				contabilidade[1] += Double.parseDouble(pedido[4]); 
//				contabilidadeDAO.salvarContabilidade(contabilidade);
				
				
//				LIXO\/
//				cdi.setLucro(cdi.getLucro() + Float.parseFloat(pedido[4]));
//				cdi.setVendas(cdi.getVendas() + 1);
				
				pedido[8] = "true";
				SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				pedido[11] = formataData.format(Calendar.getInstance().getTime());

				this.salvarPedidos(pedidos);
//				p.salvarCentral(cdi, "central");
				JOptionPane.showMessageDialog(null, "Entrega Realizada!");
			}
		}

		
	}


	
	@Override
	public PedidoDTO recuperarDadosDeTodosPedidosProntosParaPreparo() {
		ArrayList<String[]> dadosDoPedido = new ArrayList<String[]>();
		
		for (String[] pedido : pedidos) {
			if(pedido[6].equals("true") && !pedido[7].equals("true")) {
				
				String[] linha = new String[4];

				linha[0] = pedido[0];
			
				if(Integer.parseInt(pedido[2]) == 4) {
					linha[1] = "Pequeno";
				}else if(Integer.parseInt(pedido[2]) == 8) {
					linha[1] = "Médio";
				}else {
					linha[1] = "Grande";
				}
				
				linha[2] = pedido[2];
				linha[3] = pedido[3];
				
				dadosDoPedido.add(linha);
			}
		}
		
		PedidoDTO pedidoDTO = new PedidoDTO();
		pedidoDTO.setDadosDosPedidosProntosParaPreparo(dadosDoPedido);
		return pedidoDTO;
	}

	
	@Override
	public PedidoDTO recuperarDadosDetodosPedidosProntosParaEntrega() {
		ArrayList<String[]> dadosDosPedidos = new ArrayList<String[]>();
		
		for (String[] pedido : pedidos) {
			if(pedido[7].equals("true") && !pedido[8].equals("true")) {
				String[] linha = new String[6];
				linha[0] = pedido[0];
				linha[1] = pedido[1];
				linha[2] = pedido[2];
				linha[3] = pedido[3];
				linha[4] = pedido[4];
				linha[5] = pedido[5];
				
				dadosDosPedidos.add(linha);
			}
		}
		
		PedidoDTO pedidoDTO = new PedidoDTO();
		pedidoDTO.setDadosDosPedidosProntosParaEntrega(dadosDosPedidos);
		
		return pedidoDTO;

	}

	
	@Override
	public ClienteDTO recuperarDadosDeTodosPedidosDeUmCliente(ClienteDTO clienteDTO) {
		ArrayList<String[]> dadosDosPedidos = new ArrayList<String[]>();
 		
//		for (String[] cliente : clientes) {
//			if(cliente[2].equals(clienteDTO.getCPF())) {
//				for (String[] pedido : pedidos) {
//					if(pedido[1].equals(cliente[2])) {
//						String[] linha = new String[10];
//						linha[0] = pedido[0];
//						linha[1] = pedido[1];
//						
//						if(Integer.parseInt(pedido[2]) == 4) {
//							linha[2] = "Pequeno";
//						}else if(Integer.parseInt(pedido[2]) == 8) {
//							linha[2] = "Médio";
//						}else {
//							linha[2] = "Grande";
//						}
//						
//						linha[3] = pedido[2];
//						linha[4] = pedido[3];
//						linha[5] = pedido[4];
//						linha[6] = pedido[5];
//						linha[7] = pedido[9];
//						
//						if(pedido[10]== null) {
//							linha[8] = "Não foi preparado";
//							
//						}else {
//							linha[8] = pedido[10];
//						}
//
//						if(pedido[11] == null) {
//							linha[9] = "Não foi Entregue";
//						}else {
//							linha[9] = pedido[11];
//						}
//						dadosDosPedidos.add(linha);
//					}
//				}
//				break;
//			}
//		}
//		
		clienteDTO.setDadosDosPedidosDeUmUnicoCliente(dadosDosPedidos);
		return clienteDTO;
	
	}

	
	@Override
	public PedidoDTO getPedidos() {
		
		ArrayList<String[]> dadosDosPedidos = new ArrayList<String[]>();
		
		for (String[] pedido : pedidos) {
			String[] linha = new String[9];
			
			linha[0] = pedido[0];
			linha[1] = pedido[1];
			
			if(Integer.parseInt(pedido[2]) == 4) {
				linha[2] = "Pequeno";
			}else if(Integer.parseInt(pedido[2]) == 8) {
				linha[2] = "Médio";
			}else {
				linha[2] = "Grande";
			}
			
			linha[3] = pedido[2];
			
			
			linha[4] = pedido[3];
			linha[5] = pedido[4];
			
			if (pedido[7].equals("true")) {
				linha[6] = "Sim";
			} else {
				linha[6] = "Não";
			}
			
			if (pedido[8].equals("true")) {
				linha[7] = "Sim";
			} else {
				linha[7] = "Não";
			}
			
			
			linha[8] = pedido[9];
			
			dadosDosPedidos.add(linha);

		}
		
		
		PedidoDTO pedidoDTO = new PedidoDTO();
		pedidoDTO.setPedidos(dadosDosPedidos);
		return pedidoDTO;
	}

	
	public void salvarPedidos(ArrayList<String[]> pedidos) {
		String xml = "<?xml version =\"1.0\" encoding=\"ISO-8859-1\" ?>\n";
		xml += xstream.toXML(pedidos);
		
		try {
			if(!arquivoPedidos.exists())
				arquivoPedidos.createNewFile();
			PrintWriter gravar = new PrintWriter(arquivoPedidos);
			gravar.print(xml);
			gravar.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<String[]> recuperarPedidos() {
		try {
			if(arquivoPedidos.exists()) {
				FileInputStream fis = new FileInputStream(arquivoPedidos);
				return (ArrayList<String[]>) xstream.fromXML(fis);
			}else {
				ArrayList<String[]> pedidos = new ArrayList<>();
				this.salvarPedidos(pedidos);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return new ArrayList<String[]>();			
	}


	
}
