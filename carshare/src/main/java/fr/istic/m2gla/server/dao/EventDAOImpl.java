package fr.istic.m2gla.server.dao;

import fr.istic.m2gla.shared.Event;
import fr.istic.m2gla.shared.Ville;

import java.util.Date;
import java.util.List;

/**
 * Created by mds on 04/02/15.
 */
public class EventDAOImpl extends DaoImpl<Event> implements IEventDAO {
    public EventDAOImpl(Class<Event> entityClass) {
        super(entityClass);
    }

    @Override
    public List<Event> findEvent(Ville depart, Ville arrivee, Date date) {
        String req = "select p from Event p where p.date >= :date";
        return
                em.createQuery(req, Event.class)
                        .setParameter("date", date)
                        .getResultList();
    }
}
