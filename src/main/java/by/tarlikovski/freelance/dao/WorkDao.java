package by.tarlikovski.freelance.dao;

import by.tarlikovski.freelance.bean.Order;
import by.tarlikovski.freelance.bean.User;
import by.tarlikovski.freelance.bean.Work;

import java.util.List;

public interface WorkDao extends Dao<Work> {
    List<Work> findByUser(User user) throws DAOException;

    List<Work> findByOrder(Order order) throws DAOException;
}
