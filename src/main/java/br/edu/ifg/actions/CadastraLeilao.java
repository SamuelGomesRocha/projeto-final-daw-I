	package br.edu.ifg.actions;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifg.dao.LeilaoDao;
import br.edu.ifg.entities.Leilao;
import br.edu.ifg.entities.Status;

public class CadastraLeilao {

	private HttpServletRequest req;
	private HttpServletResponse resp;
	
	public CadastraLeilao(HttpServletRequest req, HttpServletResponse resp) {
		this.req = req;
		this.resp = resp;
	}
	
	
	public void executa() throws IOException {
		String item = req.getParameter("input-item");
		String valorMinimo = req.getParameter("input-lanceMinimo");
		String dataExpiracao = req.getParameter("input-dataExpiracao");
		Date dateE = Date.valueOf(dataExpiracao);
		Leilao leilao = new Leilao(item, Double.parseDouble(valorMinimo), Status.INATIVO, dateE); 
		
		LeilaoDao ld = new LeilaoDao();
		
		try {
			ld.salvaLeilao(leilao);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Cadastro efetuado com sucesso, nome do item: "+leilao.getItem());
		System.out.println("Value of: "+leilao.getStatus()+"is: "+ leilao.getStatus().ordinal());
		resp.sendRedirect(req.getServletContext().getContextPath() + "/port?action=/novo");
	}
}
