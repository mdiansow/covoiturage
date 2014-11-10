package jpa;

import dao.IDao;
import dao.BasicDAO;
import model.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

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
				.createEntityManagerFactory("prod");
		EntityManager manager = factory.createEntityManager();
		JpaTest test = new JpaTest(manager);

        Personne personne = new Personne();
        Voiture voiture = new Voiture();
        personne.setNom("SOW");
        personne.setPrenom("Mamadou");
        personne.setVoiture(voiture);

        Ville add = new Ville();
        add.setNom("Rennes");
        personne.setAddress(add);

        Preference pref = new Preference();
        pref.setNomPref("Cigarette");
        List<Preference> mesPrefs = new ArrayList<>();
        mesPrefs.add(pref);
        personne.setPreferences(mesPrefs);

        Evenement event = new Evenement();
        event.setLieu("La Gare");
        List<Evenement> myEvents = new ArrayList<>();
        myEvents.add(event);
        personne.setMyEvents(myEvents);

        IDao<Personne> dao = new BasicDAO(Personne.class);
        dao.setEm(manager);
        dao.create(personne);

        dao.findAll();

        manager.close();
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
	}
}
