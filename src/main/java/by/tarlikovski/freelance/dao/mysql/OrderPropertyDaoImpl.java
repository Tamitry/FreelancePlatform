package by.tarlikovski.freelance.dao.mysql;

import by.tarlikovski.freelance.bean.Category;
import by.tarlikovski.freelance.bean.Order;
import by.tarlikovski.freelance.bean.OrderProperty;
import by.tarlikovski.freelance.dao.DAOException;
import by.tarlikovski.freelance.dao.OrderPropertyDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderPropertyDaoImpl extends BaseDaoImpl implements OrderPropertyDao {
    private static final String FIND_BY_ORDER = "select OrderPropertyId, OrderId, CategoryId from orderproperties where OrderId = ?";
    private static final String FIND_BY_CATEGORY = "select OrderPropertyId, OrderId, CategoryId from orderproperties where CategoryId = ? and datediff(now(), OrderDeadLine) < 0";
    private static final String CREATE = "insert into orderproperties (OrderId, CategoryId) value (?,?)";
    private static final String FIND_BY_ID = "select OrderPropertyId, OrderId, CategoryId from orderproperties where OrderPropertyId = ?";
    private static final String UPDATE = "update orderproperties set OrderId = ?, CategoryId = ? where OrderPropertyId = ?";
    private static final String DELETE = "delete from orderproperties where OrderPropertyId = ?";
    @Override
    public List<OrderProperty> findByOrder(final Order order) throws DAOException {
        PreparedStatement prepState = null;
        ResultSet resSet = null;
        int i = 1;
        try {
            prepState = connection.prepareCall(FIND_BY_ORDER);
            prepState.setInt(i, order.getId());
            resSet = prepState.executeQuery();
            List<OrderProperty> orderProperties = new ArrayList<>();
            OrderProperty orderProperty = null;
            while (resSet.next()) {
                orderProperty = new OrderProperty();
                orderProperty.setId(resSet.getInt("OrderPropertyId"));
                orderProperty.setOrderId(resSet.getInt("OrderId"));
                orderProperty.setCategoryId(resSet.getInt("CategoryId"));
                orderProperties.add(orderProperty);
            }
            return orderProperties;
        } catch (SQLException ex) {
            throw new DAOException(ex);
        } finally {
            try {
                resSet.close();
            } catch (SQLException | NullPointerException e) {
            }
            try {
                prepState.close();
            } catch (SQLException | NullPointerException e) {
            }
        }
    }

    @Override
    public List<OrderProperty> findByCategory(final Category category)
            throws DAOException {
        PreparedStatement prepState = null;
        ResultSet resSet = null;
        int i = 1;
        try {
            prepState = connection.prepareCall(FIND_BY_CATEGORY);
            prepState.setInt(i, category.getId());
            resSet = prepState.executeQuery();
            List<OrderProperty> orderProperties = new ArrayList<>();
            OrderProperty orderProperty = null;
            while (resSet.next()) {
                orderProperty = new OrderProperty();
                orderProperty.setId(resSet.getInt("OrderPropertyId"));
                orderProperty.setOrderId(resSet.getInt("OrderId"));
                orderProperty.setCategoryId(resSet.getInt("CategoryId"));
                orderProperties.add(orderProperty);
            }
            return orderProperties;
        } catch (SQLException ex) {
            throw new DAOException(ex);
        } finally {
            try {
                resSet.close();
            } catch (SQLException | NullPointerException e) {
            }
            try {
                prepState.close();
            } catch (SQLException | NullPointerException e) {
            }
        }
    }

    @Override
    public int create(final OrderProperty entity)
            throws DAOException {
        PreparedStatement prepStat = null;
        ResultSet resSet = null;
        int i = 1;
        int v;
        try {
            prepStat = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            prepStat.setInt(i++, entity.getOrderId());
            prepStat.setInt(i++, entity.getCategoryId());
            v = prepStat.executeUpdate();
            resSet = prepStat.getGeneratedKeys();
            if (resSet.next()) {
                i = 1;
                entity.setId(resSet.getInt(i));
                return v;
            } else {
                throw new DAOException("An error occurred while adding to the table Users.");
            }
        } catch (SQLException ex) {
            throw new DAOException(ex);
        } finally {
            try {
                resSet.close();
            } catch (SQLException | NullPointerException e) {
            }
            try {
                prepStat.close();
            } catch (SQLException | NullPointerException e) {
            }
        }
    }

    @Override
    public Optional read(final Integer identity)
            throws DAOException {
        PreparedStatement prepState = null;
        ResultSet resSet = null;
        int i = 1;
        try {
            prepState = connection.prepareCall(FIND_BY_ID);
            prepState.setInt(i, identity);
            resSet = prepState.executeQuery();
            if (!resSet.next()) {
                return Optional.empty();
            } else {
                OrderProperty orderProperty = new OrderProperty();
                orderProperty.setId(resSet.getInt("OrderPropertyId"));
                orderProperty.setOrderId(resSet.getInt("OrderId"));
                orderProperty.setCategoryId(resSet.getInt("CategoryId"));
                return Optional.ofNullable(orderProperty);
            }
        } catch (SQLException ex) {
            throw new DAOException(ex);
        } finally {
            try {
                resSet.close();
            } catch (SQLException | NullPointerException e) {
            }
            try {
                prepState.close();
            } catch (SQLException | NullPointerException e) {
            }
        }
    }

    @Override
    public int update(final OrderProperty entity)
            throws DAOException {
        PreparedStatement prepStat = null;
        int i = 1;
        try {
            prepStat = connection.prepareStatement(UPDATE);
            prepStat.setInt(i++, entity.getOrderId());
            prepStat.setInt(i++, entity.getCategoryId());
            prepStat.setInt(i, entity.getId());
            return prepStat.executeUpdate();
        } catch (SQLException ex) {
            throw new DAOException(ex);
        } finally {
            try {
                prepStat.close();
            } catch (SQLException | NullPointerException ex) {
            }
        }
    }

    @Override
    public int delete(final Integer identity)
            throws DAOException {
        PreparedStatement prepStat = null;
        int i = 1;
        try {
            prepStat = connection.prepareStatement(DELETE);
            prepStat.setInt(i, identity);
            return prepStat.executeUpdate();
        } catch (SQLException ex) {
            throw new DAOException(ex);
        } finally {
            try {
                prepStat.close();
            } catch (SQLException | NullPointerException ex) {
            }
        }
    }
}
