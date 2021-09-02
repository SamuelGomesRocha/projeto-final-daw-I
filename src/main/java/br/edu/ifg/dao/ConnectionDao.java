package br.edu.ifg.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDao {

	private String jdbcURL = "jdbc:mysql://localhost:3306/daw_i_leilao?useTimezone=true&serverTimezone=GMT";
	private String jdbcUsername = "root";
	private String jdbcPassword = "alohomora";
	private String jdbcDriver = "com.mysql.cj.jdbc.Driver";
	
	protected Connection getConnection() {
		Connection cnx = null;
		
		try {
			Class.forName(jdbcDriver);
			cnx = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return cnx;
	}
	
}
