package br.edu.ifg.actions;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifg.dao.LeilaoDao;
import br.edu.ifg.dao.ProponenteDAO;
import br.edu.ifg.entities.Leilao;
import br.edu.ifg.entities.Proponente;
import br.edu.ifg.entities.Status;

public class CadastraUser {
	private HttpServletRequest req;
	private HttpServletResponse resp;
	
	public CadastraUser(HttpServletRequest req, HttpServletResponse resp) {
		this.req = req;
		this.resp = resp;
	}
	
	
	public void executa() throws IOException {
		
		String cpf = req.getParameter("input-cpf");
		String nome = req.getParameter("input-nome");
		String email = req.getParameter("input-email");
		String telefone = req.getParameter("input-telefone");
		String password = req.getParameter("input-password");
		String userName = req.getParameter("input-user_name");
		
		
		Proponente proponente = new Proponente(cpf, nome, email, telefone, userName, password);
		try {
			new ProponenteDAO().cadProponente(proponente);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Cadastro efetuado com sucesso, nome do item: "+proponente.getNome());
		resp.sendRedirect(req.getServletContext().getContextPath() + "/port?action=/login");
	}
}
