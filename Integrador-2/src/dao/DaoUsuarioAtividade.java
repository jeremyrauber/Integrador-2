package dao;

import java.util.List;

import javax.persistence.Query;

import model.EventoUsuario;
import model.UsuarioAtividade;

public class DaoUsuarioAtividade extends DaoEntity<UsuarioAtividade, Integer> {

	public DaoUsuarioAtividade(){
		super(UsuarioAtividade.class);
	}
	
	public List<UsuarioAtividade> findByEventoId(Integer id) {
		Query q = em.createNativeQuery("Select * from usuario_has_atividade WHERE id_evento="+id,EventoUsuario.class);
		return  q.getResultList();
	}

}
