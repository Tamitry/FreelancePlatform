package by.tarlikovski.freelance.service.impl;

import by.tarlikovski.freelance.bean.Order;
import by.tarlikovski.freelance.bean.Type;
import by.tarlikovski.freelance.bean.User;
import by.tarlikovski.freelance.bean.Work;
import by.tarlikovski.freelance.dao.DAOException;
import by.tarlikovski.freelance.dao.OrderDao;
import by.tarlikovski.freelance.dao.UserDao;
import by.tarlikovski.freelance.dao.WorkDao;
import by.tarlikovski.freelance.service.ServiceException;
import by.tarlikovski.freelance.service.WorkService;

import java.util.ArrayList;
import java.util.List;

public class WorkServiceImpl extends ServiceImpl implements WorkService {
    @Override
    public List<Order> findByUser(final User user)
            throws ServiceException {
        try {
            WorkDao workDao = (WorkDao) transaction.createDao(Type.WORK_DAO);
            OrderDao orderDao = (OrderDao) transaction.createDao(Type.ORDER_DAO);
            List<Work> list = workDao.findByUser(user);
            List<Order> orders = new ArrayList<>();
            transaction.commit();
            for (Work work : list) {
                if (orderDao.read(work.getOrder().getId()).isPresent()) {
                    orders.add(orderDao.read(work.getOrder().getId()).get());
                }
            }
            return orders;
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> findByOrder(final Order order)
            throws ServiceException {
        try {
            WorkDao workDao = (WorkDao) transaction.createDao(Type.WORK_DAO);
            UserDao userDao = (UserDao) transaction.createDao(Type.USER_DAO);
            List<Work> list = workDao.findByOrder(order);
            List<User> users = new ArrayList<>();
            transaction.commit();
            for (Work work : list) {
                if (userDao.read(work.getOrder().getId()).isPresent()) {
                    users.add(userDao.read(work.getOrder().getId()).get());
                }
            }
            return users;
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int create(final Work work)
            throws ServiceException {
        try {
            int i;
            WorkDao workDao = (WorkDao) transaction.createDao(Type.WORK_DAO);
            i = workDao.create(work);
            transaction.commit();
            return i;
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int delete(final Work work)
            throws ServiceException {
        try {
            int i;
            WorkDao workDao = (WorkDao) transaction.createDao(Type.WORK_DAO);
            i = workDao.delete(work.getId());
            transaction.commit();
            return i;
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
