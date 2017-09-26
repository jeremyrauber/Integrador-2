package model;
 
import java.io.Serializable;
 
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
 
@Embeddable
public class EventoUsuarioId implements Serializable { 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Usuario usuario;
    private Evento evento;
 
    @ManyToOne
    public Usuario getUsuario() {
        return usuario;
    }
 
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
 
    @ManyToOne
    public Evento getEvento() {
        return evento;
    }
 
    public void setEvento(Evento evento) {
        this.evento = evento;
    }
}