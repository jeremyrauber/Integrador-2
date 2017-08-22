package br.ifpr.dao;

import java.util.List;

import javax.persistence.Query;

import br.ifpr.model.Filme;

public class DaoFilme extends DaoEntity<Filme, Integer> {

	public DaoFilme() {
		super(Filme.class);
	}

	public List<Filme> findByTitulo(String fquery) {
		Query q = em.createNativeQuery("SELECT * from filme WHERE titulo LIKE '%"+fquery+"%'",Filme.class);
		return q.getResultList();
	}
	
	public List<Filme> findByLike(String Parametro) {
		Query q = em.createNativeQuery("SELECT * FROM filme ORDER by likes ASC limit "+Parametro,Filme.class);
		return q.getResultList();
	}

}
