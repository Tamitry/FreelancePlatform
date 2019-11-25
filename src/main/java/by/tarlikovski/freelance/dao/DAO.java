package by.tarlikovski.freelance.dao;

import by.tarlikovski.freelance.entities.Entity;
import by.tarlikovski.freelance.errors.PersistentException;

import java.util.List;

/**
 * General interface for all data access objects.
 *
 * @author Tarlikovski
 * @since 16.11.2019
 * @version 1.0
 * @param <K> key to the table.
 * @param <T> entity.
 */
public interface DAO<K, T extends Entity> {
    /**
     * Return all entities from the table.
     *
     * @return list of entities.
     */
    List<T> findAll() throws PersistentException;

    /**
     * Find entity by the id.
     *
     * @param id id of the entity.
     * @return the entity.
     */
    T findEntityById(final K id) throws PersistentException;

    /**
     * Delete entity by id.
     *
     * @param id id to delete.
     * @return was the item deleted.
     */
    boolean delete(final K id) throws PersistentException;

    /**
     * Create an entity.
     *
     * @param item to create.
     * @return was the item created.
     */
    boolean create(final T item) throws PersistentException;

    /**
     * Update the item.
     * @param item item to update.
     * @return item.
     */
    void update(final T item) throws PersistentException;
}
