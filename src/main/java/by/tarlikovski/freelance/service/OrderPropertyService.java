package by.tarlikovski.freelance.service;

import by.tarlikovski.freelance.bean.Category;
import by.tarlikovski.freelance.bean.Order;

import java.util.List;

public interface OrderPropertyService extends Service {
    List<Category> findByOrder(Order order) throws ServiceException;

    List<Order> findByCategory(Category category) throws ServiceException;

    int create(Order order, Category category) throws ServiceException;

    int delete(Order order, Category category) throws ServiceException;
}
