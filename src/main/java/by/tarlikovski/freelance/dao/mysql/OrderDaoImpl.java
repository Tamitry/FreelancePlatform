package by.tarlikovski.freelance.dao.mysql;

import by.tarlikovski.freelance.bean.Order;
import by.tarlikovski.freelance.bean.User;
import by.tarlikovski.freelance.dao.DAOException;
import by.tarlikovski.freelance.dao.OrderDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDaoImpl extends BaseDaoImpl implements OrderDao {
    private static final String FIND_ALL = "select OrderId, OrderName, OrderRegDate, OrderDeadLine, OrderDesc, ClientId from orders";
    private static final String FIND_BY_USER = "select OrderId, OrderName, OrderRegDate, OrderDeadLine, OrderDesc, ClientId from orders where ClientId = ?";
    private static final String FIND_BY_NAME = "select OrderId, OrderName, OrderRegDate, OrderDeadLine, OrderDesc, ClientId from orders where OrderName like ?";
    private static final String CREATE = "insert into orders (OrderName, OrderRegDate, OrderDeadLine, OrderDesc, ClientId) value (?,?,?,?,?)";
    private static final String READ = "select OrderId, OrderName, OrderRegDate, OrderDeadLine, OrderDesc, ClientId from orders where OrderId = ?";
    private static final String UPDATE = "update orders set OrderName = ?, OrderRegDate = ?, OrderDeadLine = ?, OrderDesc = ? where OrderId = ?";
    private static final String DELETE = "delete from orders where OrderId = ?";
    @Override
    public List<Order> findAll() throws DAOException {
        PreparedStatement prepState = null;
        ResultSet resSet = null;
        try {
            prepState = connection.prepareCall(FIND_ALL);
            resSet = prepState.executeQuery();
            List<Order> orders = new ArrayList<>();
            Order order = null;
            while (resSet.next()) {
                order = new Order();
                order.setId(resSet.getInt("OrderId"));
                order.setOrderName(resSet.getString("OrderName"));
                order.setOrderRegDate(resSet.getTimestamp("OrderRegDate"));
                order.setOrderDeadLine(resSet.getTimestamp("OrderDeadLine"));
                order.setDescription(resSet.getString("OrderDesc"));
                User user = new User();
                user.setId(resSet.getInt("ClientId"));
                order.setClient(user);
                orders.add(order);
            }
            return orders;
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
    public List<Order> findByUser(final User user) throws DAOException {
        PreparedStatement prepState = null;
        ResultSet resSet = null;
        int i = 1;
        try {
            prepState = connection.prepareCall(FIND_BY_USER);
            prepState.setInt(i, user.getId());
            resSet = prepState.executeQuery();
            List<Order> orders = new ArrayList<>();
            Order order = null;
            while (resSet.next()) {
                order = new Order();
                order.setId(resSet.getInt("OrderId"));
                order.setOrderName(resSet.getString("OrderName"));
                order.setOrderRegDate(resSet.getTimestamp("OrderRegDate"));
                order.setOrderDeadLine(resSet.getTimestamp("OrderDeadLine"));
                order.setDescription(resSet.getString("OrderDesc"));
                order.setClient(user);
                orders.add(order);
            }
            return orders;
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
    public List<Order> findByName(final String name)
            throws DAOException {
        PreparedStatement prepState = null;
        ResultSet resSet = null;
        int i = 1;
        try {
            prepState = connection.prepareCall(FIND_BY_NAME);
            prepState.setString(i, "%" + name + "%");
            resSet = prepState.executeQuery();
            List<Order> orders = new ArrayList<>();
            Order order = null;
            while (resSet.next()) {
                order = new Order();
                order.setId(resSet.getInt("OrderId"));
                order.setOrderName(resSet.getString("OrderName"));
                order.setOrderRegDate(resSet.getTimestamp("OrderRegDate"));
                order.setOrderDeadLine(resSet.getTimestamp("OrderDeadLine"));
                order.setDescription(resSet.getString("OrderDesc"));
                User user = new User();
                user.setId(resSet.getInt("ClientId"));
                order.setClient(user);
                orders.add(order);
            }
            return orders;
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
    public int create(final Order entity)
            throws DAOException {
        PreparedStatement prepStat = null;
        ResultSet resSet = null;
        int i = 1;
        int v;
        try {
            prepStat = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            prepStat.setString(i++, entity.getOrderName());
            prepStat.setTimestamp(i++, entity.getOrderRegDate());
            prepStat.setTimestamp(i++, entity.getOrderDeadLine());
            prepStat.setString(i++, entity.getDescription());
            prepStat.setInt(i, (entity.getClient().getId()));
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
    public Optional<Order> read(final Integer identity)
            throws DAOException {
        PreparedStatement prepState = null;
        ResultSet resSet = null;
        int i = 1;
        try {
            prepState = connection.prepareCall(READ);
            prepState.setInt(i, identity);
            resSet = prepState.executeQuery();
            if (!resSet.next()) {
                return Optional.empty();
            } else {
                Order order = new Order();
                order.setId(resSet.getInt("OrderId"));
                order.setOrderName(resSet.getString("OrderName"));
                order.setOrderRegDate(resSet.getTimestamp("OrderRegDate"));
                order.setOrderDeadLine(resSet.getTimestamp("OrderDeadLine"));
                order.setDescription(resSet.getString("OrderDesc"));
                User user = new User();
                user.setId(resSet.getInt("ClientId"));
                order.setClient(user);
                return Optional.ofNullable(order);
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
    public int update(final Order entity) throws DAOException {
        PreparedStatement prepStat = null;
        int i = 1;
        try {
            prepStat = connection.prepareStatement(UPDATE);
            prepStat.setString(i++, entity.getOrderName());
            prepStat.setTimestamp(i++, entity.getOrderRegDate());
            prepStat.setTimestamp(i++, entity.getOrderDeadLine());
            prepStat.setString(i++, entity.getDescription());
            prepStat.setInt(i, entity.getId());
            i = prepStat.executeUpdate();
            return i;
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
    public int delete(final Integer identity) throws DAOException {
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
