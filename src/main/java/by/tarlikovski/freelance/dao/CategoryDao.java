package by.tarlikovski.freelance.dao;

import by.tarlikovski.freelance.bean.Category;

import java.util.List;

public interface CategoryDao extends Dao<Category> {
    List<Category> findAll() throws DAOException;
}
