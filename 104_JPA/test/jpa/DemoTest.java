package jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import fr.eservice.jpa.EtudiantJPA;

public class DemoTest {
	
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("myapp");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		EtudiantJPA etudiant = new EtudiantJPA();
		etudiant.setFirstname("Guillaume");
		etudiant.setLastname("Dufrene");
		etudiant.setAge( 31 );
		
		em.persist(etudiant);
		
		tx.commit();
		
		em.close();
		emf.close();
	}

}
