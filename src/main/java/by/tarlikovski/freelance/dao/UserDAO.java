package by.tarlikovski.freelance.dao;

import by.tarlikovski.freelance.entities.Role;
import by.tarlikovski.freelance.entities.User;
import by.tarlikovski.freelance.errors.PersistentException;

import java.util.List;

public interface UserDAO extends DAO<Integer, User> {
    List<User> findByFullName(final String name) throws PersistentException;
    List<User> findFreelancers() throws PersistentException;
}
