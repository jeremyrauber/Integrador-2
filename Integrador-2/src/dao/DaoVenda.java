package dao;

import java.util.List;

import javax.persistence.Query;
import model.Venda;

import java.math.*;

public class DaoVenda extends DaoEntity<Venda, Integer> {

	public DaoVenda() {
		super(Venda.class);
	}

	public Integer findByUserAndFilme(Integer userquery, Integer filmquery) {
		Query q = em.createNativeQuery("SELECT count(*) FROM venda WHERE filme_id =? and usuario_id=?");
		  q.setParameter(1,filmquery.toString());
		  q.setParameter(2,userquery.toString()); 
		  Integer resultadoquery = ((BigInteger) q.getSingleResult()).intValue();
		  return resultadoquery;
	}
	
			
	@SuppressWarnings("unchecked")
	public List<Integer> findFilmesDoUser(Integer user_id){
		Query q = em.createNativeQuery("SELECT filme_id FROM venda WHERE usuario_id="+user_id);
		return q.getResultList();	
	}
	
	public Venda findVendaByUserAndFilme(Integer userquery, Integer filmquery) {
		Query q = em.createNativeQuery("SELECT * FROM venda WHERE filme_id =? and usuario_id=?",Venda.class);
		 q.setParameter(1,filmquery.toString());
		 q.setParameter(2,userquery.toString());
		return (Venda) q.getSingleResult();	
	}

}
