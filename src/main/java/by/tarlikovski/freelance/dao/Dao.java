package by.tarlikovski.freelance.dao;

import by.tarlikovski.freelance.bean.Entity;
import by.tarlikovski.freelance.exception.DAOException;

public interface Dao<Type extends Entity> {
    Integer create(Type entity) throws DAOException;

    Type read(Integer identity) throws DAOException;

    void update(Type entity) throws DAOException;

    void delete(Integer identity) throws DAOException;
}
