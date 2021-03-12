package DAO_Imple;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import DAO_Interfaces.PedidoInterface;
import DTO.ClienteDTO;
import DTO.PedidoDTO;

public class PedidoDAO_Postgre implements PedidoInterface{

	
	@Override
	public void adicionarPedido(PedidoDTO pedidoDTO) {

		String sql = "INSERT INTO pedido (cliente,fatias,sabores,preco,endereco,aberto,pronto,entregue,criacao,idatendente) VALUES(?,?,?,?,?,?,?,?,?,?)";
		
		Connection conexao = Conexao.getConnection();
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);

			preparador.setString(1, pedidoDTO.getCPFcliente());
			preparador.setInt(2, pedidoDTO.getQtdFatias());
			
			
			String sabores = "";
			for (String sabor : pedidoDTO.getSaboresQueCompoemAPizza()) {
				sabores += sabor + ", ";
			}
			
//			retirando os dois �ltimos caracteres (, ) da string sabores
			sabores = sabores.substring(0, sabores.length() - 2);
			
			preparador.setString(3, sabores);

			
			preparador.setFloat(4, pedidoDTO.getPreco());
			preparador.setString(5, pedidoDTO.getEnderecoDoCliente());
			preparador.setBoolean(6, pedidoDTO.isAberto());
			preparador.setBoolean(7, pedidoDTO.isPronto());
			preparador.setBoolean(8, pedidoDTO.isEntregue());
			
			Timestamp criacao = new Timestamp(pedidoDTO.getDataDeCriacao().getTime());
			
			preparador.setTimestamp(9, criacao);
			preparador.setInt(10, pedidoDTO.getCodigoDoAtedente());
			
			preparador.execute();

			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void concluirPedido(PedidoDTO pedidoDTO) {

		String sql = "UPDATE pedido SET pronto=?, idpizzaiolo=?, preparo=? WHERE numero=?";
		
		Connection conexao = Conexao.getConnection();
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			
			preparador.setBoolean(1, true);
			preparador.setInt(2, pedidoDTO.getCodigoDoPizzaiolo());

			Timestamp preparo = new Timestamp(Calendar.getInstance().getTime().getTime());
			preparador.setTimestamp(3, preparo);
			
			preparador.setInt(4, pedidoDTO.getNumeroDoPedido());
			
			preparador.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	
	
	@Override
	public void realizarEntregaDePedido(PedidoDTO pedidoDTO) {
			
		Connection conexao = Conexao.getConnection();
		
		try {

			String sql = "UPDATE pedido SET entregue=?, idmotoboy=?, entrega=? WHERE numero=? ";
		
			PreparedStatement preparador = conexao.prepareStatement(sql);
			
			preparador.setBoolean(1, true);
			
			preparador.setInt(2, pedidoDTO.getCodigoDoMotoboy());
			
			Timestamp entrega = new Timestamp(Calendar.getInstance().getTime().getTime());
			
			preparador.setTimestamp(3, entrega);

			preparador.setInt(4, pedidoDTO.getNumeroDoPedido());
			
			preparador.executeUpdate();


//						********						
//				!!!!	NOVO SQL !!!!!!!!!!
//						********			
			
//			selecionar apenas a colunas sabores!!!
			sql = "SELECT * FROM pedido WHERE numero=?";
			
			preparador = conexao.prepareStatement(sql);
			
			preparador.setInt(1, pedidoDTO.getNumeroDoPedido());
			
			ResultSet resultado = preparador.executeQuery();
			
			
			while(resultado.next()) {
				
				String[] idDeSabores = resultado.getString("sabores").split(", ");
				
//						********						
//				!!!!	NOVO SQL !!!!!!!!!!
//						********			
		
				sql = "UPDATE pizza SET quantidade_vendida=quantidade_vendida+4 WHERE id=?";
//				4 � um valor especificado por mim (o dono)
				
				preparador = conexao.prepareStatement(sql);
				
				for (String idPizza : idDeSabores) {
					
					preparador.setInt(1, Integer.parseInt(idPizza));
					
					preparador.executeUpdate();
					
				}
				
			}
			
//						********			
//				!!!!	NOVO SQL !!!!!!!!!!
			
//						********			
			sql = "UPDATE contabilidade SET vendas=vendas+1, lucro=lucro+"+pedidoDTO.getPreco();
			
			preparador = conexao.prepareStatement(sql);


			preparador.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public PedidoDTO recuperarDadosDeTodosPedidosProntosParaPreparo() {

		ArrayList<String[]> dadosDoPedido = new ArrayList<String[]>();
		
		String sql = "SELECT numero, fatias, sabores FROM pedido WHERE aberto=? AND pronto=?";
		
		Connection conexao = Conexao.getConnection();
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			
			preparador.setBoolean(1, true);
			preparador.setBoolean(2, false);
			
			ResultSet resultado = preparador.executeQuery();
			
			while(resultado.next()){
				String[] linha = new String[4];
				linha[0] = resultado.getString("numero");
				
				
				int fatias = Integer.parseInt(resultado.getString("fatias"));
				
				if(fatias == 4) {
					linha[1] = "Pequeno";
				}else if(fatias == 8) {
					linha[1] = "M�dio";
				}else {
					linha[1] = "Grande";
				}
				
				linha[2] = Integer.toString(fatias);
				linha[3] = resultado.getString("sabores");
				
				dadosDoPedido.add(linha);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		PedidoDTO pedidoDTO = new PedidoDTO();
		pedidoDTO.setDadosDosPedidosProntosParaPreparo(dadosDoPedido);

		return pedidoDTO;
	}

	
	
	
	@Override
	public PedidoDTO recuperarDadosDetodosPedidosProntosParaEntrega() {

		ArrayList<String[]> dadosDosPedidos = new ArrayList<String[]>();
		
//		String sql = "SELECT numero, cliente, fatias, sabores, preco, endereco FROM pedido WHERE pronto=? AND entregue=?";

		String sql = "SELECT numero, cliente, fatias, sabores, preco, endereco FROM pedido WHERE pronto=? AND entregue=? and cliente not in (select cliente from pedido where pronto = false)";
		
		Connection conexao = Conexao.getConnection();
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			
			preparador.setBoolean(1, true);
			preparador.setBoolean(2, false);
			
			ResultSet resultado = preparador.executeQuery();
			
			while(resultado.next()) {
				String[] linha = new String[6];
				linha[0] = resultado.getString("numero");
				linha[1] = resultado.getString("cliente");
				linha[2] = resultado.getString("fatias");
				linha[3] = resultado.getString("sabores");
				linha[4] = resultado.getString("preco");
				linha[5] = resultado.getString("endereco");
				
				dadosDosPedidos.add(linha);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		PedidoDTO pedidoDTO = new PedidoDTO();
		pedidoDTO.setDadosDosPedidosProntosParaEntrega(dadosDosPedidos);

		return pedidoDTO;
		
	}

	
	
	@Override
	public ClienteDTO recuperarDadosDeTodosPedidosDeUmCliente(ClienteDTO clienteDTO) {

		ArrayList<String[]> dadosDosPedidos = new ArrayList<String[]>();
		
		String sql = "SELECT * FROM pedido WHERE cliente=?";
		
		Connection conexao = Conexao.getConnection();
		
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);

			preparador.setString(1, clienteDTO.getCPF());
			
			ResultSet resultado = preparador.executeQuery();

			
			SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			
			while(resultado.next()) {
				String[] linha = new String[10];

				
				linha[0] = resultado.getString("numero");
				linha[1] = resultado.getString("cliente");
				
				int fatias = Integer.parseInt(resultado.getString("fatias"));
				
				if(fatias == 4) {
					linha[2] = "Pequeno";
				}else if(fatias == 8) {
					linha[2] = "M�dio";
				}else {
					linha[2] = "Grande";
				}
				
				linha[3] = Integer.toString(fatias);
				linha[4] = resultado.getString("sabores");
				linha[5] = resultado.getString("preco");
				linha[6] = resultado.getString("endereco");
				
				Timestamp criacao = resultado.getTimestamp("criacao");
				linha[7] = formataData.format(criacao);


				Timestamp preparo = resultado.getTimestamp("preparo");
				if(preparo == null) {
					linha[8] = "N�o foi preparado";
				}else {
					linha[8] = formataData.format(preparo);
				}


				Timestamp entrega = resultado.getTimestamp("entrega");
				if(entrega == null) {
					linha[9] = "N�o foi Entregue";
				}else {
					linha[9] = formataData.format(entrega);
				}
				dadosDosPedidos.add(linha);
			}
					
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		clienteDTO.setDadosDosPedidosDeUmUnicoCliente(dadosDosPedidos);
		return clienteDTO;
		
	}

	@Override
	public PedidoDTO getPedidos() {
		
		String sql = "SELECT numero, cliente, fatias, sabores, preco, pronto, entregue, criacao FROM pedido";
		
		Connection conexao = Conexao.getConnection();

		ArrayList<String[]> dadosDosPedidos = new ArrayList<String[]>();

		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			
			ResultSet resultado = preparador.executeQuery();
			
			while(resultado.next()) {
					String[] linha = new String[9];
				
					linha[0] = resultado.getString("numero");
					
					linha[1] = resultado.getString("cliente");
					
					int fatias = Integer.parseInt(resultado.getString("fatias"));
					
					if(fatias == 4) {
						linha[2] = "Pequeno";
					}else if(fatias == 8) {
						linha[2] = "M�dio";
					}else {
						linha[2] = "Grande";
					}
					
					linha[3] = Integer.toString(fatias);
					
				
					linha[4] = resultado.getString("sabores");
					linha[5] = resultado.getString("preco");
					
					if (resultado.getBoolean("pronto")) {
						linha[6] = "Sim";
					} else {
						linha[6] = "N�o";
					}

					if (resultado.getBoolean("entregue")){
						linha[7] = "Sim";
					} else {
						linha[7] = "N�o";
					}
					
					SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy HH:mm");
					Timestamp criacao = resultado.getTimestamp("criacao");
					linha[8] = formataData.format(criacao);
					
					dadosDosPedidos.add(linha);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		PedidoDTO pedidoDTO = new PedidoDTO();
		pedidoDTO.setPedidos(dadosDosPedidos);
		return pedidoDTO;
	}

	
	
	
	
	

}
