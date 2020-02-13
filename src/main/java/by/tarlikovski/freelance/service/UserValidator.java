package by.tarlikovski.freelance.service;

import by.tarlikovski.freelance.bean.User;

public interface UserValidator {
    boolean validate(User user) throws ServiceException;
}
