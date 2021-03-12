package DAO_Imple;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

	private volatile static Connection connection;

	private Conexao() {};
	
	public static Connection getConnection(){

		if(connection == null) {
			synchronized(Conexao.class) {
				if(connection == null) {
					String driver = "org.postgresql.Driver";
					String url = "jdbc:postgresql://localhost:5432/Pizzaria";
					String usuario = "postgres";
					String senha = "cramunhao";
					
					try {
						Class.forName(driver);
						connection = DriverManager.getConnection(url, usuario, senha);
//						System.out.println("Conex�o realizada com o BD.");	
					}catch (ClassNotFoundException e) {
						System.out.println("N�o foi possivel carregar o drive "+e.getMessage());
					} catch (SQLException e) {
						System.out.println("N�o foi poss�vel criar a conex�o "+e.getMessage());
					}
				}
			}
		}
		
		return connection;
		
	}

	
	
	
}

