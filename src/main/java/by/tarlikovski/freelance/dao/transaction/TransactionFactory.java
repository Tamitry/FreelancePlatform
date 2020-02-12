package by.tarlikovski.freelance.dao.transaction;

import by.tarlikovski.freelance.dao.DAOException;

public interface TransactionFactory {
    Transaction createTransaction() throws DAOException;

    void close() throws DAOException;
}
