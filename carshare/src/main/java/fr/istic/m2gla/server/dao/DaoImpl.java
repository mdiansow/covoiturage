package fr.istic.m2gla.server.dao;

import fr.istic.m2gla.shared.Person;
import org.hibernate.exception.ConstraintViolationException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import java.util.Collection;

/**
 * Created by mds on 02/02/15.
 */
public class DaoImpl<T> implements IDao<T> {

    public DaoImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
        em = PersistenceManager.getInstance().getEntityManagerFactory().createEntityManager();
    }

    /**
     * The class of the entity to be manipulated.
     */
    protected Class<T> entityClass;
    protected EntityManager em;

    @Override
    public T findById(long id) {
        if (id < 0) {
            throw new PersistenceException("Id may not be null or negative");
        }
        return em.find(entityClass, id);
    }

    @Override
    public Collection findAll() {
        System.out.println("L'entité " + entityClass);
        String req = "select e from " + entityClass.getName() + " as e";
        return em.createQuery(req).getResultList();
    }

    @Override
    public void create(T entity) {
        if (entity == null)
            throw new PersistenceException("Entity to persist may not be null");//throw Persistence exception
        EntityTransaction t = em.getTransaction();
        try {
            t.begin();
            em.persist(entity);
            t.commit();
        } catch (ConstraintViolationException e) {
            System.out.println("Constraint violation " + e.getConstraintName() + "  " + e.getMessage());
        } catch (Exception e) {
            System.out.println("error persist commande" + e.toString());
        }
    }

    @Override
    public void delete(long id) {
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
    public T update(T entity) {
        System.out.println("Create de AbstractDAO : " + entity.getClass().toString());
        if (entity == null)
            throw new PersistenceException("Entity to persist may not be null");//throw Persistence exception
        EntityTransaction t = em.getTransaction();
        t.begin();
        try {
            em.merge(entity);
            t.commit();
        } catch (Exception e) {
            System.out.println("error persist commande" + e.toString());
        }
        return entity;
    }
}
