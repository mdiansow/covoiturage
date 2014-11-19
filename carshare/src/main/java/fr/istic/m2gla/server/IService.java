package fr.istic.m2gla.server;

import java.util.Collection;

/**
 * Created by mds on 10/11/14.
 */
public interface IService<T> {
    public T findById(long id);

    /**
     * Find all entities of type T.
     *
     * @return the list of all entities
     */
    public Collection<T> findAll();

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

}
