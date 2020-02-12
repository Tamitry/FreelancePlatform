package by.tarlikovski.freelance.service.impl;

import by.tarlikovski.freelance.bean.Category;
import by.tarlikovski.freelance.bean.Type;
import by.tarlikovski.freelance.dao.CategoryDao;
import by.tarlikovski.freelance.dao.DAOException;
import by.tarlikovski.freelance.service.ServiceException;
import by.tarlikovski.freelance.service.CategoryService;

import java.util.List;

public class CategoryServiceImpl extends ServiceImpl implements CategoryService {
    @Override
    public List<Category> findAll() throws ServiceException {
        try {
            CategoryDao categoryDao = (CategoryDao) transaction.createDao(Type.CATEGORY_DAO);
            transaction.commit();
            return categoryDao.findAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
