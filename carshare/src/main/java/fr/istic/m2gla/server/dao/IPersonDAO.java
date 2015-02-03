package fr.istic.m2gla.server.dao;

import fr.istic.m2gla.shared.Event;
import fr.istic.m2gla.shared.Person;

import java.util.List;

/**
 * Created by mds on 02/02/15.
 */
public interface IPersonDAO extends IDao<Person> {
    public List<Event> findEventsByPersonID(long id);
}
