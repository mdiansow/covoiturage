package fr.istic.m2gla.server.dao;

import fr.istic.m2gla.shared.Event;
import fr.istic.m2gla.shared.Person;

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
}
