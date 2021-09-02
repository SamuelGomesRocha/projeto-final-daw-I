package br.edu.ifg.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;

import br.edu.ifg.controller.EnviaEmailAoGanhador;
import br.edu.ifg.dao.ConnectionDao;
import br.edu.ifg.dao.LeilaoDao;
import br.edu.ifg.entities.Leilao;
import br.edu.ifg.entities.Proponente;
import br.edu.ifg.entities.Status;

public class LeilaoService extends ConnectionDao{

	private Connection cnx = getConnection();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	
	public void abreLeilao(Leilao leilao) {
		leilao.setStatus(Status.ABERTO);
		new LeilaoDao().updateStatusLeilao(leilao);
	}
	
	public void finalizaLeilao(Leilao leilao) {
		leilao.setStatus(Status.FINALIZADO);
		Proponente proponenteGanhador = new LanceService().retornaMaiorLance(leilao).getProponente();
		new EnviaEmailAoGanhador(proponenteGanhador.getEmail(), leilao.getItem(), proponenteGanhador.getNome()).executa();
		new LeilaoDao().updateStatusLeilao(leilao);
	}
	
	public void expiraLeilao(Leilao leilao) {
		leilao.setStatus(Status.EXPIRADO);
		new LeilaoDao().updateStatusLeilao(leilao);
	}
	
	
	public Leilao retornaLeilaoPeloID(int idLeilao) {
		Leilao leilao = null;
		
		try(PreparedStatement ps = cnx.prepareStatement("SELECT * FROM leilao WHERE idLeilao = "+ idLeilao)){
		
			ResultSet rs = ps.executeQuery();
			rs.next();
			Double lanceMinimo = rs.getDouble("lanceMinimo");
			Date dataExpiracao = rs.getDate("dataExpiracao");
			String status = rs.getString("status");
			String item = rs.getString("item");
			
			leilao = new Leilao(idLeilao, item, lanceMinimo, Status.valueOf(status), dataExpiracao);
			System.out.println("aaaaaaaaaa"+leilao.getItem() +leilao.getItem()+leilao.getLanceMinimo()+leilao.getStatus());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return leilao;
	}
	

	public void verificaEFinalizaOuEncerraLeilao(Leilao leilao) {
		String format = sdf.format(new java.util.Date());
		System.out.println("SQLDATE: "+leilao.getDataExpiracao().toString()+" DATE SDF"+ format);
		String dataSQL = leilao.getDataExpiracao().toString()+" 00:00:00";
		
		long atualMillis = LocalDateTime.parse(
				format.replace(" ", "T")
				.replace("/", "-")
				).atZone(
						ZoneId.of( "America/Sao_Paulo" )
				).toInstant().toEpochMilli();
		
		long sqlMillis = LocalDateTime.parse(
				dataSQL.replace(" ", "T")
				.replace("/", "-")
				).atZone(
						ZoneId.of( "America/Sao_Paulo" )
				).toInstant().toEpochMilli();
		
		if(atualMillis >= sqlMillis) {
			if(leilao.getStatus() == Status.ABERTO) finalizaLeilao(leilao);
			else if(leilao.getStatus() == Status.INATIVO) expiraLeilao(leilao);
		}
	}
	
}
