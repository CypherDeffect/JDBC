package db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class FabricaConexao {
	private static Connection conexao = null;
	
	private FabricaConexao() {}
	
	public static Connection getConexao() {
		try {
			if (conexao != null && !conexao.isClosed()){
				return conexao;
			}
			
			Properties prop = LoadProperties();
			final String url = prop.getProperty("url"); 
			final String usuario = prop.getProperty("usuario");
			final String senha = prop.getProperty("senha");
			
			
			conexao = DriverManager.getConnection(url, usuario, senha);
			return conexao;
			
	        }catch (SQLException e) {
	        	throw new DbException(e.getMessage());
	        }
	}
	
	private static Properties LoadProperties() {
		return null;
	}

	public static void fechaConexao() {
		if (conexao != null) {
			try {
				conexao.close();
				
		  } catch (SQLException e) {
			  throw new DbException(e.getMessage());//
		  }
		}
	}

	private static Properties loadProperties() throws FileNotFoundException, IOException {
		try (FileInputStream fs = new FileInputStream("db.properties")) {
			Properties prop = new Properties();
			prop.load(fs);
			return prop;
		}
	}
}
