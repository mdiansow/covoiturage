package fr.istic.m2gla.server;

/**
 * Created by root on 11/10/14.
 */

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * The Interface IDao.
 *
 * @param <T> the generic type
 */
public interface IDao<T> {

    /**
     * set the entity manager factory to the dao.
     *
     * @param em : the entity manager
     */
    public void setEm(EntityManager em);

    /**
     * Gets the entity manager for the database.
     *
     * @return the em
     */
    public EntityManager getEm();

    /**
     * Find an entity by id.
     *
     * @param id the id to look for
     * @return the entity
     */
    public T findById(Integer id);

    /**
     * Find all entities of type T.
     *
     * @return the list of all entities
     */
    public List<T> findAll();

    /**
     * Creates (persists) the entity.
     *
     * @param entity the entity to persist
     */
    public void create(T entity);

    /**
     * Delete the entity.
     *
     * @param entity the entity to delete
     */
    public void delete(T entity);

    /**
     * Update the entity.
     *
     * @param entity the entity to update
     * @return the updated entity
     */
    public T update(T entity);

    /**
     * Find entities by attributes.
     *
     * @param attributes the attributes to match
     * @return the list of corresponding entities
     */
    public List<T> findByAttributes(Map<String, String> attributes);

}