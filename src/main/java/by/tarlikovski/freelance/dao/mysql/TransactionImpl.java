package by.tarlikovski.freelance.dao.mysql;

import by.tarlikovski.freelance.dao.DAO;
import by.tarlikovski.freelance.dao.Transaction;
import by.tarlikovski.freelance.errors.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionImpl implements Transaction {
    private static final Logger LOGGER = LogManager
            .getLogger(TransactionImpl.class);
    private Connection connection;

    public TransactionImpl(final Connection connect) {
        connection = connect;
    }

    @Override
    public DAO createDao(final String key)
            throws PersistentException {
        switch (key) {
            case "UserDAO":
                return new UserDAOImpl();
            case "OrderDAO":
                return new OrderDAOImpl();
            case "OrderCategoryDAO":
                return new OrderCategoryDAOImpl();
            case "CategoriesDAO":
                return new CategoriesDAOImpl();
            default:
                throw new PersistentException("");
        }
    }

    @Override
    public void commit() throws PersistentException {
        try {
            connection.commit();
        } catch (SQLException ex) {
            LOGGER.error(ex);
            throw new PersistentException(ex);
        }
    }

    @Override
    public void rollback() throws PersistentException {
        try {
            connection.rollback();
        } catch (SQLException ex) {
            LOGGER.error(ex);
            throw new PersistentException(ex);
        }
    }
}
