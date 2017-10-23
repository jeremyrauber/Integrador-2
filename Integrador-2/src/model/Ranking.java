package model;

public class Ranking {
	
	private String nome;
	private String login;
	private String bairro;
	private String cidade;
	private String estado;
	private String tempoTotal;
	private Integer totalAtividade;
	private String dataNascimento;	
	private Integer banido;
	
	public Ranking() {}
	
	public Ranking(String nome, String login, String bairro, String cidade, String estado, String tempoTotal,
			Integer totalAtividade, String dataNascimento) {
		super();
		this.nome = nome;
		this.login = login;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.tempoTotal = tempoTotal;
		this.totalAtividade = totalAtividade;
		this.dataNascimento = dataNascimento;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getTempoTotal() {
		return tempoTotal;
	}
	public void setTempoTotal(String tempoTotal) {
		this.tempoTotal = tempoTotal;
	}
	
	public Integer getTotalAtividade() {
		return totalAtividade;
	}
	public void setTotalAtividade(Integer totalAtividade) {
		this.totalAtividade = totalAtividade;
	}
	public String getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Integer getBanido() {
		return banido;
	}

	public void setBanido(Integer banido) {
		this.banido = banido;
	}
 

}
