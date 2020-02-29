package by.tarlikovski.freelance.service.impl;

import by.tarlikovski.freelance.bean.Category;
import by.tarlikovski.freelance.bean.Type;
import by.tarlikovski.freelance.dao.CategoryDao;
import by.tarlikovski.freelance.dao.DAOException;
import by.tarlikovski.freelance.service.ServiceException;
import by.tarlikovski.freelance.service.CategoryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CategoryServiceImpl extends ServiceImpl implements CategoryService {

    private static final Logger LOGGER = LogManager.getLogger(CategoryServiceImpl.class);

    @Override
    public List<Category> findAll() throws ServiceException {
        try {
            CategoryDao categoryDao = (CategoryDao) transaction.createDao(Type.CATEGORY_DAO);
            transaction.commit();
            return categoryDao.findAll();
        } catch (DAOException e) {
            try {
                transaction.rollback();
            } catch (DAOException ex) {
                LOGGER.debug("Rollback has not been done." + ex);
                throw new ServiceException(ex);
            }
            LOGGER.debug(e);
            throw new ServiceException(e);
        }
    }
}
