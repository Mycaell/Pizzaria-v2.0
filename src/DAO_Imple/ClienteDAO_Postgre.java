 package DAO_Imple;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO_Interfaces.ClienteInterface;
import DTO.ClienteDTO;
import View_excecoes.CPFInexistenteException;
import View_excecoes.CampoVazioException;
import View_excecoes.ClienteExistenteException;

public class ClienteDAO_Postgre implements ClienteInterface{

	
	@Override
	public void cadastrarCliente(ClienteDTO clienteDTO) throws ClienteExistenteException, CampoVazioException {
	
		Connection conexao = Conexao.getConnection();
		
		String sql = "INSERT INTO cliente(nome,sobrenome,cpf,telefone,bairro,rua,casa,endereco,iddofuncionarioqueRealizouocadastro) VALUES(?,?,?,?,?,?,?,?,?)";
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			
//			preparador = conexao.prepareStatement(sql);
			preparador.setString(1, clienteDTO.getNome());
			preparador.setString(2, clienteDTO.getSobrenome());
			preparador.setString(3, clienteDTO.getCPF());
			preparador.setString(4, clienteDTO.getTelefone());
			preparador.setString(5, clienteDTO.getBairro());
			preparador.setString(6, clienteDTO.getRua());
			preparador.setInt(7, clienteDTO.getNumeroDaCasa());
			preparador.setString(8, clienteDTO.getEndereço());
			preparador.setInt(9, clienteDTO.getFuncionarioQueRealizouOCadastro());
			
			preparador.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
	}

	@Override
	public void excluirCliente(ClienteDTO clienteDTO) {
		String sql = "DELETE FROM cliente WHERE cpf = ?";

		Connection conexao = Conexao.getConnection();
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			
			preparador.setString(1, clienteDTO.getCPF());
			
			preparador.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	
	@Override
	public ClienteDTO recuperarDadosDeTodosOsClientes() {

		String sql = "SELECT nome, cpf, telefone, endereco FROM cliente order by nome asc";
		
		Connection conexao = Conexao.getConnection();
		
		ArrayList<ClienteDTO> dadosDosClientes = new ArrayList<ClienteDTO>();
		
//		ArrayList<String[]> dadosDosClientes = new ArrayList<String[]>();

		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			
			ResultSet resultado = preparador.executeQuery();
			
			while(resultado.next()) {
				
				ClienteDTO cliente = new ClienteDTO();
				cliente.setNome(resultado.getString("nome"));
				cliente.setCPF(resultado.getString("cpf"));
				cliente.setTelefone(resultado.getString("telefone"));
				cliente.setEndereço(resultado.getString("endereco"));
				
				dadosDosClientes.add(cliente);
				
//				String[] linha = new String[4];
//				linha[0] = resultado.getString("nome");
//				linha[1] = resultado.getString("cpf");
//				linha[2] = resultado.getString("telefone");
//				linha[3] = resultado.getString("endereco");
//				
//				dadosDosClientes.add(linha);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ClienteDTO clienteDTO = new ClienteDTO();
		clienteDTO.setDadosDosClientes(dadosDosClientes);
		return clienteDTO;
	
	}

	
	
	
	@Override
	public ClienteDTO validarCPF(ClienteDTO clienteDTO) throws CPFInexistenteException, CampoVazioException {

//		esse teste deve estar em alguma outra camada (MODEL eu acho)
		if(clienteDTO.getCPF().equals("   .   .   -  ")) {
			throw new CampoVazioException();
		}
	
//		selecionar apenas o cpf
		String sql = "SELECT * FROM cliente WHERE cpf=?";
		
		
		Connection conexao = Conexao.getConnection();
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);

			preparador.setString(1, clienteDTO.getCPF());
			
			ResultSet resultado = preparador.executeQuery();
			
			while(resultado.next()) {
				clienteDTO.setCpfValido(true);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		

//		esse teste deve estar em alguma outra camada 
		if(clienteDTO.isCpfValido()) {
			return clienteDTO;
		}
		
		throw new CPFInexistenteException();
		
	}

	
	@Override
	public ClienteDTO getEndereco(ClienteDTO clienteDTO) {

		String sql = "SELECT * FROM cliente WHERE cpf = ?";
		
		Connection conexao = Conexao.getConnection();
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			
			preparador.setString(1, clienteDTO.getCPF());

			ResultSet resultado = preparador.executeQuery();
			
			while(resultado.next()) {
				clienteDTO.setEndereço(resultado.getString("endereco"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return clienteDTO;
		
	}

	@Override
	public ClienteDTO recuperarCPFDeTodosClientes(ClienteDTO clienteDTO) {
		String sql = "SELECT cpf FROM cliente WHERE cpf LIKE '"+clienteDTO.getCPF()+"%' ORDER BY cpf";
	
		Connection conexao = Conexao.getConnection();
		
		ArrayList<String> cpfs = new ArrayList<String>();
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			ResultSet resultado = preparador.executeQuery();
			
			while(resultado.next()) {
				cpfs.add(resultado.getString("cpf"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		clienteDTO.setCpfDeTodosClientes(cpfs);
		return clienteDTO;
	}



}
