package by.tarlikovski.freelance.dao.mysql;

import by.tarlikovski.freelance.dao.CategoriesDAO;
import by.tarlikovski.freelance.entities.Category;
import by.tarlikovski.freelance.errors.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

public class CategoriesDAOImpl extends AbstractDAO implements CategoriesDAO {

    private static final Logger LOGGER
            = LogManager.getLogger(CategoriesDAOImpl.class);

    private static final String FIND_ALL = "select `CategoryId`, `CategoryName` from `categories`";
    private static final String FIND_BY_ID = "select `CategoryId`, `CategoryName` from `categories` where `CategoryId` = ?";
    private static final String DELETE = "delete from `categories` where `CategoryId` = ?";
    private static final String INSERT = "insert into `categories` (`CategoryName`) values (?)";
    private static final String UPDATE = "update `categories` set `CategoryName` = ? where `CategoryId` = ?";

    @Override
    public List<Category> findAll() throws PersistentException {
        List<Category> categories = new ArrayList<>();
        PreparedStatement prepStat = null;
         try {
             prepStat = getConnection().prepareStatement(FIND_ALL);
         } catch (SQLException ex) {
             LOGGER.error(ex);
             throw new PersistentException(ex);
         }
         try (ResultSet set = prepStat.executeQuery()){
             while (set.next()) {
                 Category category = new Category();
                 category.setId(set.getInt("CategoryId"));
                 category.setName(set.getString("CategoryName"));
                 categories.add(category);
             }
             return categories;
         } catch (SQLException ex) {
             LOGGER.error(ex);
             throw new PersistentException(ex);
         }
    }

    @Override
    public Category findEntityById(final Integer id) throws PersistentException {
        return null;
    }

    @Override
    public boolean delete(final Integer id) throws PersistentException {
        return false;
    }

    @Override
    public boolean create(final Category item) throws PersistentException {
        return false;
    }

    @Override
    public void update(final Category item) throws PersistentException {

    }
}
