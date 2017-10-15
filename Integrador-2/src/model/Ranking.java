package model;

import java.beans.Transient;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

public class Ranking {
	
	static String nome;
	static String login;
	static String bairro;
	static String cidade;
	static String estado;
	static Integer tempoTotal;
	static Integer totalAtividade;
	static String dataNascimento;	
	
	public Ranking() {}
	
	public Ranking(String nome, String login, String bairro, String cidade, String estado, Integer tempoTotal,
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
	public Integer getTempoTotal() {
		return tempoTotal;
	}
	public void setTempoTotal(Integer tempoTotal) {
		this.tempoTotal = tempoTotal;
	}
	
	public Integer getTotalAtividade() {
		return totalAtividade;
	}
	public void setTotalAtividade(Integer totalAtividade) {
		totalAtividade = totalAtividade;
	}
	public String getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
}
