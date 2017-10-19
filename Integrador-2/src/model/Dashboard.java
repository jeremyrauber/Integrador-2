package model;

public class Dashboard {
	private Integer numeroFotos;
	private Double mediaFotos;
	private String tempoTotal;
	private Integer participantes;
	private Integer banidos;
	private Integer bairro;
	
	public Dashboard() {
		
	}
	
	public Dashboard(Integer numeroFotos, Double mediaFotos, String tempoTotal, Integer participantes, Integer banidos,
			Integer bairro) {
		super();
		this.numeroFotos = numeroFotos;
		this.mediaFotos = mediaFotos;
		this.tempoTotal = tempoTotal;
		this.participantes = participantes;
		this.banidos = banidos;
		this.bairro = bairro;
	}
	
	public Integer getNumeroFotos() {
		return numeroFotos;
	}
	public void setNumeroFotos(Integer numeroFotos) {
		this.numeroFotos = numeroFotos;
	}
	public Double getMediaFotos() {
		return mediaFotos;
	}
	public void setMediaFotos(Double mediaFotos) {
		this.mediaFotos = mediaFotos;
	}
	public String getTempoTotal() {
		return tempoTotal;
	}
	public void setTempoTotal(String tempoTotal) {
		this.tempoTotal = tempoTotal;
	}
	public Integer getParticipantes() {
		return participantes;
	}
	public void setParticipantes(Integer participantes) {
		this.participantes = participantes;
	}
	public Integer getBanidos() {
		return banidos;
	}
	public void setBanidos(Integer banidos) {
		this.banidos = banidos;
	}
	public Integer getBairro() {
		return bairro;
	}
	public void setBairro(Integer bairro) {
		this.bairro = bairro;
	}
	
}
