package fr.istic.m2gla.server.services;

import fr.istic.m2gla.shared.Person;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

/**
 * Created by mds on 10/11/14.
 */

public interface IPersonService {
    @GET
    @Path("search/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    Person findById(@PathParam("id") long id);

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    Collection<Person> findAll();

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("create")
    String create(Person entity);

    @GET
    @Path("delete/{id}")
    void delete(@PathParam("id") long id);

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("update")
    Person update(Person entity);

    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/login")
    Person login(@FormParam("username") String username, @FormParam("password") String password);

    @Path("profile")
    @Produces({MediaType.APPLICATION_JSON})
    @GET
    Response userProfile(@CookieParam("username") javax.ws.rs.core.Cookie username);
}
