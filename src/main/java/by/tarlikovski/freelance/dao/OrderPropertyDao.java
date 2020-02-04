package by.tarlikovski.freelance.dao;

import by.tarlikovski.freelance.bean.Category;
import by.tarlikovski.freelance.bean.Order;
import by.tarlikovski.freelance.exception.DAOException;

import java.util.List;

public interface OrderPropertyDao extends Dao {
    List<Category> findCategories(Order order) throws DAOException;
}
