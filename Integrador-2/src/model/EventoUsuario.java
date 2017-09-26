package model;

import java.util.Date;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


@Entity
@Table(name="evento_has_usuario")
@AssociationOverrides({
    @AssociationOverride(name = "primaryKey.usuario",
        joinColumns = @JoinColumn(name = "id_usuario")),
    @AssociationOverride(name = "primaryKey.evento",
        joinColumns = @JoinColumn(name = "id_evento")) })
public class EventoUsuario implements java.io.Serializable {
	

	private static final long serialVersionUID = 1L;
	
	// composite-id key
	 @EmbeddedId
    private EventoUsuarioId primaryKey = new EventoUsuarioId();
		
	//outros atributos
	@Temporal(TemporalType.DATE)
	@Column(name = "data_vinculo")
	private Date dataVinculo;
	
	@Column(name="banido_evento")
	private Integer banidoEvento;


	 @EmbeddedId
    public EventoUsuarioId getPrimaryKey() {
        return primaryKey;
    }
 
	public void setPrimaryKey(EventoUsuarioId primaryKey) {
        this.primaryKey = primaryKey;
    }
 
    @Transient
    public Usuario getUsuario() {
        return getPrimaryKey().getUsuario();
    }
 
    public void setUsuario(Usuario usuario) {
        getPrimaryKey().setUsuario(usuario);
    }
 
    @Transient
    public Evento getEvento() {
        return getPrimaryKey().getEvento();
    }
 
    public void setEvento(Evento evento) {
        getPrimaryKey().setEvento(evento);
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

	
}