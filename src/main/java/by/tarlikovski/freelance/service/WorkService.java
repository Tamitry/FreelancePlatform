package by.tarlikovski.freelance.service;

import by.tarlikovski.freelance.bean.Order;
import by.tarlikovski.freelance.bean.User;
import by.tarlikovski.freelance.bean.Work;

import java.util.List;
import java.util.Optional;

public interface WorkService extends Service {
    List<Work> findByUser(User user) throws ServiceException;

    List<Work> findByOrder(Order order) throws ServiceException;

    int update(Work work) throws ServiceException;

    int create(Work work) throws ServiceException;

    int delete(int id) throws ServiceException;

    Optional<Work> read(int id) throws ServiceException;
}
