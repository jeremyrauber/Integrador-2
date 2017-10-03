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
@Table(name = "evento_has_usuario", catalog = "projeto")
public class EventoUsuario implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private EventoUsuarioId id;
	private Evento evento;
	private Usuario usuario;
	private Date dataVinculo;
	private Boolean banidoEvento;


	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "idUsuario", column = @Column(name = "id_usuario", nullable = false)),
			@AttributeOverride(name = "idEvento", column = @Column(name = "id_evento", nullable = false)) })
	public EventoUsuarioId getId() {
		return this.id;
	}

	public void setId(EventoUsuarioId id) {
		this.id = id;
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
	@Column(name = "data_vinculo", length = 19)
	public Date getDataVinculo() {
		return this.dataVinculo;
	}

	public void setDataVinculo(Date dataVinculo) {
		this.dataVinculo = dataVinculo;
	}

	@Column(name = "banido_evento")
	public Boolean getBanidoEvento() {
		return this.banidoEvento;
	}

	public void setBanidoEvento(Boolean banidoEvento) {
		this.banidoEvento = banidoEvento;
	}
}