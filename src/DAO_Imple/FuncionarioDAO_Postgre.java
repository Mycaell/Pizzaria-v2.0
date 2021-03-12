package DAO_Imple;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO_Interfaces.FuncionarioInterface;
import DTO.FuncionarioDTO;
import View_excecoes.FuncionarioExistenteException;
import View_excecoes.FuncionarioNaoExistenteException;

public class FuncionarioDAO_Postgre implements FuncionarioInterface{
	

	@Override
	public void cadastrarFuncionario(FuncionarioDTO funcionarioDTO) throws FuncionarioExistenteException {
		
		String sql = "INSERT INTO funcionario(nome,sobrenome,sexo,nascimento,endereço,telefone,cpf,email,senha,cargo,IDDoFuncionarioQueRealizouOCadastro) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
		
		Connection conexao = Conexao.getConnection();
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			
			preparador.setString(1, funcionarioDTO.getNome());
			preparador.setString(2, funcionarioDTO.getSobrenome());
			preparador.setString(3, Character.toString(funcionarioDTO.getSexo()));
			
			Date dataSQL = new Date(funcionarioDTO.getDataDeNascimento().getTime());
			preparador.setDate(4, dataSQL);
			
			preparador.setString(5, funcionarioDTO.getEndereço());
			preparador.setString(6, funcionarioDTO.getTelefone());
			preparador.setString(7, funcionarioDTO.getCPF());
			preparador.setString(8, funcionarioDTO.getEmail());
			preparador.setString(9, funcionarioDTO.getSenha());
			preparador.setString(10, funcionarioDTO.getCargo());
			preparador.setInt(11, funcionarioDTO.getCodigoDoFuncionarioQueRealizouOCadastro());

			preparador.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void excluirFuncionario(FuncionarioDTO funcionarioDTO) {

		String sql = "DELETE FROM funcionario WHERE id=?";
		
		Connection conexao = Conexao.getConnection();
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			
			preparador.setInt(1, funcionarioDTO.getCodigo());
			
			preparador.execute();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	
	@Override
	public FuncionarioDTO recuperarDadosDeTodosFuncionaros() {
		

		String sql = "SELECT * FROM funcionario order by nome asc";
		
		Connection conexao = Conexao.getConnection();
		
		ArrayList<String[]> dadosDosFuncionarios = new ArrayList<String[]>();
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			
			ResultSet resultado = preparador.executeQuery();
			
			while(resultado.next()) {
				String[] linha = new String[4];
				linha[0] = resultado.getString("nome");
				linha[1] = Integer.toString(resultado.getInt("id"));
				linha[2] = resultado.getString("cargo");
				linha[3] = resultado.getString("telefone");

				dadosDosFuncionarios.add(linha);
			}
			
		}  catch (SQLException e) {
			e.printStackTrace();
		}

		FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
		funcionarioDTO.setDadosDosFuncionarios(dadosDosFuncionarios);
		return funcionarioDTO;
	
	}
	
	
	@Override
	public FuncionarioDTO recuperarFuncionario(FuncionarioDTO funcionarioDTO) throws FuncionarioNaoExistenteException {

		String sql = "SELECT * FROM funcionario WHERE email=? AND senha=?";
	
		Connection conexao = Conexao.getConnection();
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);

			preparador.setString(1, funcionarioDTO.getEmail());
			preparador.setString(2, funcionarioDTO.getSenha());
			
			ResultSet resultado = preparador.executeQuery();
		
			while(resultado.next()) {
//		possivelmente nao é necessario o cargo
				funcionarioDTO.setCargo(resultado.getString("cargo"));
				funcionarioDTO.setCodigo(resultado.getInt("id"));
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return funcionarioDTO;
	
	}

	@Override
	public FuncionarioDTO recuperarFuncionarioPeloID(FuncionarioDTO funcionarioDTO) {

		String sql = "SELECT * FROM funcionario WHERE id=?";
		
		Connection conexao = Conexao.getConnection();
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);

			preparador.setInt(1, funcionarioDTO.getCodigo());
			
			ResultSet resultado = preparador.executeQuery();
		
			while(resultado.next()) {

				funcionarioDTO.setNome(resultado.getString("nome"));
				funcionarioDTO.setSobrenome(resultado.getString("sobrenome"));
				
				String sexo = resultado.getString("sexo");
				char s = sexo.charAt(0);
				funcionarioDTO.setSexo(s);
				
				funcionarioDTO.setEndereço(resultado.getString("endereço"));
				
				funcionarioDTO.setDataDeNascimento(resultado.getDate("nascimento"));
				
				
				funcionarioDTO.setTelefone(resultado.getString("telefone"));
				funcionarioDTO.setCPF(resultado.getString("cpf"));
				funcionarioDTO.setEmail(resultado.getString("email"));
				funcionarioDTO.setCargo(resultado.getString("cargo"));
				
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return funcionarioDTO;
	}
	
	
	
//	possivelmente inutil
	@Override
	public FuncionarioDTO getCodigo(FuncionarioDTO funcionarioDTO) {


		String sql = "SELECT * FROM funcionario WHERE email=?";
	
		Connection conexao = Conexao.getConnection();
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			
			preparador.setString(1, funcionarioDTO.getEmail());
			
			ResultSet resultado = preparador.executeQuery();
			
			while(resultado.next()) {
				funcionarioDTO.setCodigo(resultado.getInt("id"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return funcionarioDTO;
	}

	@Override
	public FuncionarioDTO getCargo(FuncionarioDTO funcionarioDTO) {
		

		String sql = "SELECT * FROM funcionario WHERE id=?";
		
		Connection conexao = Conexao.getConnection();
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			
			preparador.setInt(1, funcionarioDTO.getCodigo());
			
			ResultSet resultado = preparador.executeQuery();
			
			while(resultado.next()) {
				funcionarioDTO.setCargo(resultado.getString("cargo"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		
		return funcionarioDTO;
	}

	@Override
	public FuncionarioDTO getNome(FuncionarioDTO funcionarioDTO) {
		

		String sql = "SELECT * FROM funcionario WHERE id=?";
		
		Connection conexao = Conexao.getConnection();
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			
			preparador.setInt(1, funcionarioDTO.getCodigo());
			
			ResultSet resultado = preparador.executeQuery();
			
			while(resultado.next()) {
				funcionarioDTO.setNome(resultado.getString("nome"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return funcionarioDTO;
	}



	
	
	
	
	
	
	
	
	
	
	
	
}
