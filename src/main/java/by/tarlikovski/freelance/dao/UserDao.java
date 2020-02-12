package by.tarlikovski.freelance.dao;

import by.tarlikovski.freelance.bean.User;

import java.util.List;
import java.util.Optional;

public interface UserDao extends Dao<User> {
    List<User> findByFullName(String name) throws DAOException;

    Optional<User> findByLogin(String lg) throws DAOException;

    List<User> findAllFreelancers() throws DAOException;

    Optional<User> findByEmail(String email) throws DAOException;
}
