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
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Integer id;
	
	@Column(nullable=false)
	private String nome;
	
	@Column(nullable=false,unique = true)
	private String login;
	
	@Column(nullable=false)
	private String senha;
	
	@Column(nullable=false)
	private String endereco;

	@Column(nullable=false)
	private String bairro;

	@Column(nullable=false)
	private String cidade;
	
	@Column(nullable=false)
	private String estado;
	
	@Column(nullable=false)
	private String cep;
	
	@Column(nullable=false)
	private String email;
	
	@Column(nullable=false)
	private boolean ativo;
	
	@Column(nullable=false)
	private String banido;
	
	@Column(nullable=false,name="data_cadastro")
	private Date dataCadastro;
	
	@Column(nullable=false,name="data_nasc")
	private Date dataNascimento;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
	private Set<EventoUsuario> eventos = new HashSet<EventoUsuario>(0);

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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
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

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public String getBanido() {
		return banido;
	}

	public void setBanido(String banido) {
		this.banido = banido;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Set<EventoUsuario> getEventos() {
		return eventos;
	}

	public void setEventos(Set<EventoUsuario> eventos) {
		this.eventos = eventos;
	}
}
