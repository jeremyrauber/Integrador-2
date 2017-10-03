package model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
@Table(name = "evento", catalog = "projeto")
public class Evento implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Mestre mestre;
	private String nome;
	private Date dataInicio;
	private Date dataFim;
	private String descricao;
	private String palavraChave;
	private Set<Atividade> atividades = new HashSet<Atividade>(0);
	private Set<UsuarioAtividade> usuarioAtividades = new HashSet<UsuarioAtividade>(0);
	private Set<EventoUsuario> eventoUsuarios = new HashSet<EventoUsuario>(0);

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_mestre", nullable = false)
	public Mestre getMestre() {
		return this.mestre;
	}

	public void setMestre(Mestre mestre) {
		this.mestre = mestre;
	}

	@Column(name = "nome", nullable = false, length = 45)
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_inicio", nullable = false, length = 19)
	public Date getDataInicio() {
		return this.dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_fim", nullable = false, length = 19)
	public Date getDataFim() {
		return this.dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	@Column(name = "descricao")
	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Column(name = "palavra_chave", length = 45)
	public String getPalavraChave() {
		return this.palavraChave;
	}

	public void setPalavraChave(String palavraChave) {
		this.palavraChave = palavraChave;
	}
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "evento_has_atividade", catalog = "projeto", joinColumns = {
			@JoinColumn(name = "id_evento", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "id_atividade", nullable = false, updatable = false) })
	public Set<Atividade> getAtividades() {
		return this.atividades;
	}

	public void setAtividades(Set<Atividade> atividades) {
		this.atividades = atividades;
	}	

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "evento")
	public Set<UsuarioAtividade> getUsuarioAtividades() {
		return this.usuarioAtividades;
	}

	public void setUsuarioAtividades(Set<UsuarioAtividade> usuarioAtividades) {
		this.usuarioAtividades = usuarioAtividades;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "evento")
	public Set<EventoUsuario> getEventoUsuarios() {
		return this.eventoUsuarios;
	}

	public void setEventoUsuarios(Set<EventoUsuario> eventoUsuarios) {
		this.eventoUsuarios = eventoUsuarios;
	}

}

