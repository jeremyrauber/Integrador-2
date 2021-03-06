package model;
// default package
// Generated 02/10/2017 21:27:52 by Hibernate Tools 5.2.5.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * EventoHasAtividadeId generated by hbm2java
 */
@Embeddable
public class EventoAtividadeId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idEvento;
	private int idAtividade;

	public EventoAtividadeId() {
	}

	public EventoAtividadeId(int idEvento, int idAtividade) {
		this.idEvento = idEvento;
		this.idAtividade = idAtividade;
	}

	@Column(name = "id_evento", nullable = false)
	public int getIdEvento() {
		return this.idEvento;
	}

	public void setIdEvento(int idEvento) {
		this.idEvento = idEvento;
	}

	@Column(name = "id_atividade", nullable = false)
	public int getIdAtividade() {
		return this.idAtividade;
	}

	public void setIdAtividade(int idAtividade) {
		this.idAtividade = idAtividade;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof EventoAtividadeId))
			return false;
		EventoAtividadeId castOther = (EventoAtividadeId) other;

		return (this.getIdEvento() == castOther.getIdEvento()) && (this.getIdAtividade() == castOther.getIdAtividade());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getIdEvento();
		result = 37 * result + this.getIdAtividade();
		return result;
	}

}
