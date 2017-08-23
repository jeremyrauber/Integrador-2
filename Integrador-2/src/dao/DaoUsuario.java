package dao;

import java.util.List;

import javax.persistence.Query;

import model.Usuario;

public class DaoUsuario extends DaoEntity<Usuario, Integer> {

	public DaoUsuario() {
		super(Usuario.class);
	}

	public Usuario findByLogin(String fquery) {
		Query q = em.createNativeQuery("SELECT * from usuario WHERE login LIKE '%"+fquery+"%'",Usuario.class);
		return (Usuario) q.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> findAllByLogin(String fquery) {
		Query q = em.createNativeQuery("SELECT * FROM usuario WHERE login LIKE '%"+fquery+"%'",Usuario.class);
		return q.getResultList();
	}
	

}
