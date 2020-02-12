package by.tarlikovski.freelance.dao;

import by.tarlikovski.freelance.bean.Category;
import by.tarlikovski.freelance.bean.Order;
import by.tarlikovski.freelance.bean.OrderProperty;

import java.util.List;

public interface OrderPropertyDao extends Dao<OrderProperty> {
    List<OrderProperty> findByOrder(Order order) throws DAOException;

    List<OrderProperty> findByCategory(Category category) throws DAOException;
}
