package fr.istic.m2gla.server;

import fr.istic.m2gla.shared.Personne;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

/**
 * Created by mds on 10/11/14.
 */
@Path("/user")
public class PersonServiceImpl implements IPersonService{
    private EntityManager em;

    @Override
    @GET
    @Path("search/{id}")
    public Personne findById(Integer id) {
        Personne result = null;
        if (id == null || id < 1)
            throw new PersistenceException("Id may not be null or negative");
        result = em.find(Personne.class,id);
        return result;
    }

    @Override
    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public Collection<Personne> findAll() {
        return em.createQuery("select e from m2gla_personne as e").getResultList();
    }

    @Override
    @POST
    @Path("create")
    public void create(Personne entity) {
        System.out.println("Create de AbstractDAO : "+entity.getClass().toString());
        if(entity == null)
            throw new PersistenceException("Entity to persist may not be null");//throw Persistence exception
        em.getTransaction().begin();
        try {
            em.persist(entity);
        }
        catch(Exception e){
            System.out.println("error persist commande"+e.toString());
        }
    }

    @Override
    public void delete(Personne entity) {

    }

    @Override
    public Personne update(Personne entity) {
        return null;
    }
}
