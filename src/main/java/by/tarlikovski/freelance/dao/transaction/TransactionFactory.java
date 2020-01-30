package by.tarlikovski.freelance.dao.transaction;

import by.tarlikovski.freelance.exception.DAOException;
import by.tarlikovski.freelance.exception.PersistentException;

public interface TransactionFactory {
    Transaction createTransaction() throws DAOException;

    void close() throws DAOException;
}
