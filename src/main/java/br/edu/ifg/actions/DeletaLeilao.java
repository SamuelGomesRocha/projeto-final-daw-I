package br.edu.ifg.actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifg.dao.LeilaoDao;
import br.edu.ifg.entities.Leilao;

public class DeletaLeilao {

	private HttpServletRequest req;
	private HttpServletResponse resp;
	
	public DeletaLeilao(HttpServletRequest req, HttpServletResponse resp) {
		this.req = req;
		this.resp = resp;
	}
	
	
	public void executa() throws ServletException, IOException {
		String idLeilas = req.getParameter("idLeilao");
		Integer idLeilao = Integer.parseInt(idLeilas);
		System.out.println("Debug id: "+ idLeilao);
		new LeilaoDao().deletaLeilao(idLeilao);
		
		
		List<Leilao> leiloes = new LeilaoDao().listaLeiloes();
		RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/pages/colecao-leiloes.jsp");
		for(Leilao l : leiloes) System.out.println(l.getItem() +" - " +  l.getLanceMinimo() + " " + l.getStatus() + " " + l.getDataExpiracao());
		req.setAttribute("leiloes", leiloes);
		dispatcher.forward(req, resp);		
	}
	
}
