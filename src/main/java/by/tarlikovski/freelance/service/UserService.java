package by.tarlikovski.freelance.service;

import by.tarlikovski.freelance.bean.User;
import by.tarlikovski.freelance.exception.PersistentException;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void userRegistration(User user) throws PersistentException;

    void deleteUser(int i) throws PersistentException;

    void changeParameters(User user) throws PersistentException;

    List<User> findAllFreelancers() throws PersistentException;

    List<User> findByName(String name) throws PersistentException;

    Optional<User> findByLogin(String login) throws PersistentException;

    Optional<User> findByEmail(String email) throws PersistentException;

    Optional<User> findById(int id) throws PersistentException;
}
