package dao;

import java.util.List;

import javax.persistence.Query;

import model.Artista;

public class DaoArtista extends DaoEntity<Artista, Integer> {

	public DaoArtista() {
		super(Artista.class);
	}

	@SuppressWarnings("unchecked")
	public List<Artista> findByTitulo(String fquery) {
		Query q = em.createNativeQuery("SELECT * from artista WHERE nome LIKE '%"+fquery+"%'",Artista.class);
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Artista> findByCategoria(Integer cat) {
		Query q = em.createNativeQuery("SELECT artista.id FROM artista INNER JOIN artista_filme ON artista.id = artista_filme.artista_id INNER JOIN filme_categoria ON artista_filme.filme_id = filme_categoria.filme_id WHERE filme_categoria.categoria_id = "+cat+" GROUP BY artista.id ASC",Artista.class);
		return q.getResultList();
	}

}
