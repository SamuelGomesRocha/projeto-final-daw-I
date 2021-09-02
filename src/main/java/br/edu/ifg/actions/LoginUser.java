package br.edu.ifg.actions;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.ifg.dao.ProponenteDAO;

public class LoginUser {
	
	private HttpServletRequest req;
	private HttpServletResponse resp;
	
	public LoginUser(HttpServletRequest req, HttpServletResponse resp) {
		this.req = req;
		this.resp = resp;
	}
	
	public void executa() throws ServletException, IOException, SQLException {
		String user = req.getParameter("input-user");
		String senha = req.getParameter("input-senha");
		
		if(user != null && (new ProponenteDAO().verificaLogin(user, senha))) {
			HttpSession session = req.getSession();
			session.setAttribute("usuarioLogado", user);
			resp.sendRedirect(req.getServletContext().getContextPath() + "/port?action=/listar");
			return;
		}
		req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);
	}
	
	
}
