package model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="filme")
public class Filme implements Comparable<Filme>{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable=false)
	private String titulo;
	
	@ManyToMany
    @JoinTable(name="filme_categoria", 
	  joinColumns={@JoinColumn(name="filme_id")}, 
	  inverseJoinColumns={@JoinColumn(name="categoria_id")})
	private List<Categoria> categorias;
	
	@Column(nullable=true)
	private String descricao;
	
	@Column(nullable=false)
	private Integer ano;
	
	@Column(nullable=false)
	private Double preco;
	
	@Column
	private String url;
	
	@Column
	private String sublink;
	
	@Column
	private Double nota=0d; // 0 - 10
	
	@ManyToMany(mappedBy="filmes")
	private List<Artista> artistas;
	
	@Column
	private Integer likes=0;
	
	@Column
	private Integer dislikes=0;
	
	@ManyToOne(fetch=FetchType.EAGER)  //LAZY TAVA DANDO PAU
	@JoinColumn(name="estudio_id")
	private Estudio estudio;
	
	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="filme_id")
	private List<Venda> vendas;
	
	
	public Integer getLikes() {
		return likes;
	}

	public void setLikes(Integer likes) {
		this.likes = likes;
	}

	public Integer getDislikes() {
		return dislikes;
	}

	public void setDislikes(Integer dislikes) {
		this.dislikes = dislikes;
	}

	public List<Venda> getVenda() {
		return vendas;
	}

	public void setVenda(List<Venda> vendas) {
		this.vendas = vendas;
	}

	
	
	public List<Artista> getArtistas() {
		return artistas;
	}

	public void setArtistas(List<Artista> artistas) {
		this.artistas = artistas;
	}
	
	public Filme(){}
	
	public Filme(String _titulo, Integer _ano){
		this.titulo = _titulo;
		this.ano = _ano;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public List<Categoria> getCategorias() {
		return categorias;
	}
	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Integer getAno() {
		return ano;
	}
	public void setAno(Integer ano) {
		this.ano = ano;
	}
	public Double getNota() {
		return nota;
	}
	public void setNota(Double nota) {
		this.nota = nota;
	}

	@Override
	public int compareTo(Filme outro) {
		return this.getTitulo().compareTo(outro.getTitulo());
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}


	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Estudio getEstudio() {
		return estudio;
	}

	public void setEstudio(Estudio estudio) {
		this.estudio = estudio;
	}

	public String getSublink() {
		return sublink;
	}

	public void setSublink(String sublink) {
		this.sublink = sublink;
	}

	public List<Venda> getVendas() {
		return vendas;
	}

	public void setVendas(List<Venda> vendas) {
		this.vendas = vendas;
	}

}
