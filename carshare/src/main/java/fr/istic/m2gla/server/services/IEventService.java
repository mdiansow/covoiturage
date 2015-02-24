package fr.istic.m2gla.server.services;

import fr.istic.m2gla.shared.Event;
import fr.istic.m2gla.shared.Person;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.List;

/**
 * Created by mds on 10/11/14.
 */
public interface IEventService {
    public Event joinEvent(long eventID, long travellerID);

    @Path("events/owner={id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @GET
    List<Event> myEvents(@PathParam("id") long id);

    @GET
    @Path("search/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    Event findById(@PathParam("id") long id);

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    Collection<Event> findAll();

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("create")
    String create(Event entity, @PathParam("username") String username);

    @GET
    @Path("delete/{id}")
    void delete(@PathParam("id") long id);

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("update")
    Event update(Event entity);

    @Path("addEvent/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @POST
    Person createAnEvent(@PathParam("id") long id, Event event);
}
