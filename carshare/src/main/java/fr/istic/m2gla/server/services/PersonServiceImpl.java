package fr.istic.m2gla.server.services;

import fr.istic.m2gla.server.dao.IPersonDAO;
import fr.istic.m2gla.server.dao.PersonDAOImpl;
import fr.istic.m2gla.shared.Event;
import fr.istic.m2gla.shared.Person;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.List;

/**
 * Created by mds on 10/11/14.
 */
@Path("/user")
public class PersonServiceImpl implements IPersonService {
    private EntityManager em;
    static Logger log = Logger.getLogger(
            PersonServiceImpl.class.getName());


    private IPersonDAO personIDao;

    public PersonServiceImpl() {
        personIDao = new PersonDAOImpl(Person.class);
    }

    @Override
    @Path("addEvent/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @POST
    public Person createAnEvent(@PathParam("id") long id, Event event) {
        Person person = findById(id);
        if (person != null) {
            EntityTransaction t = em.getTransaction();
            try {
                t.begin();
                em.persist(event);
                person.addEvent(event);
                em.merge(person);
                t.commit();
            } catch (Exception e) {
                System.out.println("error persist commande" + e.toString());
            }
        }
        return person;
    }

    @Override
    @Path("events/owner={id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @GET
    public List<Event> myEvents(@PathParam("id") long id) {
        Person person = personIDao.findById(id);
        return person.getMyEvents();
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
    public void create(Person entity) {
        System.out.println("Create de AbstractDAO : " + entity.getClass().toString());
        personIDao.create(entity);
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
        Person result = null;
        if (id < 0 || id < 1) {
            throw new PersistenceException("Id may not be null or negative");
        }
        result = em.find(Person.class, id);

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
    public Person update(Person entity) {
        return personIDao.update(entity);
    }
}
