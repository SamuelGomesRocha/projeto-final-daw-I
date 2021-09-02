package br.edu.ifg.actions;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.ifg.dao.LanceDAO;
import br.edu.ifg.dao.LeilaoDao;
import br.edu.ifg.entities.Lance;
import br.edu.ifg.entities.Leilao;
import br.edu.ifg.entities.Proponente;
import br.edu.ifg.entities.Status;
import br.edu.ifg.service.LanceService;
import br.edu.ifg.service.LeilaoService;

public class CadastraLance {

	private HttpServletRequest req;
	private HttpServletResponse resp;

	public CadastraLance(HttpServletRequest req, HttpServletResponse resp) {
		this.req = req;
		this.resp = resp;
	}

	public void executa() throws IOException, SQLException {
		HttpSession session = req.getSession();

		Double valorLance = Double.parseDouble(req.getParameter("input-valorLance"));
		Object user = session.getAttribute("usuarioLogado");
		System.out.println("Olha a session: " + user.toString());
		Proponente proponente = new LanceDAO().retornaProponentePeloUser(user.toString());
		System.out.println("Olha o prop: " + proponente.toString());
		String idLeilas = req.getParameter("idLeilao");
		System.out.println("olha o id leilassssss: " + idLeilas);
		Integer idLeilao = Integer.parseInt(idLeilas);
		Leilao leilao = new LeilaoService().retornaLeilaoPeloID(idLeilao);

		new LeilaoService().abreLeilao(leilao);
		Lance lance = new Lance(proponente, valorLance, leilao);

		String msg = "";
		System.out.println("AINDA SEM SENTIDO? "+new LanceService().verificaOrdemDosProponentes(lance));
		if (new LanceService().verificaOrdemDosProponentes(lance)) {
			msg = "<h5>Você não pode propor lances duas vezes seguidas!</h5>";

		} else if (!new LanceService().verificaValorLanceMinimo(lance, leilao)) {
			msg = "<h5>O lance deve ser maior que o lance mínimo</h5>";
			// Thread.sleep(10000);
		} else if (!new LanceService().verificaValorLanceAnterior(lance)) {
			msg = "<h5>O lance deve ser maior que o anterior</h5>";
		} else {
			LanceDAO ld = new LanceDAO();
			ld.cadLance(lance);
		}

		System.out.println("olha essa a: " + new LanceService().verificaOrdemDosProponentes(lance));
		session.setAttribute("mensagem", msg);
		resp.sendRedirect(req.getServletContext().getContextPath() + "/port?action=/newLances&idLeilao=" + idLeilao);
	}

}
