package br.edu.ifg.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifg.entities.Lance;
import br.edu.ifg.entities.Leilao;
import br.edu.ifg.entities.Proponente;
import br.edu.ifg.entities.Status;
import br.edu.ifg.service.LeilaoService;

public class LanceDAO extends ConnectionDao {

	private Connection cnx = getConnection();

	private static final String INSERT_LANCE_SQL = "INSERT INTO lance"
			+ "  (id_proponente, valor_lance, id_leilao) VALUES " + " (?, ?, ?);";

	private static final String SELECT_ALL_LANCES_SQL = "SELECT * FROM lance WHERE id_leilao = ?";

	private static final String DELETE_LANCES_SQL = "DELETE FROM lance WHERE cpf = ?";

	public LanceDAO() {
			
		}
	
	public void cadLance(Lance lance) {
		
		try(PreparedStatement ps = cnx.prepareStatement(INSERT_LANCE_SQL)){
			
			ps.setString(1, lance.getProponente().getCpf());
			ps.setDouble(2, lance.getValorLance());
			ps.setInt(3, lance.getLeilao().getIdLeilao());
			ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public List<Lance> listaLances(int idLeilao){
		List<Lance> lances = new ArrayList<Lance>();
		
		try(PreparedStatement ps = cnx.prepareStatement(SELECT_ALL_LANCES_SQL)){
			ps.setInt(1, idLeilao);
			
			ResultSet rs = ps.executeQuery();			
			
			while(rs.next()) {
				Integer idLance = rs.getInt("id_lance");
				String proponente_id = rs.getString("id_proponente");
				Double valorLance = rs.getDouble("valor_lance");
				Integer leilao_id = rs.getInt("id_leilao");
				
				lances.add(new Lance(idLance, new LanceDAO().retornaProponente(proponente_id), valorLance, new LeilaoService().retornaLeilaoPeloID(leilao_id)));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		for(Lance l : lances) {
			System.out.println("Olha o lance aí: "+ " nome - "+l.getProponente().getNome()+" valor - "+l.getValorLance());
		}
	return lances;
	}
	
	
	public Proponente retornaProponente(String proponente_id) {
		Proponente prop = null;
		
		try(PreparedStatement ps = cnx.prepareStatement("SELECT * FROM proponente WHERE cpf = "+"'"+proponente_id+"'")){
			ResultSet rs = ps.executeQuery();
			rs.next();
			String cpf = rs.getString("cpf");
			String nome = rs.getString("nome");
			String email = rs.getString("email");
			String telefone = rs.getString("telefone");
			String password = rs.getString("password");
			String userName = rs.getString("user_name");
			prop = new Proponente(cpf, nome, email, telefone, userName, password);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return prop;
	}
		
	
	public Proponente retornaProponentePeloUser(String user) {
		Proponente prop = null;
		
		try(PreparedStatement ps = cnx.prepareStatement("SELECT * FROM proponente WHERE user_name = "+"'"+user+"'")){
			System.out.println("ó o ps: " + ps.toString());
			
			ResultSet rs = ps.executeQuery();
			rs.next();
			System.out.println("ó o rs: " + rs.getString("cpf"));
			String cpf = rs.getString("cpf");
			String nome = rs.getString("nome");
			String email = rs.getString("email");
			String telefone = rs.getString("telefone");
			String password = rs.getString("password");
			String userName = rs.getString("user_name");
			prop = new Proponente(cpf, nome, email, telefone, userName, password);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return prop;
	}
	


	
	
}