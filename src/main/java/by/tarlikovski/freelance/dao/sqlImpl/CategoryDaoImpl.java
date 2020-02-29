package by.tarlikovski.freelance.dao.sqlImpl;

import by.tarlikovski.freelance.bean.Category;
import by.tarlikovski.freelance.dao.CategoryDao;
import by.tarlikovski.freelance.dao.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoryDaoImpl extends BaseDaoImpl implements CategoryDao {
    private static final String FIND_ALL = "select CategoryId, CategoryName from Categories";
    private static final String READ = "select CategoryId, CategoryName from Categories where CategoryId = ?";
    private static final Logger LOGGER = LogManager.getLogger(CategoryDaoImpl.class);
    @Override
    public List<Category> findAll() throws DAOException {
        PreparedStatement prepStat = null;
        ResultSet resSet = null;
        try {
            prepStat = connection.prepareStatement(FIND_ALL);
            resSet = prepStat.executeQuery();
            Category category = null;
            List<Category> categories = new ArrayList<>();
            while (resSet.next()) {
                category = new Category();
                category.setId(resSet.getInt("CategoryId"));
                category.setName(resSet.getString("CategoryName"));
                categories.add(category);
            }
            return categories;
        } catch (SQLException e) {
            LOGGER.debug(e);
            throw new DAOException(e);
        } finally {
            try {
                resSet.close();
            } catch (SQLException | NullPointerException e) {
                LOGGER.debug(e);
            }
            try {
                prepStat.close();
            } catch (SQLException | NullPointerException e) {
                LOGGER.debug(e);
            }
        }
    }

    @Override
    public int create(final Category entity) throws DAOException {
        LOGGER.debug(new UnsupportedOperationException());
        throw new DAOException(new UnsupportedOperationException("Сategory creation should be done in the database console."));
    }

    @Override
    public Optional<Category> read(final Integer identity) throws DAOException {
        PreparedStatement prepStat = null;
        ResultSet resSet = null;
        int i = 1;
        try {
            prepStat = connection.prepareStatement(READ);
            prepStat.setInt(i, identity);
            resSet = prepStat.executeQuery();
            if (resSet.next()) {
                Category category = new Category();
                category.setId(resSet.getInt("CategoryId"));
                category.setName(resSet.getString("CategoryName"));
                return Optional.ofNullable(category);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            LOGGER.debug(e);
            throw new DAOException(e);
        } finally {
            try {
                resSet.close();
            } catch (SQLException | NullPointerException e) {
                LOGGER.debug(e);
            }
            try {
                prepStat.close();
            } catch (SQLException | NullPointerException e) {
                LOGGER.debug(e);
            }
        }
    }

    @Override
    public int update(final Category entity) throws DAOException {
        throw new DAOException(new UnsupportedOperationException("Сategory update should be done in the database console."));
    }

    @Override
    public int delete(final Integer identity) throws DAOException {
        throw new DAOException(new UnsupportedOperationException("Сategory deletion should be done in the database console."));
    }
}
