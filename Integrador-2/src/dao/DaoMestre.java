package dao;

import java.util.List;

import javax.persistence.Query;

import model.Mestre;

public class DaoMestre extends DaoEntity<Mestre, Integer> {

	public DaoMestre() {
		super(Mestre.class);
	}

	public Mestre findMestre(String login,String senha) {
		Query q = em.createNativeQuery("SELECT * from mestre WHERE login='"+login+"' and senha = '"+senha+"'",Mestre.class);
		return (Mestre) q.getSingleResult();
	}

}
