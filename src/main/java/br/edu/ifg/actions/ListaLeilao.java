package br.edu.ifg.actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifg.dao.LeilaoDao;
import br.edu.ifg.entities.Leilao;

public class ListaLeilao {

	private HttpServletRequest req;
	private HttpServletResponse resp;
	
	public ListaLeilao(HttpServletRequest req, HttpServletResponse resp) {
		this.req = req;
		this.resp = resp;
	}
	
	public void executa() throws ServletException, IOException {
		String status = req.getParameter("input-statusLeilas");

		System.out.println("Olha o parametro: "+status);
		List<Leilao> leiloes = new ArrayList<Leilao>();
		
		if(status == "") {
			status = null;
		}
		
		if(status != null) {
			leiloes = new LeilaoDao().filtraLeilaoPorStatus(status);
		}else {
			leiloes = new LeilaoDao().listaLeiloes();
		}
		RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/pages/colecao-leiloes.jsp");
		for(Leilao l : leiloes) System.out.println(l.getItem() +" - " +  l.getLanceMinimo() + " " + l.getStatus() + " " + l.getDataExpiracao());
		req.setAttribute("leiloes", leiloes);
		dispatcher.forward(req, resp);
	}
}
