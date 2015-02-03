package fr.istic.m2gla.server.services;

import fr.istic.m2gla.server.dao.IDao;
import fr.istic.m2gla.shared.Event;
import fr.istic.m2gla.shared.Person;

import java.util.List;

/**
 * Created by mds on 10/11/14.
 */
public interface IPersonService extends IDao<Person> {
    public Person createAnEvent(long id, Event event);
    public List<Event> myEvents(long id);
}
