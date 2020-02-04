package by.tarlikovski.freelance.service;

import by.tarlikovski.freelance.bean.Category;
import by.tarlikovski.freelance.bean.Type;
import by.tarlikovski.freelance.dao.CategoryDao;
import by.tarlikovski.freelance.exception.DAOException;
import by.tarlikovski.freelance.exception.PersistentException;

import java.util.List;

public class CategoryServiceImpl extends ServiceImpl implements CategoryService {
    @Override
    public List<Category> findAll() throws PersistentException {
        try {
            CategoryDao categoryDao = (CategoryDao) transaction.createDao(Type.CATEGORY_DAO);
            transaction.commit();
            return categoryDao.findAll();
        } catch (DAOException e) {
            throw new PersistentException(e);
        }
    }
}
