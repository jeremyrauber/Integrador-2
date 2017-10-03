package model;
// default package
// Generated 02/10/2017 21:27:52 by Hibernate Tools 5.2.5.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * EventoHasAtividade generated by hbm2java
 */
@Entity
@Table(name = "evento_has_atividade", catalog = "projeto")
public class EventoAtividade implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private EventoAtividadeId id;

	public EventoAtividade() {
	}

	public EventoAtividade(EventoAtividadeId id) {
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "idEvento", column = @Column(name = "id_evento", nullable = false)),
			@AttributeOverride(name = "idAtividade", column = @Column(name = "id_atividade", nullable = false)) })
	public EventoAtividadeId getId() {
		return this.id;
	}

	public void setId(EventoAtividadeId id) {
		this.id = id;
	}

}
