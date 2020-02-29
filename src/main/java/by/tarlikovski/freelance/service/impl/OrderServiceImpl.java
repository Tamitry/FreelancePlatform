package by.tarlikovski.freelance.service.impl;

import by.tarlikovski.freelance.bean.Order;
import by.tarlikovski.freelance.bean.Type;
import by.tarlikovski.freelance.bean.User;
import by.tarlikovski.freelance.dao.DAOException;
import by.tarlikovski.freelance.dao.OrderDao;
import by.tarlikovski.freelance.dao.UserDao;
import by.tarlikovski.freelance.service.OrderService;
import by.tarlikovski.freelance.service.ServiceException;
import by.tarlikovski.freelance.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class OrderServiceImpl extends ServiceImpl implements OrderService {

    private static final Logger LOGGER = LogManager.getLogger(OrderServiceImpl.class);

    @Override
    public List<Order> findByName(final String name)
            throws ServiceException {
        try {
            OrderDao orderDao = (OrderDao) transaction.createDao(Type.ORDER_DAO);
            UserDao userDao = (UserDao) transaction.createDao(Type.USER_DAO);
            List<Order> orders = orderDao.findByName(name);
            for (Order order : orders) {
                order.setClient(userDao.read(order.getClient().getId()).get());
            }
            transaction.commit();
            return orders;
        } catch (DAOException e) {
            try {
                transaction.rollback();
            } catch (DAOException ex) {
                LOGGER.debug("Rollback has not been done." + ex);
                throw new ServiceException(ex);
            }
            LOGGER.debug(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> findByUser(final User user)
            throws ServiceException {
        try {
            OrderDao orderDao = (OrderDao) transaction.createDao(Type.ORDER_DAO);
            List<Order> orders = orderDao.findByUser(user);
            transaction.commit();
            return orders;
        } catch (DAOException e) {
            try {
                transaction.rollback();
            } catch (DAOException ex) {
                LOGGER.debug("Rollback has not been done." + ex);
                throw new ServiceException(ex);
            }
            LOGGER.debug(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> findAll() throws ServiceException {
        try {
            OrderDao orderDao = (OrderDao) transaction.createDao(Type.ORDER_DAO);
            UserDao userDao = (UserDao) transaction.createDao(Type.USER_DAO);
            List<Order> orders = orderDao.findAll();
            for (Order order : orders) {
                order.setClient(userDao.read(order.getClient().getId()).get());
            }
            transaction.commit();
            return orders;
        } catch (DAOException e) {
            try {
                transaction.rollback();
            } catch (DAOException ex) {
                LOGGER.debug("Rollback has not been done." + ex);
                throw new ServiceException(ex);
            }
            LOGGER.debug(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> findNewest(final int days)
            throws ServiceException {
        try {
            UserDao userDao = (UserDao) transaction.createDao(Type.USER_DAO);
            OrderDao orderDao = (OrderDao) transaction.createDao(Type.ORDER_DAO);
            List<Order> orders = orderDao.findNewest(days);
            for (Order order : orders) {
                order.setClient(userDao.read(order.getClient().getId()).get());
            }
            transaction.commit();
            return orders;
        } catch (DAOException e) {
            try {
                transaction.rollback();
            } catch (DAOException ex) {
                LOGGER.debug("Rollback has not been done." + ex);
                throw new ServiceException(ex);
            }
            LOGGER.debug(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<Order> read(final int id)
            throws ServiceException {
        try {
            OrderDao orderDao = (OrderDao) transaction.createDao(Type.ORDER_DAO);
            UserDao userDao = (UserDao) transaction.createDao(Type.USER_DAO);
            Order order = orderDao.read(id).get();
            order.setClient(userDao.read(order.getClient().getId()).get());
            transaction.commit();
            return Optional.ofNullable(order);
        } catch (DAOException e) {
            try {
                transaction.rollback();
            } catch (DAOException ex) {
                LOGGER.debug("Rollback has not been done." + ex);
                throw new ServiceException(ex);
            }
            LOGGER.debug(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public int create(final Order order)
            throws ServiceException {
        try {
            OrderDao orderDao = (OrderDao) transaction.createDao(Type.ORDER_DAO);
            int i = orderDao.create(order);
            transaction.commit();
            return i;
        } catch (DAOException e) {
            try {
                transaction.rollback();
            } catch (DAOException ex) {
                LOGGER.debug("Rollback has not been done." + ex);
                throw new ServiceException(ex);
            }
            LOGGER.debug(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public int delete(final int id)
            throws ServiceException {
        try {
            OrderDao orderDao = (OrderDao) transaction.createDao(Type.ORDER_DAO);
            int i = orderDao.delete(id);
            transaction.commit();
            return i;
        } catch (DAOException e) {
            try {
                transaction.rollback();
            } catch (DAOException ex) {
                LOGGER.debug("Rollback has not been done." + ex);
                throw new ServiceException(ex);
            }
            LOGGER.debug(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public int update(final Order order)
            throws ServiceException {
        try {
            OrderDao orderDao = (OrderDao) transaction.createDao(Type.ORDER_DAO);
            int i = orderDao.update(order);
            transaction.commit();
            return i;
        } catch (DAOException e) {
            try {
                transaction.rollback();
            } catch (DAOException ex) {
                LOGGER.debug("Rollback has not been done." + ex);
                throw new ServiceException(ex);
            }
            LOGGER.debug(e);
            throw new ServiceException(e);
        }
    }
}
