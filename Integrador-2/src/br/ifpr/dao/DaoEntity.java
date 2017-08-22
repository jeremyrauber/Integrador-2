package br.ifpr.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class DaoEntity<T,ID> {
	
	protected static EntityManagerFactory emf;
	protected static EntityManager em;
	private final Class<T> clazz;

	public DaoEntity(Class<T> _clazz){
		if(emf==null){
			emf = Persistence.createEntityManagerFactory("cinemaPU");
			em = emf.createEntityManager();
		}
		clazz = _clazz;
	}
	public void save(T obj) {
		em.clear();
		em.getTransaction().begin();
		em.persist(obj);
		em.getTransaction().commit();
	}
	public void update(T obj) {
		em.clear();
		em.getTransaction().begin();
		em.merge(obj);
		em.getTransaction().commit();
	}
	public void remove(T obj) {
		em.getTransaction().begin();
		em.remove(obj);
		em.getTransaction().commit();
	}
	public List<T> findAll() {
		em.clear();
		Query q = em.createQuery("from "+ clazz.getSimpleName() +" tb");
		return q.getResultList();
	}
	public T findById(ID id) {
		em.clear();
		return em.find(clazz, id);
	}
}
