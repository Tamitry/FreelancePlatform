package by.tarlikovski.freelance.service;

import by.tarlikovski.freelance.bean.Order;
import by.tarlikovski.freelance.bean.User;

import java.util.List;
import java.util.Optional;

public interface OrderService extends Service {
    List<Order> findByName(final String name) throws ServiceException;

    List<Order> findByUser(final User user) throws ServiceException;

    List<Order> findAll() throws ServiceException;

    Optional<Order> read(final int id) throws ServiceException;

    int create(final Order order) throws ServiceException;

    int delete(final int id) throws ServiceException;

    int update(final Order order) throws ServiceException;
}
