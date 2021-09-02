package br.edu.ifg.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifg.entities.Leilao;
import br.edu.ifg.entities.Proponente;
import br.edu.ifg.entities.Status;

public class ProponenteDAO extends ConnectionDao {

	private Connection cnx = getConnection();

	private static final String INSERT_PROPONENTE_SQL = "INSERT INTO proponente"
			+ "  (cpf, nome, email, telefone, password, user_name) VALUES " + " (?, ?, ?, ?, ?, ?);";

	private static final String SELECT_ALL_PROPONENTES_SQL = "SELECT * FROM proponente";

	private static final String DELETE_PROPONENTES_SQL = "DELETE FROM proponente WHERE cpf = ?";

	public ProponenteDAO() {
			
		}
	
	public void cadProponente(Proponente proponente) throws SQLException {
		System.out.println(INSERT_PROPONENTE_SQL);

		
		
		try(PreparedStatement ps = cnx.prepareStatement(INSERT_PROPONENTE_SQL)){
			
			ps.setString(1, proponente.getCpf());
			ps.setString(2, proponente.getNome());
			ps.setString(3, proponente.getEmail());
			ps.setString(4, proponente.getTelefone());
			ps.setString(5, proponente.getPassword());
			ps.setString(6, proponente.getUserName());
			
			ps.execute();
			ps.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}		
	}
	
	
	public List<Proponente> listaProponentes() throws SQLException{
		List<Proponente> proponentes = new ArrayList<Proponente>();
		
		try(PreparedStatement ps = cnx.prepareStatement(SELECT_ALL_PROPONENTES_SQL)){
						
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				String cpf = rs.getString("cpf");
				String nome = rs.getString("nome");
				String email = rs.getString("email");
				String telefone = rs.getString("telefone");
				String password = rs.getString("password");
				String userName = rs.getString("user_name");
				proponentes.add(new Proponente(cpf, nome, email, telefone, userName, password));
			}	
		}
		return proponentes;
	}
		
	
	public Boolean verificaLogin(String userName, String password) throws SQLException {
		boolean status = false;
		try(PreparedStatement ps = cnx.prepareStatement("SELECT * FROM proponente WHERE user_name = ? AND password = ?")){
			
			ps.setString(1, userName);
			ps.setString(2, password);
				
			System.out.println(ps);
			ResultSet rs = ps.executeQuery();
			status = rs.next();
			System.out.println(status);
			
			}
		return status;	
	}
	
	
}
