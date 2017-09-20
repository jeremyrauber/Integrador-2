package model;

import java.util.Date;

import javax.persistence.CascadeType;
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
	
	private Date dataVinculo;
	
	private Integer banidoEvento;
	
}