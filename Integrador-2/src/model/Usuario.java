package model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;



@Entity
@Table(name = "usuario", catalog = "projeto", uniqueConstraints = @UniqueConstraint(columnNames = "login"))
public class Usuario implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String nome;
	private String login;
	private String senha;
	private String endereco;
	private Date dataNasc;
	private String bairro;
	private String cidade;
	private String cep;
	private String email;
	private Boolean ativo;
	private String estado;
	private Integer banido;
	private Date dataCadastro;
	private Set<EventoUsuario> eventoUsuarios = new HashSet<EventoUsuario>(0);
	private Set<UsuarioAtividade> usuarioAtividades = new HashSet<UsuarioAtividade>(0);


	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "nome", nullable = false, length = 45)
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "login", unique = true, nullable = false, length = 45)
	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Column(name = "senha", nullable = false, length = 45)
	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Column(name = "endereco", nullable = false, length = 45)
	public String getEndereco() {
		return this.endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_nasc", nullable = false, length = 19)
	public Date getDataNasc() {
		return this.dataNasc;
	}

	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}

	@Column(name = "bairro", nullable = false, length = 45)
	public String getBairro() {
		return this.bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	@Column(name = "cidade", nullable = false, length = 45)
	public String getCidade() {
		return this.cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	@Column(name = "cep", nullable = false, length = 45)
	public String getCep() {
		return this.cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	@Column(name = "email", nullable = false, length = 45)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "ativo")
	public Boolean getAtivo() {
		return this.ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	@Column(name = "estado", nullable = false, length = 45)
	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Column(name = "banido")
	public Integer getBanido() {
		return this.banido;
	}

	public void setBanido(Integer banido) {
		this.banido = banido;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_cadastro", length = 19)
	public Date getDataCadastro() {
		return this.dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
	public Set<EventoUsuario> getEventoUsuarios() {
		return this.eventoUsuarios;
	}

	public void setEventoUsuarios(Set<EventoUsuario> eventoUsuarios) {
		this.eventoUsuarios = eventoUsuarios;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
	public Set<UsuarioAtividade> getUsuarioAtividades() {
		return this.usuarioAtividades;
	}

	public void setUsuarioAtividades(Set<UsuarioAtividade> usuarioAtividades) {
		this.usuarioAtividades = usuarioAtividades;
	}

}
