package by.tarlikovski.freelance.dao;

import by.tarlikovski.freelance.bean.Order;
import by.tarlikovski.freelance.bean.User;

import java.util.List;

public interface OrderDao extends Dao<Order> {
    List<Order> findAll() throws DAOException;
    List<Order> findByUser(User user) throws DAOException;
    List<Order> findByName(String name) throws DAOException;
}
