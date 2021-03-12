package DAO_Imple;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO_Interfaces.PizzaInterface;
import DTO.IngredienteDTO;
import DTO.PizzaDTO;

public class PizzaDAO_Postgre implements PizzaInterface{

	
//	método depreciado (fazer atualização)
	@Override
	public void editarPizza(PizzaDTO pizzaDTO) {
		
		String sql = "UPDATE pizza SET sabor=?, preco_fatia=?, modo_preparo=? WHERE id=?";
		
		Connection conexao = Conexao.getConnection();
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			
			preparador.setString(1, pizzaDTO.getNovoSabor());
			preparador.setFloat(2, pizzaDTO.getNovoPrecoPorFatia());
			preparador.setInt(3, pizzaDTO.getIdDaPizza());
			preparador.setString(4, pizzaDTO.getModoDePreparo());
			
			preparador.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void adicionarPizza(PizzaDTO pizzaDTO,  IngredienteDTO idto) {
		
		String sql = "INSERT INTO pizza (sabor,preco_fatia,quantidade_vendida,id_do_gerente,custo_preparo, modo_preparo) VALUES (?,?,?,?,?,?)";
		
		Connection conexao = Conexao.getConnection();
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			
			preparador.setString(1, pizzaDTO.getSabor());
			preparador.setFloat(2, pizzaDTO.getPrecoProFatia());
			preparador.setInt(3, 0);
			preparador.setInt(4, pizzaDTO.getCodigoDoGerenteQueCadastrou());
			preparador.setFloat(5, pizzaDTO.getCustoPreparo());
			preparador.setString(6, pizzaDTO.getModoDePreparo());

			preparador.execute();

			int id_pizza = getIDPizza();
	
			sql = "INSERT INTO pizza_ingrediente(id_pizza, id_ingrediente) VALUES(?,?)"; 

			preparador = conexao.prepareStatement(sql);
			
			for (String ingredientes : idto.getNomes()) {
				
				String[] id_ingrediente = ingredientes.split("-");
				
				preparador.setInt(1, id_pizza);
				preparador.setInt(2, Integer.parseInt(id_ingrediente[0]));
				preparador.execute();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	
	/**
	 * método resposábel por retornar um inteiro representando o id da última pizza inserida no banco
	 * */
	public int getIDPizza(){
		 
		int num = 0;
		
		String sql = "SELECT * FROM pizza WHERE id = (SELECT MAX(id) FROM pizza)";
		
		Connection conexao = Conexao.getConnection();
		
		try {
		
			PreparedStatement preparador = conexao.prepareStatement(sql);
			
			ResultSet resultado = preparador.executeQuery();

			while(resultado.next()) {
				num = resultado .getInt("id");
			}
		 
		}catch(Exception e) {
			e.printStackTrace();
		}
		return num;
	}
	
	
	@Override
	public void removerPizza(PizzaDTO pizzaDTO) {
		
		String sql = "DELETE FROM pizza WHERE id=?";
		
		Connection conexao = Conexao.getConnection();
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
	
			preparador.setInt(1, pizzaDTO.getIdDaPizza());
			
			preparador.execute();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public PizzaDTO getSaboresDePizzas() {
		
		ArrayList<String> saboresDePizzas = new ArrayList<String>();
		
		String sql = "SELECT * FROM pizza";
		
		Connection conexao = Conexao.getConnection();
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);

			ResultSet resultado = preparador.executeQuery();
			
			while (resultado.next()) {
				saboresDePizzas.add(resultado.getString("id")+"-"+resultado.getString("sabor"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		PizzaDTO pizzaDTO = new PizzaDTO();
		pizzaDTO.setSaboresDePizzas(saboresDePizzas);
		
		return pizzaDTO;
	}

	@Override
	public PizzaDTO getSabor(PizzaDTO pizzaDTO) {
		
//		selecionar apenas a coluna referente ao sabor (nome) da pizza
		String sql = "SELECT * FROM pizza WHERE id=?";
		
		Connection conexao = Conexao.getConnection();
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			
			preparador.setInt(1, pizzaDTO.getIdDaPizza());
			
			ResultSet resultado = preparador.executeQuery();
			
			while(resultado.next()) {
				pizzaDTO.setSabor(resultado.getString("sabor"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return pizzaDTO;
	}

	@Override
	public PizzaDTO getPrecoDaFatia(PizzaDTO pizzaDTO) {

		String sql = "SELECT * FROM pizza WHERE id=?";
		
		Connection conexao = Conexao.getConnection();
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			
			preparador.setInt(1, pizzaDTO.getIdDaPizza());
			
			ResultSet resultado = preparador.executeQuery();
			
			while(resultado.next()) {
				pizzaDTO.setPrecoProFatia(resultado.getFloat("preco_fatia"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return pizzaDTO;
		
	}

	
	@Override
	public PizzaDTO recuperarCustoPreparoDePizza(PizzaDTO pizzaDTO) {
		String sql = "SELECT * from pizza where id = '"+pizzaDTO.getIdDaPizza()+"'";
		
		Connection conexao = Conexao.getConnection();
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			
			ResultSet resultado = preparador.executeQuery();
			
			while (resultado.next()) {
				pizzaDTO.setCustoPreparo(resultado.getFloat("custo_preparo"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return pizzaDTO;
	}
	
	@Override
	public PizzaDTO recuperarDadosDeTodasPizzas() {
		
		ArrayList<String[]> dadosDasPizzas = new ArrayList<String[]>();
		
		String sql = "SELECT * FROM pizza order by sabor asc";
		
		Connection conexao = Conexao.getConnection();
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);

			ResultSet resultado = preparador.executeQuery();
			
			while(resultado.next()) {
				String[] linha = new String[5];
				linha[0] = resultado.getString("sabor");
				linha[1] = resultado.getString("preco_fatia");
				linha[2] = resultado.getString("quantidade_vendida");
				linha[3] = resultado.getString("id");
				linha[4] = resultado.getString("modo_preparo");
				
				dadosDasPizzas.add(linha);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		PizzaDTO pizzaDTO = new PizzaDTO();
		pizzaDTO.setDadosDasPizzas(dadosDasPizzas);
		
		return pizzaDTO;
		
	}


}
