/*package model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="evento_has_usuario")
public class EventoUsuario {
	
	@ManyToOne
	@JoinColumn(name = "evento_id", insertable = false, updatable = false)
	private Evento evento;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="usuario_id", insertable = false, updatable = false)
	private Usuario usuario;
	
	@Column(name="data_vinculo")
	private Date dataVinculo;
	
	@Column(name="banido_evento")
	private Integer banidoEvento;

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getDataVinculo() {
		return dataVinculo;
	}

	public void setDataVinculo(Date dataVinculo) {
		this.dataVinculo = dataVinculo;
	}

	public Integer getBanidoEvento() {
		return banidoEvento;
	}

	public void setBanidoEvento(Integer banidoEvento) {
		this.banidoEvento = banidoEvento;
	}
	
}*/