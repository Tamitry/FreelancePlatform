package by.tarlikovski.freelance.service.impl;

import by.tarlikovski.freelance.bean.Type;
import by.tarlikovski.freelance.bean.User;
import by.tarlikovski.freelance.dao.UserDao;
import by.tarlikovski.freelance.dao.DAOException;
import by.tarlikovski.freelance.service.ServiceException;
import by.tarlikovski.freelance.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl extends ServiceImpl implements UserService {

    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);

    @Override
    public int userRegistration(final User user) throws ServiceException {
        try {
            UserDao userDao = (UserDao) transaction.createDao(Type.USER_DAO);
            int id = userDao.create(user);
            transaction.commit();
            return id;
        } catch (DAOException e) {
            try {
                transaction.rollback();
            } catch (DAOException ex) {
                LOGGER.error("Rollback has not been done." + ex);
                throw new ServiceException(ex);
            }
            LOGGER.error(e);
            throw new ServiceException(e);
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
            try {
                transaction.rollback();
            } catch (DAOException ex) {
                LOGGER.error("Rollback has not been done." + ex);
                throw new ServiceException(ex);
            }
            LOGGER.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public int update(final User user) throws ServiceException {
        try {
            int i;
            UserDao userDao = (UserDao) transaction.createDao(Type.USER_DAO);
            i = userDao.update(user);
            transaction.commit();
            return i;
        } catch (DAOException e) {
            try {
                transaction.rollback();
            } catch (DAOException ex) {
                LOGGER.error("Rollback has not been done." + ex);
                throw new ServiceException(ex);
            }
            LOGGER.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> findAllFreelancers() throws ServiceException {
        try {
            UserDao userDao = (UserDao) transaction.createDao(Type.USER_DAO);
            List<User> users = userDao.findAllFreelancers();
            transaction.commit();
            return users;
        } catch (DAOException e) {
            try {
                transaction.rollback();
            } catch (DAOException ex) {
                LOGGER.error("Rollback has not been done." + ex);
                throw new ServiceException(ex);
            }
            LOGGER.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> findByName(final String name) throws ServiceException {
        try {
            UserDao userDao = (UserDao) transaction.createDao(Type.USER_DAO);
            List<User> users = userDao.findByFullName(name);
            transaction.commit();
            return users;
        } catch (DAOException e) {
            try {
                transaction.rollback();
            } catch (DAOException ex) {
                LOGGER.error("Rollback has not been done." + ex);
                throw new ServiceException(ex);
            }
            LOGGER.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<User> findByLogin(final String login) throws ServiceException {
        try {
            UserDao userDao = (UserDao) transaction.createDao(Type.USER_DAO);
            Optional<User> user = userDao.findByLogin(login);
            transaction.commit();
            return user;
        } catch (DAOException e) {
            try {
                transaction.rollback();
            } catch (DAOException ex) {
                LOGGER.error("Rollback has not been done." + ex);
                throw new ServiceException(ex);
            }
            LOGGER.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<User> findByEmail(final String email) throws ServiceException {
        try {
            UserDao userDao = (UserDao) transaction.createDao(Type.USER_DAO);
            Optional<User> user = userDao.findByEmail(email);
            transaction.commit();
            return user;
        } catch (DAOException e) {
            try {
                transaction.rollback();
            } catch (DAOException ex) {
                LOGGER.error("Rollback has not been done." + ex);
                throw new ServiceException(ex);
            }
            LOGGER.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<User> read(final int id) throws ServiceException {
        try {
            UserDao userDao = (UserDao) transaction.createDao(Type.USER_DAO);
            Optional<User> user = userDao.read(id);
            transaction.commit();
            return user;
        } catch (DAOException e) {
            try {
                transaction.rollback();
            } catch (DAOException ex) {
                LOGGER.error("Rollback has not been done." + ex);
                throw new ServiceException(ex);
            }
            LOGGER.error(e);
            throw new ServiceException(e);
        }
    }
}
