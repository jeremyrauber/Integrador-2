package model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class UsuarioAtividadeId implements Serializable { 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Usuario usuario;
    private Atividade atividade;
 
    @ManyToOne(cascade = CascadeType.ALL)
    public Usuario getUsuario() {
        return usuario;
    }
 
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
 
    @ManyToOne(cascade = CascadeType.ALL)
    public Atividade getAtividade() {
        return atividade;
    }
 
    public void setAtividade(Atividade atividade) {
        this.atividade= atividade;
    }
}