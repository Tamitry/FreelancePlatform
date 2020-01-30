package by.tarlikovski.freelance.dao.transaction.Impl;

import by.tarlikovski.freelance.bean.Type;
import by.tarlikovski.freelance.dao.Dao;
import by.tarlikovski.freelance.dao.mysql.UserDaoImpl;
import by.tarlikovski.freelance.dao.transaction.Transaction;
import by.tarlikovski.freelance.exception.DAOException;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionImpl implements Transaction {

    private Connection connection;

    public TransactionImpl(final Connection conn) {
        connection = conn;
    }

    @Override
    public Dao<?> createDao(Type type) throws DAOException {
        switch (type) {
            case USER_DAO:
                UserDaoImpl userDao = new UserDaoImpl();
                userDao.setConnection(connection);
                return userDao;
            //case OrderDao:
            //    break;
            default:
                throw new DAOException("Unknown DAO class.");
        }
    }

    @Override
    public void commit() throws DAOException {
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void rollback() throws DAOException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
}
