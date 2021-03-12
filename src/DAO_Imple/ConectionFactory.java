package DAO_Imple;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectionFactory {

	private static Connection connection = null;

	public static Connection getConnection(){
	
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/livraria";
		String usuario = "root";
		String senha = "root";

		try {
			if (connection == null){
				//Carregar o Driver do .jar
				Class.forName(driver);
				//Abrindo Conexão
				connection = DriverManager.getConnection(url, usuario, senha);
				System.out.println("Conexão realizada com o BD.");
				}
		}catch (ClassNotFoundException e) {
			System.out.println("Não foi possivel carregar o drive "+e.getMessage());
		} catch (SQLException e) {
			System.out.println("Não foi possível criar a conexão"
			+ e.getMessage());
		}
			
		
		return connection;
		
	}

	
	public static void closeConnection() {
		try {
			if(connection != null) {
				connection.close();
				System.out.println("Conexão fechada!");
			}else {
				System.out.println("Não foi possível encerrar a conexao, pois a mesma não foi iniciada!");
			}
			
		}catch(SQLException e) {
			System.out.println("Não foi possível encerrar a conexao "+ e.getMessage());
		}
	}
	
	
}

