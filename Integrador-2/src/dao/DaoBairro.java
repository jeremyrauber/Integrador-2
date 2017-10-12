package dao;

import java.util.List;

import javax.persistence.Query;

import model.Bairro;
import model.EventoUsuario;

public class DaoBairro extends DaoEntity<Bairro, Integer> {

	public DaoBairro() {
		super(Bairro.class);
	}
	
	public List<Bairro> findOrdenado() {
		Query q = em.createNativeQuery("SELECT * FROM bairro ORDER BY nome ASC ",Bairro.class);
		return  q.getResultList();
	}
	
}