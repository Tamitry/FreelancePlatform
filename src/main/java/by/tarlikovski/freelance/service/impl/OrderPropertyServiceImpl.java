package by.tarlikovski.freelance.service.impl;

import by.tarlikovski.freelance.bean.*;
import by.tarlikovski.freelance.dao.*;
import by.tarlikovski.freelance.service.OrderPropertyService;
import by.tarlikovski.freelance.service.ServiceException;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class OrderPropertyServiceImpl extends ServiceImpl implements OrderPropertyService {

    private static final Logger LOGGER = LogManager.getLogger(OrderPropertyServiceImpl.class);

    @Override
    public List<Category> findByOrder(final Order order)
            throws ServiceException {
        try {
            OrderPropertyDao ordPropDao = (OrderPropertyDao) transaction.createDao(Type.ORDER_PROPERTY_DAO);
            CategoryDao categoryDao = (CategoryDao) transaction.createDao(Type.CATEGORY_DAO);
            List<OrderProperty> orderProperties = ordPropDao.findByOrder(order);
            List<Category> categories = new ArrayList<>();
            for (OrderProperty orderProperty : orderProperties) {
                if (categoryDao.read(orderProperty.getCategoryId()).isPresent()) {
                    categories.add(categoryDao.read(orderProperty.getCategoryId()).get());
                }
            }
            transaction.commit();
            return categories;
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
    public List<Order> findByCategory(final Category category)
            throws ServiceException {
        try {
            OrderPropertyDao orderPropertyDao = (OrderPropertyDao) transaction.createDao(Type.ORDER_PROPERTY_DAO);
            UserDao userDao = (UserDao) transaction.createDao(Type.USER_DAO);
            OrderDao orderDao = (OrderDao) transaction.createDao(Type.ORDER_DAO);
            List<OrderProperty> orderProperties = orderPropertyDao.findByCategory(category);
            List<Order> orders = new ArrayList<>();
            for (OrderProperty orderProperty : orderProperties) {
                if (orderDao.read(orderProperty.getOrderId()).isPresent()) {
                    orders.add(orderDao.read(orderProperty.getOrderId()).get());
                }
            }
            int i;
            for (Order order : orders) {
                i = order.getClient().getId();
                order.setClient(userDao.read(i).get());
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
    public int create(final Order order,
                       final Category category)
            throws ServiceException {
        try {
            OrderPropertyDao orderPropertyDao = (OrderPropertyDao) transaction.createDao(Type.ORDER_PROPERTY_DAO);
            OrderProperty orderProperty = new OrderProperty();
            orderProperty.setOrderId(order.getId());
            orderProperty.setCategoryId(category.getId());
            int i = orderPropertyDao.create(orderProperty);
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
    public int delete(final Order order,
                       final Category category)
            throws ServiceException {
        try {
            int i = 0;
            OrderPropertyDao orderPropertyDao = (OrderPropertyDao) transaction.createDao(Type.ORDER_PROPERTY_DAO);
            List<OrderProperty> orders = orderPropertyDao.findByOrder(order);
            OrderProperty orderProperty = null;
            for (OrderProperty orderProp : orders) {
                if (orderProp.getCategoryId() == category.getId()) {
                    i += orderPropertyDao.delete(orderProp.getId());
                }
            }
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
