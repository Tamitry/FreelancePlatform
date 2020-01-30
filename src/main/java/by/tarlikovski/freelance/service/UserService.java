package by.tarlikovski.freelance.service;

import by.tarlikovski.freelance.bean.User;
import by.tarlikovski.freelance.exception.PersistentException;

import java.util.List;

public interface UserService {
    void userRegistration(User user) throws PersistentException;

    void deleteUser(int i) throws PersistentException;

    void changeParameters(User user) throws PersistentException;

    List<User> findAll() throws PersistentException;

    List<User> findByName(String name) throws PersistentException;

    User findByLogin(String login) throws PersistentException;
}
