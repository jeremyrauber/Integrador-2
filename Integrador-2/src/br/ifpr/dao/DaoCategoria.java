package br.ifpr.dao;

import java.util.List;

import javax.persistence.Query;

import br.ifpr.model.Artista;
import br.ifpr.model.Categoria;

public class DaoCategoria extends DaoEntity<Categoria,Integer> {

	public DaoCategoria() {
		super(Categoria.class);
	}

	public List<Categoria> findByNome(String fquery) {
		Query q = em.createNativeQuery("SELECT * from categoria WHERE nome LIKE '%"+fquery+"%'",Categoria.class);
		return q.getResultList();
	}
}
