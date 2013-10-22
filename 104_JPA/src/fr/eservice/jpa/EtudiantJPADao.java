package fr.eservice.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import fr.eservice.common.Etudiant;
import fr.eservice.common.EtudiantDao;

public class EtudiantJPADao implements EtudiantDao {

	EntityManager em;
	
	@Override
	public void init() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("myapp");
		em = emf.createEntityManager();
	}
	
	private Etudiant selectCompareId(String comp, int id) {
		try {
			EtudiantJPA etudiant = em.createQuery( 
					"select e from etudiant e where e.id "+comp+" :ID order by e.id" + ("<".equals(comp) ? " DESC" : ""), EtudiantJPA.class 
				)
				.setParameter("ID", id)
				.setMaxResults(1)
				.getSingleResult();
			return etudiant;
		} catch (NoResultException e) {
			return null;
		}
	}
	
	@Override
	public Etudiant after(int id) {
		return selectCompareId(">", id);
	}
	
	@Override
	public Etudiant before(int id) {
		return selectCompareId("<", id);
	}
	
	@Override
	public Etudiant load(int id) {
		return em.find(EtudiantJPA.class, id);
	}
	
	@Override
	public boolean save(Etudiant etudiant) {
		EtudiantJPA toSave = new EtudiantJPA();
		toSave.setFirstname( etudiant.getFirstname() );
		toSave.setLastname( etudiant.getLastname() );
		toSave.setAge( etudiant.getAge() );
		em.getTransaction().begin();
		em.persist(toSave);
		em.getTransaction().commit();
		int id = toSave.getId();
		etudiant.setId( id );
		return id != 0;
	}
	
	@Override
	public void clear() {
		em.getTransaction().begin();
		em.createQuery("delete from etudiant").executeUpdate();
		em.getTransaction().commit();
	}
	
}
