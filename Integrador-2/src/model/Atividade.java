package model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import javax.persistence.Transient;

@Entity
@Table(name="atividade")
public class Atividade implements Comparable<Atividade>,Serializable {
    private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Integer id;
	
	@Column(nullable=false)
	private String descricao;
	
	@Column(nullable=false,name="nivel")
	private String nivel;
	
	 @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	 @JoinTable(name = "evento_has_atividade",
	        joinColumns = @JoinColumn(name = "id_atividade", referencedColumnName = "id"),
	        inverseJoinColumns = @JoinColumn(name = "id_evento", referencedColumnName = "id"))
	private List<Evento> eventos;
	
	@Transient 
	private boolean selecionado = false;
	
	public boolean isSelecionado() {
		return selecionado;
	}
	
	public void setSelecionado(boolean selecionado) {
		this.selecionado = selecionado;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	//fornece o crit�rio de ordena��o para a inteface Comparable
	//muitas classes do java (ex. TreeSet) precisam de um crit�rio de ordena��o
	@Override
	public int compareTo(Atividade outro) {
		return this.descricao.compareTo(outro.getDescricao());
	}

	
}
