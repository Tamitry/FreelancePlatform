package by.tarlikovski.freelance.dao.transaction.Impl;

import by.tarlikovski.freelance.dao.connectionpool.ConnectionPool;
import by.tarlikovski.freelance.dao.transaction.Transaction;
import by.tarlikovski.freelance.dao.transaction.TransactionFactory;
import by.tarlikovski.freelance.exception.DAOException;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionFactoryImpl implements TransactionFactory {
    private Connection connection;

    public static void init() throws DAOException {
        ConnectionPool.getInstance().init();
    }

    public TransactionFactoryImpl() throws DAOException {
        connection = ConnectionPool.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public Transaction createTransaction() throws DAOException {
        return new TransactionImpl(connection);
    }

    @Override
    public void close() throws DAOException {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
}
