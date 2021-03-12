package DAO_Imple;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO_Interfaces.IngredienteInterface;
import DTO.IngredienteDTO;
import DTO.PizzaDTO;

public class IngredienteDAO_Postgre implements IngredienteInterface{

//	private Conexao conexao = new Conexao();
//	private PreparedStatement preparador;
//	private ResultSet resultado;
	
	
	@Override
	public void adicionarIngrediente(IngredienteDTO ingredienteDTO) {
		
		String sql = "INSERT INTO ingrediente (nome, preco) VALUES (?,?)";
		
		Connection conexao = Conexao.getConnection();

		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			
			preparador.setString(1, ingredienteDTO.getNome());
			preparador.setFloat(2, ingredienteDTO.getPreco());
			
			preparador.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		
	}

	@Override
	public void removerIngrediente(IngredienteDTO ingredienteDTO) {
//		a fazer 
	}

	@Override
	public IngredienteDTO recuperarTodosIngredientes() {
		ArrayList<String[]> ingredientes = new ArrayList<String[]>();
		
//		ArrayList<String> ingredientesParaComboBox = new ArrayList<String>();
		
		
		String sql = "SELECT * FROM ingrediente order by nome asc";
		
		Connection conexao = Conexao.getConnection();
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);

			ResultSet resultado = preparador.executeQuery();
			
			while(resultado.next()) {
				String[] linha = new String[3];
				linha[0] = resultado.getString("id");
				linha[1] = resultado.getString("nome");
				linha[2] = resultado.getString("preco");
				
				ingredientes.add(linha);
				
//				ingredientesParaComboBox.add(resultado.getString("id")+"-"+resultado.getString("nome"));
				
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		IngredienteDTO ingredienteDTO = new IngredienteDTO();
		ingredienteDTO.setIngredientes(ingredientes);
//		ingredienteDTO.setIngredientesParaOComboBox(ingredientesParaComboBox);
		
		return ingredienteDTO;
		
	}

	@Override
	public IngredienteDTO recuperarIngredientePorID(IngredienteDTO ingredienteDTO) {


		String sql = "SELECT * FROM ingrediente WHERE id=?";
		
		Connection conexao = Conexao.getConnection();
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);

			preparador.setInt(1, ingredienteDTO.getId());
			
			ResultSet resultado = preparador.executeQuery();
			
			while(resultado.next()) {
				ingredienteDTO.setNome(resultado.getString("nome"));
				ingredienteDTO.setPreco(resultado.getFloat("preco"));
				
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ingredienteDTO;
	}

	
	@Override
	public IngredienteDTO recuperarIngredientesDeUmaPizza(PizzaDTO pizzaDTO) {
		
		ArrayList<String[]> ingredientes = new ArrayList<String[]>();

//		String sql = "SELECT * FROM ingrediente WHERE id = '"+ pizzaDTO.getIdDaPizza()+"' "; 

		String sql = "SELECT id_ingrediente FROM pizza_ingrediente WHERE id_pizza = '"+pizzaDTO.getIdDaPizza()+"'";
		
		Connection conexao = Conexao.getConnection();
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			
			ResultSet resultado = preparador.executeQuery();
			
			while(resultado.next()) {
				sql = "SELECT * FROM ingrediente where id = '"+resultado.getInt("id_ingrediente")+"'";
				
				preparador = conexao.prepareStatement(sql);
				
				ResultSet res = preparador.executeQuery();
				
				while(res.next()) {
					String[] linha = new String[3];
					linha[0] = res.getString("id");
					linha[1] = res.getString("nome");
					linha[2] = res.getString("preco");
					
					ingredientes.add(linha);
				}
				
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		IngredienteDTO ingredienteDTO = new IngredienteDTO();
		ingredienteDTO.setIngredientes(ingredientes);
		
		return ingredienteDTO;
		
		
	}
	
	
	
	
	
}
