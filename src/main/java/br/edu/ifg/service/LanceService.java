package br.edu.ifg.service;

import java.sql.Connection;
import java.util.List;

import br.edu.ifg.dao.ConnectionDao;
import br.edu.ifg.dao.LanceDAO;
import br.edu.ifg.dao.LeilaoDao;
import br.edu.ifg.entities.Lance;
import br.edu.ifg.entities.Leilao;

public class LanceService extends ConnectionDao{

	Connection cnx = getConnection();
	
	
	public boolean verificaValorLanceAnterior(Lance lance) {
		boolean status = false;
		int count = 0;
		for(Lance l : new LanceDAO().listaLances(lance.getLeilao().getIdLeilao())) {
			if(l != lance) count++;	
			else break;
		}
		if(count==0) status = true;
		else if(new LanceDAO().listaLances(lance.getLeilao().getIdLeilao()).get(count-1).getValorLance() < lance.getValorLance()) status = true;
		return status;
	}
	
	
	public boolean verificaValorLanceMinimo(Lance lance, Leilao leilao) {
		boolean status = false;
		Leilao l = leilao;
			if(l.getLanceMinimo() < lance.getValorLance()) status = true;	
		return status;
	}
	
	
	
	public boolean verificaOrdemDosProponentes(Lance lance) {
		boolean status = false;
		int count = 0;
		
		for(Lance l : new LanceDAO().listaLances(lance.getLeilao().getIdLeilao())) {
			count++;
		}
		
		System.out.println("Olha O COUNTTTTTTTTT: "+ count);
	
		if(count == 0) status = false;
		else if(new LanceDAO().listaLances(lance.getLeilao().getIdLeilao()).get(count-1).getProponente().getUserName().equals(lance.getProponente().getUserName())) status = true;
		System.out.println("VÊ SE FZ SENTIDO PARA VOCÊ: "+status);
		
		return status;
	}
	
	
	
	
	
	
	
	public Lance retornaMaiorLance(Leilao leilao) {
		Lance maiorLance = null;
		int count = 0;
			for(Lance l : new LanceDAO().listaLances(leilao.getIdLeilao())) {
				count++;
			}
			
		if(count == 0) return null;	
		else maiorLance = new LanceDAO().listaLances(leilao.getIdLeilao()).get(count-1);	
		return maiorLance;
	}
	
	
	public Lance retornaMenorLance(Leilao leilao) {
		return new LanceDAO().listaLances(leilao.getIdLeilao()).get(0);
	}
	
}
