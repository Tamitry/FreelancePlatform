package by.tarlikovski.freelance.service;

import by.tarlikovski.freelance.bean.Type;
import by.tarlikovski.freelance.bean.User;
import by.tarlikovski.freelance.dao.UserDao;
import by.tarlikovski.freelance.exception.DAOException;
import by.tarlikovski.freelance.exception.PersistentException;

import java.util.List;

public class UserServiceImpl extends ServiceImpl implements UserService {

    @Override
    public void userRegistration(final User user) throws PersistentException {
        try {
            UserDao userDao = (UserDao) transaction.createDao(Type.USER_DAO);
            userDao.create(user);
        } catch (DAOException ex) {
            throw new PersistentException(ex);
        }
    }

    @Override
    public void deleteUser(final int i) throws PersistentException {
        try {
            UserDao userDao = (UserDao) transaction.createDao(Type.USER_DAO);
            userDao.delete(i);
        } catch (DAOException e) {
            throw new PersistentException(e);
        }
    }

    @Override
    public void changeParameters(final User user) throws PersistentException {
        try {
            UserDao userDao = (UserDao) transaction.createDao(Type.USER_DAO);
            userDao.update(user);
        } catch (DAOException ex) {
            throw new PersistentException(ex);
        }
    }

    @Override
    public List<User> findAll() throws PersistentException {
        try {
            UserDao userDao = (UserDao) transaction.createDao(Type.USER_DAO);
            return userDao.findAll();
        } catch (DAOException ex) {
            throw new PersistentException(ex);
        }
    }

    @Override
    public List<User> findByName(final String name) throws PersistentException {
        try {
            UserDao userDao = (UserDao) transaction.createDao(Type.USER_DAO);
            return userDao.findByFullName(name);
        } catch (DAOException ex) {
            throw new PersistentException(ex);
        }
    }

    @Override
    public User findByLogin(final String login) throws PersistentException {
        try {
            UserDao userDao = (UserDao) transaction.createDao(Type.USER_DAO);
            return userDao.findByLogin(login);
        } catch (DAOException ex) {
            throw new PersistentException(ex);
        }
    }
}
