package by.tarlikovski.freelance.dao;

import by.tarlikovski.freelance.bean.Entity;

import java.util.Optional;

public interface Dao<Type extends Entity> {
    int create(Type entity) throws DAOException;

    Optional<Type> read(Integer identity) throws DAOException;

    int update(Type entity) throws DAOException;

    int delete(Integer identity) throws DAOException;
}
