package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;



public class DaoEntity<ID, T> {
	 
	protected static EntityManagerFactory emf;
	protected static EntityManager em;
		
	private final Class<T> clazz;

	public DaoEntity(Class<T> _clazz){
		if(emf==null){
			emf = Persistence.createEntityManagerFactory("Integrador");
			em = emf.createEntityManager();
		}
		clazz = _clazz;
	}
		
		public void save(T obj){
			
			em.getTransaction().begin();
			em.persist(obj);
			em.getTransaction().commit();
		}
		
		public String update(T obj){
				
				em.getTransaction().begin();
				em.merge(obj);
				em.getTransaction().commit();
				return "Update com sucesso";
		}
		
		public void remove(T obj){
			
			em.getTransaction().begin();
			em.remove(obj);
			em.getTransaction().commit();
		}
		
		@SuppressWarnings("unchecked")
		public List<T> findAll(){	
			Query q = em.createQuery("from " +clazz.getSimpleName()+" tb ");  //nome da Classe!! ( com C MAIUSCULO! )
			return q.getResultList();
			
		}
		
		public T findById (ID id){
				
			return em.find(clazz,id);
		}
		
		@SuppressWarnings("unchecked")
		public ID findLastId() {
			
			Query q = em.createNativeQuery("select max(codigo) from "+clazz.getSimpleName());
			
			return (ID) q.getSingleResult();
		}
		
		@SuppressWarnings("unchecked")
		public ID findOrcLastId() {
			
			Query q = em.createNativeQuery("select max(sequencial) from "+clazz.getSimpleName());
			
			return (ID) q.getSingleResult();
		}
		
		public T findByNome (String nome){
			
			return em.find(clazz,nome);
		}
		
		@SuppressWarnings("unchecked")
		public ID geraSenquencial(){
			
			Query q = em.createNativeQuery("select sequencial from gerasequencialgen");
			
			return ((ID) q.getSingleResult());
		}
		
}

