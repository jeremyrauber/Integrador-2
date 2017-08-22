package DAO;

import javax.persistence.Query;

import Model.Mestre;

public class DaoLogin extends DaoEntity<String, Mestre>{
	
	public DaoLogin() {
		super(Mestre.class);
	}
	
	public String findSenha(String username) {
		try{
		Query q = em.createNativeQuery("SELECT senha FROM vendedores WHERE nome = :nome");
		q.setParameter("nome", username);
		return (String) q.getSingleResult();
		}
		catch(Exception ex){
			String result = "erro";
			return result;
		}
    }   
	
	public String findUserId(String username) {
		try{
		Query q = em.createNativeQuery("SELECT codigo FROM vendedores WHERE nome = :nome");
		
		q.setParameter("nome", username);
		return (String) q.getSingleResult();
		}
		catch(Exception ex){
			String result = "0";
			return result;
		}
    }   
   
}