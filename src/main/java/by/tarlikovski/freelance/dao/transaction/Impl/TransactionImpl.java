package by.tarlikovski.freelance.dao.transaction.Impl;

import by.tarlikovski.freelance.bean.Type;
import by.tarlikovski.freelance.dao.Dao;
import by.tarlikovski.freelance.dao.OrderPropertyDao;
import by.tarlikovski.freelance.dao.WorkDao;
import by.tarlikovski.freelance.dao.mysql.*;
import by.tarlikovski.freelance.dao.transaction.Transaction;
import by.tarlikovski.freelance.dao.DAOException;

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
            case SKILL_DAO:
                SkillDaoImpl skillDao = new SkillDaoImpl();
                skillDao.setConnection(connection);
                return skillDao;
            case CATEGORY_DAO:
                CategoryDaoImpl categoryDao = new CategoryDaoImpl();
                categoryDao.setConnection(connection);
                return categoryDao;
            case ORDER_PROPERTY_DAO:
                OrderPropertyDaoImpl ordPropDao = new OrderPropertyDaoImpl();
                ordPropDao.setConnection(connection);
                return ordPropDao;
            case WORK_DAO:
                WorkDaoImpl workDao = new WorkDaoImpl();
                workDao.setConnection(connection);
                return workDao;
            case ORDER_DAO:
                OrderDaoImpl orderDao = new OrderDaoImpl();
                orderDao.setConnection(connection);
                return orderDao;
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
