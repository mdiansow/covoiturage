package dao;

import model.Personne;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by root on 19/10/14.
 */
public class DAO implements IDao {
    private EntityManager em;
    private String nameClass;

    @Override
    public void setEm(EntityManager em, String nameClass) {
        this.em = em;
        this.nameClass = nameClass;

    }

    @Override
    public EntityManager getEm() {
        return this.em;
    }

    @Override
    public Serializable findById(Integer id) {
        if (nameClass.equals("Personne")) {
            Personne result = null;
            if (id == null || id < 1)
                throw new PersistenceException("Id may not be null or negative");
            result = em.find(Personne.class, id);
            return result;
        }
        return null;
    }

    @Override
    public List findAll() {
        if (nameClass.equals("Personne")) {
            List<Personne> resultList = em.createQuery("select a from m2gla_personne a",
                    Personne.class).getResultList();
            System.out.println("Nb of persons: " + resultList.size());
            for (Personne next : resultList) {
                System.out.println("next person: " + next);
            }
            return resultList;
        }
        return null;
    }

    @Override
    public void create(Serializable entity) {
        if (nameClass.equals("Personne")) {
            System.out.println("Create de AbstractDAO : "+entity.getClass().toString());
            if(entity == null)
                throw new PersistenceException("Entity to persist may not be null");//throw Persistence exception
            try {
                em.persist(entity);
            }
            catch(Exception e) {
                System.out.println("error persist commande" + e.toString());
            }
        }
    }

    @Override
    public void delete(Serializable entity) {
        if (entity == null)
            throw new PersistenceException("Entity to delete may not be null");

        em.remove(em.merge(entity));
    }

    @Override
    public Serializable update(Serializable entity) {
        return null;
    }

    @Override
    public List findByAttributes(Map attributes) {
        return null;
    }
}
