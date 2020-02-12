package by.tarlikovski.freelance.service;

import by.tarlikovski.freelance.bean.Order;
import by.tarlikovski.freelance.bean.User;
import by.tarlikovski.freelance.bean.Work;

import java.util.List;

public interface WorkService extends Service {
    List<Order> findByUser(User user) throws ServiceException;

    List<User> findByOrder(Order order) throws ServiceException;

    int create(Work work) throws ServiceException;

    int delete(Work work) throws ServiceException;
}
