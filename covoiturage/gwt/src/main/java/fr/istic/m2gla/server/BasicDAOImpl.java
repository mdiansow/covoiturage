package fr.istic.m2gla.server;


import fr.istic.m2gla.shared.Personne;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;
import java.util.Map;

/**
 * Created by root on 19/10/14.
 */
public class BasicDAOImpl<T> implements IDao {
    private EntityManager em;

    private Class<T> entity;

    public BasicDAOImpl(Class<T> entity) {
        this.entity = entity;
    }

    @Override
    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public EntityManager getEm() {
        return this.em;
    }

    @Override
    @GET
    @Path("search/{id}")
    public T findById(@PathParam("id") Integer id) {
        T result = null;
        if (id == null || id < 1)
            throw new PersistenceException("Id may not be null or negative");
        result = em.find(entity, id);
        return result;
    }

    @Override
    public List findAll() {
        em.getTransaction().begin();
        List<Personne> resultList = em.createQuery("select a from m2gla_personne a",
                Personne.class).getResultList();
        em.getTransaction().commit();
        em.close();
        System.out.println("Nb of persons: " + resultList.size());
        for (Personne next : resultList) {
            System.out.println("next person: " + next.getNom());
        }
        return resultList;
    }

    @Override
    public Object update(Object entity) {
        return null;
    }

    @Override
    public void create(Object entity) {
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
        em.getTransaction().commit();
    }

    @Override
    public void delete(Object entity) {
        if (entity == null)
            throw new PersistenceException("Entity to delete may not be null");

        em.remove(em.merge(entity));
    }

    @Override
    public List findByAttributes(Map attributes) {
        return null;
    }
}
