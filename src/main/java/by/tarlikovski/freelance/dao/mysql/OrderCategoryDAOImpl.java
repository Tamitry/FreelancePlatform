package by.tarlikovski.freelance.dao.mysql;

import by.tarlikovski.freelance.dao.OrderCategoryDAO;
import by.tarlikovski.freelance.entities.OrderCategory;
import by.tarlikovski.freelance.errors.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderCategoryDAOImpl extends AbstractDAO
        implements OrderCategoryDAO {
    private static final Logger LOGGER = LogManager.getLogger(
            OrderCategoryDAOImpl.class);
    private static final String FIND_ALL = "select `OrderCategoryId`, `NumberOfPeople`, `Price`, `OrderId`, `CategoryId` from `ordercategory`";
    private static final String FIND_BY_ID = "select `OrderCategoryId`, `NumberOfPeople`, `Price`, `OrderId`, `CategoryId` from `ordercategory` where `OrderCategoryId` = ?";
    private static final String DELETE = "delete from `ordercategory` where `OrderCategoryId` = ?";
    private static final String INSERT = "insert into `ordercategory` (`NumberOfPeople`, `Price`, `CategoryId`, `OrderId`) values (?,?,?,?)";
    public static final String UPDATE = "update `ordercategory` set `NumberOfPeople`=?, `Price`=?, `CategoryId`=?, `OrderId`=? where `OrderCategoryId` = ?";
    @Override
    public List<OrderCategory> findAll() throws PersistentException {
        PreparedStatement prepStat = null;
        List<OrderCategory> orderCategories = new ArrayList<>();
        try {
            prepStat = getConnection().prepareStatement(FIND_ALL);
        } catch (SQLException ex) {
            LOGGER.error(ex);
            throw new PersistentException(ex);
        }
        try (ResultSet set = prepStat.executeQuery()) {
            while(set.next()) {
                OrderCategory oc = new OrderCategory();
                oc.setId(set.getInt("OrderCategoryId"));
                oc.setCategory(set.getInt("CategoryId"));
                oc.setNumOfPeople(set.getInt("NumberOfPeople"));
                oc.setPrice(set.getInt("Price"));
                oc.setOrderId(set.getInt("OrderId"));
                orderCategories.add(oc);
            }
            return orderCategories;
        } catch (SQLException ex) {
            LOGGER.error(ex);
            throw new PersistentException(ex);
        }
    }

    @Override
    public OrderCategory findEntityById(final Integer id)
            throws PersistentException {
        PreparedStatement prepStat = null;
        try {
            prepStat = getConnection().prepareStatement(FIND_BY_ID);
            prepStat.setInt(1, id);
        } catch (SQLException ex) {
            LOGGER.error(ex);
            throw new PersistentException(ex);
        }
        try (ResultSet set = prepStat.executeQuery()) {
            OrderCategory oc = new OrderCategory();
            oc.setId(set.getInt("OrderCategoryId"));
            oc.setCategory(set.getInt("CategoryId"));
            oc.setNumOfPeople(set.getInt("NumberOfPeople"));
            oc.setPrice(set.getInt("Price"));
            oc.setOrderId(set.getInt("OrderId"));
            return oc;
        } catch (SQLException ex) {
            LOGGER.error(ex);
            throw new PersistentException(ex);
        }
    }

    @Override
    public boolean delete(final Integer id)
            throws PersistentException {
        PreparedStatement prepStat = null;
        try {
            prepStat = getConnection().prepareStatement(DELETE);
            prepStat.setInt(1, id);
            return prepStat.execute();
        } catch (SQLException ex) {
            LOGGER.error(ex);
            throw new PersistentException(ex);
        } finally {
            try {
                if (prepStat != null) {
                    prepStat.close();
                }
            } catch (SQLException ex) {
                LOGGER.error(ex);
                throw new PersistentException(ex);
            }
        }
    }

    @Override
    public boolean create(final OrderCategory item)
            throws PersistentException {
        PreparedStatement prepStat = null;
        try {
            prepStat = getConnection().prepareStatement(INSERT);
            int i = 1;
            prepStat.setInt(i++, item.getNumOfPeople());
            prepStat.setInt(i++, item.getPrice());
            prepStat.setInt(i++, item.getOrderId());
            prepStat.setInt(i++, item.getCategory());
            return prepStat.execute();
        } catch (SQLException ex) {
            LOGGER.error(ex);
            throw new PersistentException(ex);
        } finally {
            try {
                if (prepStat != null) {
                    prepStat.close();
                }
            } catch (SQLException ex) {
                LOGGER.error(ex);
                throw new PersistentException(ex);
            }
        }
    }

    @Override
    public void update(final OrderCategory item)
            throws PersistentException {
        PreparedStatement prepStat = null;
        try {
            prepStat = getConnection().prepareStatement(UPDATE);
            int i = 1;
            prepStat.setInt(i++, item.getNumOfPeople());
            prepStat.setInt(i++, item.getPrice());
            prepStat.setInt(i++, item.getOrderId());
            prepStat.setInt(i++, item.getCategory());
            prepStat.setInt(i++, item.getId());
            prepStat.execute();
        } catch (SQLException ex) {
            LOGGER.error(ex);
            throw new PersistentException(ex);
        }  finally {
            try {
                if (prepStat != null) {
                    prepStat.close();
                }
            } catch (SQLException ex) {
                LOGGER.error(ex);
                throw new PersistentException(ex);
            }
        }
    }
}
