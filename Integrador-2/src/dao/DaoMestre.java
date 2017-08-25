package dao;

import java.math.BigInteger;

import javax.persistence.Query;

import model.Mestre;

public class DaoMestre extends DaoEntity<Mestre, Integer> {

	public DaoMestre() {
		super(Mestre.class);
	}

	
	public Mestre findMestre(String login,String senha) {
		
		em.getEntityManagerFactory().getCache().evictAll();
		
		Query q = em.createNativeQuery("SELECT * from mestre WHERE login='"+login+"' and senha = '"+senha+"'",Mestre.class);
	
		return (Mestre) (q.getSingleResult()); 
	}

	public Mestre findByHash(String hash) {
		Query q = em.createNativeQuery("Select * from mestre WHERE hashValidador='"+hash+"'",Mestre.class);
		return (Mestre) q.getSingleResult();
	}
	
	public BigInteger findExistingLoginOrEmail(String login,String email) {
		Query q = em.createNativeQuery("Select count(*) from mestre WHERE login='"+login+"' or email ='"+email+"'");
		return (BigInteger) q.getSingleResult();
	}
	
	public Mestre findbyrEmail(String email) {
		Query q = em.createNativeQuery("Select * from mestre WHERE email ='"+email+"'",Mestre.class);
		return (Mestre) q.getSingleResult();
	}
	
}
