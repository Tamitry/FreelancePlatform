package by.tarlikovski.freelance.service;

public interface PasswordEncoder extends Service {
    String encode(String pass) throws ServiceException;

    String encode(String pass, String salt) throws ServiceException;
}
