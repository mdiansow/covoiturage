package fr.istic.m2gla.server.services;

import fr.istic.m2gla.server.dao.IDao;
import fr.istic.m2gla.shared.Event;

/**
 * Created by mds on 10/11/14.
 */
public interface IEventService extends IDao<Event> {
    public Event joinEvent(long eventID, long travellerID);
}
