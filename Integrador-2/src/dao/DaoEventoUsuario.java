package dao;

import java.util.List;
import java.util.Set;

import javax.persistence.Query;

import model.EventoUsuario;


public class DaoEventoUsuario extends DaoEntity<EventoUsuario, Integer> {

	public DaoEventoUsuario(){
		super(EventoUsuario.class);
	}
	
	public List<EventoUsuario> findByEventoId(Integer id) {
		Query q = em.createNativeQuery("Select * from evento_has_usuario WHERE id_evento="+id,EventoUsuario.class);
		return  q.getResultList();
	}
	
	public EventoUsuario findByEventoAndUsuarioID(Integer id_evento,Integer id_usuario) {
		Query q = em.createNativeQuery("Select * from evento_has_usuario WHERE id_evento="+id_evento+" and id_usuario = "+id_usuario,EventoUsuario.class);
		return  (EventoUsuario) q.getSingleResult();
	}

}
