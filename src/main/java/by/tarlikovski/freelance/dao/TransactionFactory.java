package by.tarlikovski.freelance.dao;

import by.tarlikovski.freelance.errors.PersistentException;

public interface TransactionFactory {
    Transaction createTransaction() throws PersistentException;

    void close();
}
