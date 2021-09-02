package br.edu.ifg.entities;

public class Lance {

	
	private Integer idLance;
	private Proponente proponente;
	private Double valorLance;
	private Leilao leilao;
	

	public Lance() {
		
	}
	
	
	public Lance(Proponente proponente, Double valorLance) {
		this.proponente = proponente;
		this.valorLance = valorLance;
	}

	public Lance(Proponente proponente, Double valorLance, Leilao leilao) {
		this.proponente = proponente;
		this.valorLance = valorLance;
		this.leilao = leilao;
	}


		
	public Lance(Integer idLance, Proponente proponente, Double valorLance, Leilao leilao) {
		this.idLance = idLance;
		this.proponente = proponente;
		this.valorLance = valorLance;
		this.leilao = leilao;
	}


	
	public Integer getIdLance() {
		return idLance;
	}
	public void setIdLance(Integer idLance) {
		this.idLance = idLance;
	}
	public Proponente getProponente() {
		return proponente;
	}
	public void setProponente(Proponente proponente) {
		this.proponente = proponente;
	}
	public Double getValorLance() {
		return valorLance;
	}
	public void setValorLance(Double valorLance) {
		this.valorLance = valorLance;
	}
	
	public Leilao getLeilao() {
		return leilao;
	}


	public void setLeilao(Leilao leilao) {
		this.leilao = leilao;
	}
}
