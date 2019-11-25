package by.tarlikovski.freelance.dao;

import by.tarlikovski.freelance.errors.PersistentException;

public interface Transaction {
    <Type extends DAO> Type createDao(final String key)
            throws PersistentException;

    void commit() throws PersistentException;

    void rollback() throws PersistentException;
}
