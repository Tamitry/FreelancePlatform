package by.tarlikovski.freelance.dao.transaction;

import by.tarlikovski.freelance.bean.Type;
import by.tarlikovski.freelance.dao.Dao;
import by.tarlikovski.freelance.exception.DAOException;

public interface Transaction {
    Dao<?> createDao(Type type) throws DAOException;

    void commit() throws DAOException;

    void rollback() throws DAOException;
}
