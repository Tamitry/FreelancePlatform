package by.tarlikovski.freelance.service;

public interface PasswordEncoder extends Service {
    boolean check(final String password, final String stored) throws ServiceException;

    String getSaltedHash(final String password) throws ServiceException;
}
