package by.tarlikovski.freelance.services;

import by.tarlikovski.freelance.dao.CategoriesDAO;
import by.tarlikovski.freelance.entities.Category;
import by.tarlikovski.freelance.errors.PersistentException;

import java.util.List;

public class CategoriesServiceServiceImpl extends ServiceImpl implements CategoriesService {

    @Override
    public List<Category> findAllCategories() throws PersistentException {
        CategoriesDAO categoriesDAO = getTransaction().createDao("CategoriesDAO");
        return categoriesDAO.findAll();
    }
}
