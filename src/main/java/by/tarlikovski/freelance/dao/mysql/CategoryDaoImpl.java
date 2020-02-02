package by.tarlikovski.freelance.dao.mysql;

import by.tarlikovski.freelance.bean.Category;
import by.tarlikovski.freelance.bean.User;
import by.tarlikovski.freelance.dao.CategoryDao;
import by.tarlikovski.freelance.exception.DAOException;

import java.util.List;
import java.util.Optional;

public class CategoryDaoImpl implements CategoryDao {
    @Override
    public List<Category> findAll() {
        return null;
    }

    @Override
    public List<Category> findSkills(final User user) {
        return null;
    }

    @Override
    public Integer create(Category entity) throws DAOException {
        throw new DAOException(new UnsupportedOperationException("Сategory creation should be done in the database console."));
    }

    @Override
    public Optional<User> read(Integer identity) throws DAOException {
        return Optional.empty();
    }

    @Override
    public void update(Category entity) throws DAOException {
        throw new DAOException(new UnsupportedOperationException("Сategory update should be done in the database console."));
    }

    @Override
    public void delete(final Integer identity) throws DAOException {
        throw new DAOException(new UnsupportedOperationException("Сategory deletion should be done in the database console."));
    }
}
