package by.tarlikovski.freelance.service;

import by.tarlikovski.freelance.bean.User;

public interface Validator<Type> {
    boolean validate(Type type) throws ServiceException;
}
