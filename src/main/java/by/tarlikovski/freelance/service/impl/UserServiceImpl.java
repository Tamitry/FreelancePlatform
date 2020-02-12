package by.tarlikovski.freelance.service.impl;

import by.tarlikovski.freelance.bean.Type;
import by.tarlikovski.freelance.bean.User;
import by.tarlikovski.freelance.dao.UserDao;
import by.tarlikovski.freelance.dao.DAOException;
import by.tarlikovski.freelance.service.ServiceException;
import by.tarlikovski.freelance.service.UserService;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl extends ServiceImpl implements UserService {

    @Override
    public int userRegistration(final User user) throws ServiceException {
        try {
            UserDao userDao = (UserDao) transaction.createDao(Type.USER_DAO);
            int id = userDao.create(user);
            transaction.commit();
            return id;
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public int deleteUser(final int id) throws ServiceException {
        try {
            int i;
            UserDao userDao = (UserDao) transaction.createDao(Type.USER_DAO);
            i = userDao.delete(id);
            transaction.commit();
            return i;
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int changeParameters(final User user) throws ServiceException {
        try {
            int i;
            UserDao userDao = (UserDao) transaction.createDao(Type.USER_DAO);
            i = userDao.update(user);
            transaction.commit();
            return i;
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public List<User> findAllFreelancers() throws ServiceException {
        try {
            UserDao userDao = (UserDao) transaction.createDao(Type.USER_DAO);
            List<User> users = userDao.findAllFreelancers();
            transaction.commit();
            return users;
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public List<User> findByName(final String name) throws ServiceException {
        try {
            UserDao userDao = (UserDao) transaction.createDao(Type.USER_DAO);
            List<User> users = userDao.findByFullName(name);
            transaction.commit();
            return users;
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public Optional<User> findByLogin(final String login) throws ServiceException {
        try {
            UserDao userDao = (UserDao) transaction.createDao(Type.USER_DAO);
            Optional<User> user = userDao.findByLogin(login);
            transaction.commit();
            return user;
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public Optional<User> findByEmail(final String email) throws ServiceException {
        try {
            UserDao userDao = (UserDao) transaction.createDao(Type.USER_DAO);
            Optional<User> user = userDao.findByEmail(email);
            transaction.commit();
            return user;
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public Optional<User> findById(final int id) throws ServiceException {
        try {
            UserDao userDao = (UserDao) transaction.createDao(Type.USER_DAO);
            Optional<User> user = userDao.read(id);
            transaction.commit();
            return user;
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }
}
