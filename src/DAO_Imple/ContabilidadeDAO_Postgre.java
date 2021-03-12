package DAO_Imple;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DAO_Interfaces.ContablidadadeInterface;
import DTO.ContabilidadeDTO;

public class ContabilidadeDAO_Postgre implements ContablidadadeInterface{
	

//	!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//	É NECESSÁRIO CRIAR A TUPLA DA CONTABILIDADE NA MÃO DIRETAMENTE PELO PGADMIN, SETANDO SEUS ATRIBUTOS COMO 0
//	!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	
	@Override
	public ContabilidadeDTO getLucro() {
		

		String sql = "Select lucro FROM contabilidade";
			
		Connection conexao = Conexao.getConnection();
	
		ContabilidadeDTO contabilidadeDTO = new ContabilidadeDTO();

		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			
			ResultSet resultado = preparador.executeQuery();

			while(resultado.next()) {
				contabilidadeDTO.setLucro(resultado.getDouble("lucro"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return contabilidadeDTO;
	
	}

	@Override
	public ContabilidadeDTO getQtdVendas() {
	

		String sql = "Select vendas FROM contabilidade";
		
		Connection conexao = Conexao.getConnection();
	
		ContabilidadeDTO contabilidadeDTO = new ContabilidadeDTO();

		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			
			ResultSet resultado = preparador.executeQuery();

			while(resultado.next()) {
				contabilidadeDTO.setVendas(resultado.getInt("vendas"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return contabilidadeDTO;
	
	}

	

}
