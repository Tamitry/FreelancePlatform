package by.tarlikovski.freelance.dao.mysql;

import by.tarlikovski.freelance.dao.Transaction;
import by.tarlikovski.freelance.dao.TransactionFactory;
import by.tarlikovski.freelance.dao.pool.ConnectionPool;
import by.tarlikovski.freelance.errors.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.locks.ReentrantLock;

public class TransactionFactoryImpl implements TransactionFactory {
    private static final Logger LOGGER
            = LogManager.getLogger(TransactionFactoryImpl.class);
    private Connection connection;

    public TransactionFactoryImpl() throws PersistentException {
        ReentrantLock lock = new ReentrantLock();
        connection = ConnectionPool.getInstance().getConnection(lock);
        try {
            connection.setAutoCommit(false);
        } catch(SQLException e) {
            LOGGER.error("It is impossible to turn off autocommiting for database connection", e);
            throw new PersistentException(e);
        }
    }

    @Override
    public Transaction createTransaction() throws PersistentException {
        return new TransactionImpl(connection);
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch(SQLException e) {
            LOGGER.error(e);
        }
    }
}
