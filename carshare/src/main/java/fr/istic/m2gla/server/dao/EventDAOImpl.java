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

    //SELECT e FROM Employee e JOIN e.phoneNumbers p WHERE p.areaCode = '613'

    @Override
    public List<Event> findEvent(Ville depart, Ville arrivee, Date date) {
        String req = "select e from Event e JOIN e.depart d where e.date >= :date and d.id =:dID";
        return
                em.createQuery(req, Event.class)
                        .setParameter("date", date)
                        .setParameter("dID", depart.getId())
                        .getResultList();
    }
}
