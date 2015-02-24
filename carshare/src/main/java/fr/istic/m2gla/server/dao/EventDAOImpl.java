package fr.istic.m2gla.server.dao;

import fr.istic.m2gla.shared.Event;

/**
 * Created by mds on 04/02/15.
 */
public class EventDAOImpl extends DaoImpl<Event> implements IEventDAO {
    public EventDAOImpl(Class<Event> entityClass) {
        super(entityClass);
    }
}
