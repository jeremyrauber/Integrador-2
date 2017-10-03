package model;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "usuario_has_atividade", catalog = "projeto")
public class UsuarioAtividade implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private UsuarioAtividadeId id;
	private Atividade atividade;
	private Evento evento;
	private Usuario usuario;
	private Date dataFimAtividade;
	private Boolean status;
	private String caminhoImagem;


	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "idUsuario", column = @Column(name = "id_usuario", nullable = false)),
			@AttributeOverride(name = "idAtividade", column = @Column(name = "id_atividade", nullable = false)),
			@AttributeOverride(name = "idEvento", column = @Column(name = "id_evento", nullable = false)) })
	public UsuarioAtividadeId getId() {
		return this.id;
	}

	public void setId(UsuarioAtividadeId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_atividade", nullable = false, insertable = false, updatable = false)
	public Atividade getAtividade() {
		return this.atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_evento", nullable = false, insertable = false, updatable = false)
	public Evento getEvento() {
		return this.evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario", nullable = false, insertable = false, updatable = false)
	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_fim_atividade", length = 19)
	public Date getDataFimAtividade() {
		return this.dataFimAtividade;
	}

	public void setDataFimAtividade(Date dataFimAtividade) {
		this.dataFimAtividade = dataFimAtividade;
	}

	@Column(name = "status")
	public Boolean getStatus() {
		return this.status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	@Column(name = "caminho_imagem")
	public String getCaminhoImagem() {
		return this.caminhoImagem;
	}

	public void setCaminhoImagem(String caminhoImagem) {
		this.caminhoImagem = caminhoImagem;
	}

}
