package fr.istic.m2gla.server.services;

import fr.istic.m2gla.server.dao.IPersonDAO;
import fr.istic.m2gla.server.dao.PersonDAOImpl;
import fr.istic.m2gla.shared.Person;
import org.apache.log4j.Logger;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.Collection;

/**
 * Created by mds on 10/11/14.
 */
@Path("/user")
public class PersonServiceImpl implements IPersonService {
    static Logger log = Logger.getLogger(
            PersonServiceImpl.class.getName());

    @Context
    private HttpServletRequest request;
    @Context
    private HttpServletResponse response;

    private IPersonDAO personIDao;

    public PersonServiceImpl() {
        personIDao = new PersonDAOImpl(Person.class);
    }

    @Override
    @GET
    @Path("search/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Person findById(@PathParam("id") long id) {
        return personIDao.findById(id);
    }

    @Override
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Collection<Person> findAll() {
        log.debug("Hello this is an debug message");
        log.info("Hello this is an info message");
        return personIDao.findAll();
    }

    @Override
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("create")
    public String create(Person entity) {
        System.out.println("Create de AbstractDAO : " + entity.getClass().toString());
        String message = "SUCCESS";
        Person person = personIDao.findBbyEmail(entity.getEmail());
        if (person != null) {
            Exception e = new PersistenceException("Email exists");
            return e.getMessage();
        }
        person = personIDao.findByUsername(entity.getUsername());
        if (person != null) {
            Exception e = new PersistenceException("Username exists");
            return e.getMessage();
        }
        System.out.println("USER ==== " + entity.toString());
        personIDao.create(entity);
        return message;
    }

    @Override
    @GET
    @Path("delete/{id}")
    public void delete(@PathParam("id") long id) {
        personIDao.delete(id);
    }

    @Override
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("update")
    public Person update(Person entity) {
        return personIDao.update(entity);
    }

    @Override
    @POST
    @Consumes("application/x-www-form-urlencoded")
//    @Produces({MediaType.APPLICATION_JSON})
    @Path("/login")
    public Response login(@FormParam("username") String username, @FormParam("password") String password) {
        System.out.println("Services \t" + username);
        Person user = personIDao.findByUsername(username);//personIDao.findByLogger(username, password);
        if (user != null) {
            HttpSession session = request.getSession(true);
            session.setAttribute("username", user.getUsername());
            return Response.ok().header("username", user.getUsername()).build();
        } else {
            System.out.println("user------" + user);
            return Response.serverError().build();
        }
    }

    @Override
    @Path("profile")
    @Produces({MediaType.APPLICATION_JSON})
    @GET
    public Response userProfile(@CookieParam("username") Cookie username) {
        if (username == null) {
            return Response.serverError().entity("ERROR").build();
        } else {
            Person user = personIDao.findByUsername(username.getValue());
            String url = "Error";
            if (user != null) {
                url = "event/owner=" + user.getId();
            }
            return Response.seeOther(URI.create(url)).build();
        }
    }
}
