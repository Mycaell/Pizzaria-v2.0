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
				//Abrindo Conex�o
				connection = DriverManager.getConnection(url, usuario, senha);
				System.out.println("Conex�o realizada com o BD.");
				}
		}catch (ClassNotFoundException e) {
			System.out.println("N�o foi possivel carregar o drive "+e.getMessage());
		} catch (SQLException e) {
			System.out.println("N�o foi poss�vel criar a conex�o"
			+ e.getMessage());
		}
			
		
		return connection;
		
	}

	
	public static void closeConnection() {
		try {
			if(connection != null) {
				connection.close();
				System.out.println("Conex�o fechada!");
			}else {
				System.out.println("N�o foi poss�vel encerrar a conexao, pois a mesma n�o foi iniciada!");
			}
			
		}catch(SQLException e) {
			System.out.println("N�o foi poss�vel encerrar a conexao "+ e.getMessage());
		}
	}
	
	
}

