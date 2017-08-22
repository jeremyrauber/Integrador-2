package br.ifpr.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;


@Entity
@Table(name="categoria")
public class Categoria implements Comparable<Categoria>{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String nome;
		
	@ManyToMany(mappedBy="categorias")
	private List<Filme> filmes;
	
	@Transient //campo nao ser� salvo no BD
	private boolean selecionado = false;
	
	public boolean isSelecionado() {
		return selecionado;
	}
	public void setSelecionado(boolean selecionado) {
		this.selecionado = selecionado;
	}
	public Categoria(){}
	public Categoria(String _nome){
		nome = _nome;
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
	
	//fornece o crit�rio de ordena��o para a inteface Comparable
	//muitas classes do java (ex. TreeSet) precisam de um crit�rio de ordena��o
	@Override
	public int compareTo(Categoria outro) {
		return this.nome.compareTo(outro.getNome());
	}
		
}
