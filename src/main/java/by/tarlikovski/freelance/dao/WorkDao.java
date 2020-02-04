package by.tarlikovski.freelance.dao;

import by.tarlikovski.freelance.bean.OrderProperty;
import by.tarlikovski.freelance.bean.User;
import by.tarlikovski.freelance.bean.Work;
import by.tarlikovski.freelance.exception.DAOException;

import java.util.List;

public interface WorkDao extends Dao<Work> {
    List<Work> findByUser(User user) throws DAOException;

    List<OrderProperty> findByOrder(OrderProperty ordProp) throws DAOException;
}
