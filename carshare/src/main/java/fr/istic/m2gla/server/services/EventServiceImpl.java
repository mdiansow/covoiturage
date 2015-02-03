package fr.istic.m2gla.server.services;

import fr.istic.m2gla.server.dao.PersistenceManager;
import fr.istic.m2gla.shared.Event;
import fr.istic.m2gla.shared.Person;
import org.apache.log4j.Logger;

import javax.persistence.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

/**
 * Created by mds on 10/11/14.
 */
@Path("/event")
public class EventServiceImpl implements IEventService {
    private EntityManager em;
    static Logger log = Logger.getLogger(
            EventServiceImpl.class.getName());

    @Override
    @GET
    @Path("/addTraveller/eID/tID")
    @Produces({MediaType.APPLICATION_JSON})
    public Event joinEvent(@PathParam("eID") long eventID, @PathParam("tID") long travellerID) {
        Event ev = findById(eventID);
        Person trav = em.find(Person.class, travellerID);
        if (ev != null && trav != null) {
            ev.addTraveller(trav);

            EntityTransaction t = em.getTransaction();
            t.begin();
            try {
                em.merge(ev);
                t.commit();
            } catch (Exception e) {
                System.out.println("error persist commande" + e.toString());
            }
        }
        return ev;
    }

    public EventServiceImpl() {
        EntityManagerFactory factory = Persistence
                .createEntityManagerFactory("mysql");
        em = PersistenceManager.getInstance().getEntityManagerFactory().createEntityManager();
    }

    @Override
    @GET
    @Path("search/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Event findById(@PathParam("id") long id) {
        Event result = null;
        if (id < 0) {
            throw new PersistenceException("Id may not be null or negative");
        }
        result = em.find(Event.class, id);
        return result;
    }

    @Override
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Collection<Event> findAll() {
        log.debug("Hello this is an debug message");
        log.info("Hello this is an info message");
        return em.createQuery("select e from Event as e").getResultList();
    }

    @Override
//    @POST
//    @Consumes({MediaType.APPLICATION_JSON})
//    @Path("create")
    public void create(Event entity) {
//        System.out.println("Create de AbstractDAO : " + entity.getClass().toString());
//        if (entity == null)
//            throw new PersistenceException("Entity to persist may not be null");//throw Persistence exception
//        EntityTransaction t = em.getTransaction();
//        try {
//            t.begin();
//            System.err.println("BEFORE INSERT");
//            em.persist(entity);
//            System.err.println("AFTER INSERT");
//            t.commit();
//        } catch (Exception e) {
//            System.out.println("error persist commande" + e.toString());
//        }
    }

    @Override
    @GET
    @Path("delete/{id}")
    public void delete(@PathParam("id") long id) {
        Event result = null;
        if (id < 0 || id < 1)
            throw new PersistenceException("Id may not be null or negative");
        result = em.find(Event.class, id);

        EntityTransaction t = em.getTransaction();
        t.begin();
        em.remove(result);
        t.commit();
    }

    @Override
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("update")
    public Event update(Event entity) {
        System.out.println("Create de AbstractDAO : " + entity.getClass().toString());
        if (entity == null)
            throw new PersistenceException("Entity to persist may not be null");//throw Persistence exception
        EntityTransaction t = em.getTransaction();
        t.begin();
        try {
            em.merge(entity);
            System.err.println("GOOOD!");
            t.commit();
        } catch (Exception e) {
            System.out.println("error persist commande" + e.toString());
        }
        return entity;
    }

}
