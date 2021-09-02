package br.edu.ifg.actions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NovoLeilao {

	private HttpServletRequest req;
	private HttpServletResponse resp;
	
	public NovoLeilao(HttpServletRequest req, HttpServletResponse resp) {
		this.req = req;
		this.resp = resp;
	}
	
	
	public void executa() throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/pages/cadastro-leilao.jsp").forward(req, resp);
	}
	
}
