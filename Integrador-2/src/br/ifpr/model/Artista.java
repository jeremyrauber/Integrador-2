package br.ifpr.model;


import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="artista")
public class Artista implements Comparable<Artista> {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable=false)
	private String nome;
	
	@ManyToMany
    @JoinTable(name="artista_filme", 
	  joinColumns={@JoinColumn(name="artista_id")}, 
	  inverseJoinColumns={@JoinColumn(name="filme_id")})
	private List<Filme> filmes;
	
	@Column(nullable=false)
	private Date dataNascimento;
	
	@Column(nullable=false)
	private String sexo;
	
	@Column(nullable=false)
	private String nacionalidade;
	
	@Transient // campo não será salvo no BD
	private boolean selecionado = false;
	
	public boolean isSelecionado() {
		return selecionado;
	}
	
	public void setSelecionado(boolean selecionado) {
		this.selecionado = selecionado;
	}
	
	public Artista(){}
	
	public Artista(String _nome, String _sexo){
		this.nome = _nome;
		this.sexo = _sexo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Filme> getFilmes() {
		return filmes;
	}

	public void setFilmes(List<Filme> filmes) {
		this.filmes = filmes;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	@Override
	public int compareTo(Artista o) {
		return this.getNome().compareTo(o.getNome());
	}



}
