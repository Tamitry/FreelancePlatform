package by.tarlikovski.freelance.service;

import by.tarlikovski.freelance.bean.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    int userRegistration(User user) throws ServiceException;

    int deleteUser(int i) throws ServiceException;

    int changeParameters(User user) throws ServiceException;

    List<User> findAllFreelancers() throws ServiceException;

    List<User> findByName(String name) throws ServiceException;

    Optional<User> findByLogin(String login) throws ServiceException;

    Optional<User> findByEmail(String email) throws ServiceException;

    Optional<User> findById(int id) throws ServiceException;
}
