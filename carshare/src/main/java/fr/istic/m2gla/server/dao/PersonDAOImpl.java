package fr.istic.m2gla.server.dao;

import fr.istic.m2gla.shared.Event;
import fr.istic.m2gla.shared.Person;

import javax.persistence.NoResultException;
import java.util.List;

/**
 * Created by mds on 02/02/15.
 */
public class PersonDAOImpl extends DaoImpl<Person> implements IPersonDAO {
    public PersonDAOImpl(Class<Person> entityClass) {
        super(entityClass);
    }

    @Override
    public List<Event> findEventsByPersonID(long id) {
        String req = "select e from Person p JOIN p.myEvents e where p.id = id";
        return em.createQuery(req).getResultList();
    }

    @Override
    public Person findByLogger(String username, String password) {
        String req = "select p from Person p where p.username = :username and p.password = :password";
        Person person = null;
        try {
            em.createQuery(req, Person.class)
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (NoResultException e) {

        }
        return person;
    }

    @Override
    public Person findByUsername(String username) {
        String req = "select p from Person p where p.username = :username";
        Person person = null;
        try {
            person = em.createQuery(req, Person.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException e) {

        }
        return person;
    }

    @Override
    public Person findBbyEmail(String email) {
        String req = "select p from Person p where p.email = :email";
        Person person = null;
        try {
            em.createQuery(req, Person.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {

        }
        return person;
    }
}
