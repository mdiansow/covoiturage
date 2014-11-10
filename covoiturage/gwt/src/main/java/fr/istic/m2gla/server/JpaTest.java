package fr.istic.m2gla.server;

import fr.istic.m2gla.shared.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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
				.createEntityManagerFactory("dev");
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
        List<Preference> mesPrefs = new ArrayList<Preference>();
        mesPrefs.add(pref);
        personne.setPreferences(mesPrefs);

        Evenement event = new Evenement();
        event.setLieu("La Gare");
        List<Evenement> myEvents = new ArrayList<Evenement>();
        myEvents.add(event);
        personne.setMyEvents(myEvents);

        IDao<Personne> dao = new BasicDAOImpl<Personne>(Personne.class);
        dao.setEm(manager);
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
	}
}
