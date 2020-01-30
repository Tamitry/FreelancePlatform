package by.tarlikovski.freelance.dao;

import by.tarlikovski.freelance.bean.User;
import by.tarlikovski.freelance.exception.DAOException;

import java.util.List;

public interface UserDao extends Dao<User> {
    List<User> findByFullName(String name) throws DAOException;

    User findByLogin(String lg) throws DAOException;

    List<User> findAll() throws DAOException;
}
