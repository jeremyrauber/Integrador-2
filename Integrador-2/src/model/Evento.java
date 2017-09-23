package model;

import java.io.Serializable;
import java.util.ArrayList;
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

@Entity
@Table(name="evento")
public class Evento implements Serializable {
    private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Integer id;
	
	@Column(nullable=false)
	private String nome;
	
	@Column(nullable=false)
	private String descricao;
	
	@Column(nullable=false,name="data_inicio")
	private Date dataInicio;
	
	@Column(nullable=false,name="data_fim")
	private Date dataFim;
	
	@Column(nullable=false,name="palavra_chave")
	private String palavraChave;
	
	@ManyToOne
    @JoinColumn(name="id_mestre", nullable=false)
    private Mestre mestre;
	
	 @ManyToMany(fetch = FetchType.LAZY)
	 @JoinTable(name = "evento_has_atividade",
	        joinColumns = @JoinColumn(name = "id_evento", referencedColumnName = "id"),
	        inverseJoinColumns = @JoinColumn(name = "id_atividade", referencedColumnName = "id"))
	private List<Atividade> atividades;
	 
	@OneToMany(mappedBy = "primaryKey.usuario",
            cascade = CascadeType.ALL)
	private Set<EventoUsuario> eventoUsuario = new HashSet<EventoUsuario>();
	
	public Set<EventoUsuario> getEventoUsuario() {
		return eventoUsuario;
	}

	public void setEventoUsuario(Set<EventoUsuario> eventoUsuario) {
		this.eventoUsuario = eventoUsuario;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public String getPalavraChave() {
		return palavraChave;
	}

	public void setPalavraChave(String palavraChave) {
		this.palavraChave = palavraChave;
	}

	public Mestre getMestre() {
		return mestre;
	}

	public void setMestre(Mestre mestre) {
		this.mestre = mestre;
	}

	public List<Atividade> getAtividades() {
		return atividades;
	}

	public void setAtividades(List<Atividade> atividades) {
		this.atividades = atividades;
	}
	
}
