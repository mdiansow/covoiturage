package fr.istic.m2gla.server.services;

import fr.istic.m2gla.server.dao.EventDAOImpl;
import fr.istic.m2gla.server.dao.IEventDAO;
import fr.istic.m2gla.server.dao.IPersonDAO;
import fr.istic.m2gla.server.dao.PersonDAOImpl;
import fr.istic.m2gla.shared.Event;
import fr.istic.m2gla.shared.Person;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.List;

/**
 * Created by mds on 10/11/14.
 */
@Path("/event")
public class EventServiceImpl implements IEventService {
    static Logger log = Logger.getLogger(
            EventServiceImpl.class.getName());
    private final IPersonDAO personDAO;
    private final IEventDAO eventDAO;

    public EventServiceImpl() {
        personDAO = new PersonDAOImpl(Person.class);
        eventDAO = new EventDAOImpl(Event.class);
    }

    @Override
    @GET
    @Path("/addTraveller/eID/tID")
    @Produces({MediaType.APPLICATION_JSON})
    public Event joinEvent(@PathParam("eID") long eventID, @PathParam("tID") long travellerID) {
        Event ev = null;
        return ev;
    }

    @Override
    @Path("owner={id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @GET
    public List<Event> myEvents(@PathParam("id") long id) {
        Person person = personDAO.findById(id);
        return person.getMyEvents();
    }

    @Override
    @GET
    @Path("search/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Event findById(@PathParam("id") long id) {
        return eventDAO.findById(id);
    }

    @Override
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Collection<Event> findAll() {
        log.debug("Hello this is an debug message");
        log.info("Hello this is an info message");
        return eventDAO.findAll();
    }

    @Override
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("create/{username}")
    public String create(Event entity, @PathParam("username") String username) {
        String message = "ECHEC";
        Person user = personDAO.findByUsername(username);
        if (user != null) {
//            entity.setOwner(user);
//            eventDAO.create(entity);
//            user.getMyEvents().add(entity);
            user.addEvent(entity);
            personDAO.update(user);
            System.out.println("entity create");
            //user.addEvent(entity);
            //entity.setOwner(user);
            message = "SUCCESS";
        }
        return message;
    }

    @Override
    @GET
    @Path("delete/{id}")
    public void delete(@PathParam("id") long id) {

    }

    @Override
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("update")
    public Event update(Event entity) {

        return entity;
    }


    @Override
    @Path("addEvent/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @POST
    public Person createAnEvent(@PathParam("id") long id, Event event) {
        Person person = null; //findById(id);

        return person;
    }

}
