package br.edu.ifg.actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.ifg.dao.LanceDAO;
import br.edu.ifg.entities.Lance;
import br.edu.ifg.entities.Leilao;
import br.edu.ifg.entities.Status;
import br.edu.ifg.service.LanceService;
import br.edu.ifg.service.LeilaoService;

public class NewLances {

	private HttpServletRequest req;
	private HttpServletResponse resp;
	
	public NewLances(HttpServletRequest req, HttpServletResponse resp) {
		this.req = req;
		this.resp = resp;
	}
	
	
	public void executa() throws ServletException, IOException {
		
		String idLeilas = req.getParameter("idLeilao");
		System.out.println("idleilas ao vivo: "+ idLeilas);
		Integer idLeilao = Integer.parseInt(idLeilas);
		Leilao leilao = new LeilaoService().retornaLeilaoPeloID(idLeilao);
		LeilaoService service =  new LeilaoService();
		service.verificaEFinalizaOuEncerraLeilao(leilao);
		List<Lance> lances = new LanceDAO().listaLances(idLeilao);
		
		if(leilao.getStatus().equals(Status.ABERTO) || leilao.getStatus().equals(Status.INATIVO)) {
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/pages/lances.jsp");
			for(Lance l : lances) System.out.println("AQQQQQUIIII: " + l.getIdLance()+" - " +  l.getProponente().getNome());
			req.setAttribute("lances", lances);
			req.setAttribute("idLeilao", idLeilao);
			req.setAttribute("leilaoA", new LeilaoService().retornaLeilaoPeloID(idLeilao));
			dispatcher.forward(req, resp);
		}
		else if(leilao.getStatus().equals(Status.FINALIZADO)) {
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/pages/LeilaoFinalizado.jsp");
			req.setAttribute("lances", lances);
			req.setAttribute("idLeilao", idLeilao);
			req.setAttribute("ganhadorLeilao", new LanceService().retornaMaiorLance(leilao).getProponente().getUserName());
			req.setAttribute("maiorLance", new LanceService().retornaMaiorLance(leilao).getValorLance());
			req.setAttribute("menorLance", new LanceService().retornaMenorLance(leilao).getValorLance());
			req.setAttribute("leilaoA", new LeilaoService().retornaLeilaoPeloID(idLeilao));
			dispatcher.forward(req, resp);
		}
		
		
		resp.sendRedirect(req.getServletContext().getContextPath() + "/port?action=/listar");
	}
	
}
