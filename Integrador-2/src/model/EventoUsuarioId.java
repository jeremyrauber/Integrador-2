package model;
 
import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class EventoUsuarioId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idUsuario;
	private int idEvento;

	

	@Column(name = "id_usuario", nullable = false)
	public int getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	@Column(name = "id_evento", nullable = false)
	public int getIdEvento() {
		return this.idEvento;
	}

	public void setIdEvento(int idEvento) {
		this.idEvento = idEvento;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof EventoUsuarioId))
			return false;
		EventoUsuarioId castOther = (EventoUsuarioId) other;

		return (this.getIdUsuario() == castOther.getIdUsuario()) && (this.getIdEvento() == castOther.getIdEvento());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getIdUsuario();
		result = 37 * result + this.getIdEvento();
		return result;
	}

}