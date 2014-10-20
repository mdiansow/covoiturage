package jpa;

import dao.DAO;
import dao.IDao;
import model.Personne;
import model.Voiture;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaTest {

	private EntityManager manager;

	public JpaTest(EntityManager manager) {
		this.manager = manager;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("mysql");
		EntityManager manager = factory.createEntityManager();
		JpaTest test = new JpaTest(manager);

		EntityTransaction tx = manager.getTransaction();
		tx.begin();

        Personne personne = new Personne();
        Voiture voiture = new Voiture();
        personne.setNom("sow");
        personne.setPrenom("Mamadou");

        IDao<Personne> dao = new DAO();
        dao.setEm(manager, "Personne");
        dao.create(personne);

        dao.findAll();

       /*
		try {
			Personne p = new Personne();
            Voiture v = new Voiture();
            p.setNom("SOW");
            p.setPrenom("Mamadou");
            p.setVoiture(v);
   			manager.persist(p);
			
			//manager.persist(et);
			

		} catch (Exception e) {
			e.printStackTrace();
		}*/
		tx.commit();
	}
}