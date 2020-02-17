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
import java.util.Optional;

public class WorkServiceImpl extends ServiceImpl implements WorkService {
    @Override
    public List<Work> findByUser(final User user)
            throws ServiceException {
        try {
            WorkDao workDao = (WorkDao) transaction.createDao(Type.WORK_DAO);
            OrderDao orderDao = (OrderDao) transaction.createDao(Type.ORDER_DAO);
            UserDao userDao = (UserDao) transaction.createDao(Type.USER_DAO);
            List<Work> list = workDao.findByUser(user);
            List<Work> works = new ArrayList<>();
            for (Work work : list) {
                work.setUser(user);
                Order order = orderDao.read(work.getOrder().getId()).get();
                order.setClient(userDao.read(order.getClient().getId()).get());
                work.setOrder(order);
                works.add(work);
            }
            transaction.commit();
            return works;
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Work> findByOrder(final Order order)
            throws ServiceException {
        try {
            WorkDao workDao = (WorkDao) transaction.createDao(Type.WORK_DAO);
            UserDao userDao = (UserDao) transaction.createDao(Type.USER_DAO);
            List<Work> list = workDao.findByOrder(order);
            List<Work> works = new ArrayList<>();
            for (Work work : list) {
                work.setOrder(order);
                work.setUser(userDao
                        .read(work.getUser().getId()).get());
                works.add(work);
            }
            transaction.commit();
            return works;
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int update(final Work work) throws ServiceException {
        try {
            WorkDao workDao = (WorkDao) transaction.createDao(Type.WORK_DAO);
            int v = workDao.update(work);
            transaction.commit();
            return v;
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
    public int delete(final int id)
            throws ServiceException {
        try {
            int i;
            WorkDao workDao = (WorkDao) transaction.createDao(Type.WORK_DAO);
            i = workDao.delete(id);
            transaction.commit();
            return i;
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<Work> read(final int id)
            throws ServiceException {
        try {
            int i;
            WorkDao workDao = (WorkDao) transaction.createDao(Type.WORK_DAO);
            Optional<Work> work = workDao.read(id);
            transaction.commit();
            return work;
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
