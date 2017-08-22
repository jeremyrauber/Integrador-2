package model;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class GeraTabelas {
	
	public GeraTabelas(){
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("cinemaPU");
		
	}
	
	public static void main(String[] args) {
	
		new GeraTabelas();
		
	}

}
