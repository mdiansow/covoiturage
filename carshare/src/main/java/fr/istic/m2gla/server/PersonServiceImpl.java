package fr.istic.m2gla.server;

import fr.istic.m2gla.shared.Personne;

import javax.persistence.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

/**
 * Created by mds on 10/11/14.
 */
@Path("/user")
public class PersonServiceImpl implements IPersonService{
    private EntityManager em;

    public PersonServiceImpl() {
        EntityManagerFactory factory = Persistence
                .createEntityManagerFactory("mysql");
        em = factory.createEntityManager();
        EntityTransaction t = em.getTransaction();
        t.begin();
        Personne personne = new Personne();
        personne.setNom("SOW");
        personne.setPrenom("Mamadou");
        em.persist(personne);
        t.commit();
    }

    @Override
    @GET
    @Path("search/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    public Personne findById(@PathParam("id") long id) {
        Personne result = null;
        if (id < 0 || id < 1)
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
    @Consumes({ MediaType.APPLICATION_JSON })
    @Path("create")
    public void create(Personne entity) {
        System.out.println("Create de AbstractDAO : "+entity.getClass().toString());
        if(entity == null)
            throw new PersistenceException("Entity to persist may not be null");//throw Persistence exception
        EntityTransaction t = em.getTransaction();
        t.begin();
        try {
            em.persist(entity);
            t.commit();
        }
        catch(Exception e){
            System.out.println("error persist commande"+e.toString());
        }
    }

    @Override
    @GET
    @Path("delete/{id}")
    public void delete(@PathParam("id") long id) {
        Personne result = null;
        if (id < 0 || id < 1)
            throw new PersistenceException("Id may not be null or negative");
        result = em.find(Personne.class,id);

        EntityTransaction t = em.getTransaction();
        t.begin();
        em.remove(result);
        t.commit();
    }

    @Override
    public Personne update(Personne entity) {
        return null;
    }
}
