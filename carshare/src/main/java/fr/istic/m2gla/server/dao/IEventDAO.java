package fr.istic.m2gla.server.dao;

import fr.istic.m2gla.shared.Event;
import fr.istic.m2gla.shared.Ville;

import java.util.Date;
import java.util.List;

/**
 * Created by mds on 04/02/15.
 */
public interface IEventDAO extends IDao<Event> {

    List<Event> findEvent(Ville depart, Ville arrivee, Date date);

}