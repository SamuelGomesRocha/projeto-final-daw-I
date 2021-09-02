package br.edu.ifg.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.ifg.actions.CadastraLance;
import br.edu.ifg.actions.CadastraLeilao;
import br.edu.ifg.actions.CadastraUser;
import br.edu.ifg.actions.DeletaLeilao;
import br.edu.ifg.actions.ListaLeilao;
import br.edu.ifg.actions.LoginUser;
import br.edu.ifg.actions.NewLances;
import br.edu.ifg.actions.NewUser;
import br.edu.ifg.actions.NovoLeilao;

@WebServlet(urlPatterns = "/port")
public class ControllerServlet extends HttpServlet{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		
		 HttpSession session = req.getSession();
		if(session.getAttribute("usuarioLogado") == null) {
			if(action.equals("/newUser")) {
				new NewUser(req, resp).executa();
			}else if(action.equals("/cad-user")) {
				new CadastraUser(req, resp).executa();	
			}else {
			try {
				new LoginUser(req, resp).executa();
				
			} catch (ServletException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			return;
		}	
		switch (action) {
		case "/novo" :{
			NovoLeilao novoLeilao = new NovoLeilao(req, resp);
			novoLeilao.executa();
			break;
		}
		case "/newUser": {
			new NewUser(req, resp).executa();
			break;
		}
		case "/deletar": {
			System.out.println("Uai, cheguei aqui");
			DeletaLeilao deletaLeilao = new DeletaLeilao(req, resp);
			deletaLeilao.executa();
			break;
		}
		case "/listar": {
			ListaLeilao listaLeilao = new ListaLeilao(req, resp);
			listaLeilao.executa();
			break;
		}
		case "/cadastrar": {			
			CadastraLeilao cadastraLeilao = new CadastraLeilao(req, resp);
			cadastraLeilao.executa();
			System.out.println("Cadastrei!");
			break;
		}
		case "/login": {
			try {
					session.setAttribute("usuarioLogado", null);
				new LoginUser(req, resp).executa();
			} catch (ServletException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		case "/newLances": {
			new NewLances(req, resp).executa();
			break;
		}
		case "/cad_user":{
			new CadastraUser(req, resp).executa();
			break;
		}
		case "/cadastrarLance": {
			try {
				new CadastraLance(req, resp).executa();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + action);
		}
		
		
	}
	
}
