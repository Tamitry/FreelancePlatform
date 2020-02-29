package by.tarlikovski.freelance.service;

public interface Validator<Type> {
    boolean validate(Type type) throws ServiceException;
}
