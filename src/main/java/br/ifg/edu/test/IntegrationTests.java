package br.ifg.edu.test;

import org.junit.Before;
import org.junit.Test;

import br.edu.ifg.dao.LanceDAO;
import br.edu.ifg.dao.LeilaoDao;
import br.edu.ifg.dao.ProponenteDAO;
import br.edu.ifg.entities.Leilao;

public class IntegrationTests {

	private LeilaoDao leilaoRepositorio;
	private LanceDAO lanceRepositorio;
	private ProponenteDAO proponenteRepositorio;
	
	
	@Before
	private void init() {
		leilaoRepositorio = new LeilaoDao();
		lanceRepositorio =  new LanceDAO();
		proponenteRepositorio = new ProponenteDAO();
	}
	
	
	@Test
	public void deveCadastrarUmLeilao() {
		
		Leilao leilao = new Leilao();
		
		
	}
	
	
	
	
	
	
	
	
}
