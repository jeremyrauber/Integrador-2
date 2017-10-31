package dao;

import java.util.List;

import javax.persistence.Query;

import model.EventoAtividade;
import model.EventoUsuario;

public class DaoEventoAtividade  extends DaoEntity<EventoAtividade, Integer> {

		public DaoEventoAtividade() {
			super(EventoAtividade.class);
		}
	
		@SuppressWarnings("unchecked")
		public List<EventoAtividade> findByEventoID(Integer id_evento) {
			Query q = em.createNativeQuery("SELECT * FROM evento_has_atividade WHERE id_evento="+id_evento,EventoAtividade.class);
			return (List<EventoAtividade>) q.getResultList();
		}
		
		
}
