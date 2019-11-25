package by.tarlikovski.freelance.dao.mysql;

import by.tarlikovski.freelance.dao.OrderDAO;
import by.tarlikovski.freelance.entities.Client;
import by.tarlikovski.freelance.entities.Order;
import by.tarlikovski.freelance.errors.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class OrderDAOImpl extends AbstractDAO implements OrderDAO {

    private static final Logger LOGGER = LogManager.getLogger(
            OrderDAOImpl.class);
    private static final String CLIENT_ID_SEARCH = "select `OrderId`,`Name`,`DateOfRegistration`,`DateOfDeadline`,`ClientId`,`Description` from `orders` where `ClientId` = ?";
    private static final String CLIENT_NAME_SEARCH = "select `OrderId`,`Name`,`DateOfRegistration`,`DateOfDeadline`,`ClientId`,`Description` from `orders` where `Name` = ?";
    private static final String FULL_TABLE = "select `OrderId`,`Name`,`DateOfRegistration`,`DateOfDeadline`,`ClientId`,`Description` from `orders`";
    private static final String BY_ID = "select `OrderId`,`Name`,`DateOfRegistration`,`DateOfDeadline`,`ClientId`,`Description` from `orders` where `OrderID` = ?"
    private static final String DELETE = "delete from `orders` where `OrderID` = ?";
    private static final String INSERT = "insert into `orders` (`Name`,`DateOfRegistration`,`DateOfDeadline`,`ClientId`,`Description`) VALUES (?,?,?,?,?)";
    private static final String UPDATE = "update `orders` set `Name` = ?,`DateOfRegistration` = ?,`DateOfDeadline` = ?,`ClientId` = ?,`Description` = ? WHERE `OrderID` = ?";

    @Override
    public List<Order> findByClient(final Client client) throws PersistentException {
        int id = client.getId();
        List<Order> orders = new ArrayList<>();
        PreparedStatement prepState = null;
        Order order = null;
        try {
            prepState = getConnection().prepareCall(CLIENT_ID_SEARCH);
            prepState.setInt(1, id);
        } catch (SQLException ex) {
            LOGGER.error(ex);
            throw new PersistentException(ex);
        }
        try (ResultSet resultSet = prepState.executeQuery()) {
            while (resultSet.next()) {
                order = new Order();
                order.setId(resultSet.getInt("OrderID"));
                order.setClient(id);
                Timestamp dead = resultSet.getTimestamp("DateOfDeadline");
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(dead.getTime());
                order.setDateOfEnd(calendar);
                Timestamp reg = resultSet.getTimestamp("DateOfRegistration");
                calendar = Calendar.getInstance();
                calendar.setTimeInMillis(reg.getTime());
                order.setDateOfStart(calendar);
                order.setName(resultSet.getString("Name"));
                order.setDescription(resultSet.getString("Description"));
                orders.add(order);
            }
            return orders;
        } catch (SQLException ex) {
            LOGGER.error(ex);
            throw new PersistentException(ex);
        }
    }

    @Override
    public List<Order> findByName(final String name) throws PersistentException {
        List<Order> orders = new ArrayList<>();
        PreparedStatement prepState = null;
        Order order = null;
        try {
            prepState = getConnection().prepareCall(CLIENT_NAME_SEARCH);
            prepState.setString(1, name);
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new PersistentException(e);
        }
        try (ResultSet resultSet = prepState.executeQuery()) {
            while (resultSet.next()) {
                order = new Order();
                order.setId(resultSet.getInt("OrderID"));
                order.setClient(resultSet.getInt("ClientId"));
                Timestamp dead = resultSet.getTimestamp("DateOfDeadline");
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(dead.getTime());
                order.setDateOfEnd(calendar);
                Timestamp reg = resultSet.getTimestamp("DateOfRegistration");
                calendar = Calendar.getInstance();
                calendar.setTimeInMillis(reg.getTime());
                order.setDateOfStart(calendar);
                order.setName(resultSet.getString("Name"));
                order.setDescription(resultSet.getString("Description"));
                orders.add(order);
            }
            return orders;
        } catch (SQLException ex) {
            LOGGER.error(ex);
            throw new PersistentException(ex);
        }
    }

    @Override
    public List<Order> findAll() throws PersistentException {
        List<Order> orders = new ArrayList<>();
        PreparedStatement prepStatement = null;
        Order order = null;
        try {
            prepStatement = getConnection().prepareStatement(FULL_TABLE);
        } catch (SQLException ex) {
            LOGGER.error(ex);
            throw new PersistentException(ex);
        }
        try (ResultSet resultSet = prepStatement.executeQuery()) {
            while (resultSet.next()) {
                order = new Order();
                order.setId(resultSet.getInt("OrderID"));
                order.setClient(resultSet.getInt("ClientId"));
                Timestamp dead = resultSet.getTimestamp("DateOfDeadline");
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(dead.getTime());
                order.setDateOfEnd(calendar);
                Timestamp reg = resultSet.getTimestamp("DateOfRegistration");
                calendar = Calendar.getInstance();
                calendar.setTimeInMillis(reg.getTime());
                order.setDateOfStart(calendar);
                order.setName(resultSet.getString("Name"));
                order.setDescription(resultSet.getString("Description"));
                orders.add(order);
            }
            return orders;
        } catch (SQLException ex) {
            LOGGER.error(ex);
            throw new PersistentException(ex);
        }
    }

    @Override
    public Order findEntityById(final Integer id) throws PersistentException {
        PreparedStatement prepStatement = null;
        Order order = null;
        try {
            prepStatement = getConnection().prepareStatement(BY_ID);
            prepStatement.setInt(1, id);
        } catch (SQLException ex) {
            LOGGER.error(ex);
            throw new PersistentException(ex);
        }
        try (ResultSet resultSet = prepStatement.executeQuery()) {
            order = new Order();
            order.setId(resultSet.getInt("OrderID"));
            order.setClient(resultSet.getInt("ClientId"));
            Timestamp dead = resultSet.getTimestamp("DateOfDeadline");
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(dead.getTime());
            order.setDateOfEnd(calendar);
            Timestamp reg = resultSet.getTimestamp("DateOfRegistration");
            calendar = Calendar.getInstance();
            calendar.setTimeInMillis(reg.getTime());
            order.setDateOfStart(calendar);
            order.setName(resultSet.getString("Name"));
            order.setDescription(resultSet.getString("Description"));
            return order;
        } catch (SQLException ex) {
            LOGGER.error(ex);
            throw new PersistentException(ex);
        }
    }

    @Override
    public boolean delete(final Integer id) throws PersistentException {
        PreparedStatement prepStatement = null;
        try {
            prepStatement = getConnection().prepareStatement(DELETE);
            prepStatement.setInt(1, id);
            int res = prepStatement.executeUpdate();
            return res != 0;
        } catch (SQLException ex) {
            LOGGER.error(ex);
            throw new PersistentException(ex);
        } finally {
            try {
                if (prepStatement != null) {
                    prepStatement.close();
                }
            } catch (SQLException ex) {
                LOGGER.error(ex);
                throw new PersistentException(ex);
            }
        }
    }

    @Override
    public boolean create(final Order item) throws PersistentException {
        PreparedStatement prepStatement = null;
        try {
            int i = 1;
            prepStatement = getConnection().prepareStatement(INSERT);
            prepStatement.setInt(i++, item.getId());
            prepStatement.setTimestamp(i++, new Timestamp(item
                    .getDateOfStart()
                    .getTimeInMillis()));
            prepStatement.setTimestamp(i++, new Timestamp(item
                    .getDateOfEnd()
                    .getTimeInMillis()));
            prepStatement.setInt(i++, item.getId());
            prepStatement.setString(i++, item.getDescription());
            int res = prepStatement.executeUpdate();
            return res != 0;
        } catch (SQLException ex) {
            LOGGER.error(ex);
            throw new PersistentException(ex);
        } finally {
            try {
                if (prepStatement != null) {
                    prepStatement.close();
                }
            } catch (SQLException ex) {
                LOGGER.error(ex);
                throw new PersistentException(ex);
            }
        }
    }

    @Override
    public void update(final Order item) throws PersistentException {
        PreparedStatement prepStatement = null;
        try {
            prepStatement = getConnection().prepareStatement(UPDATE);
            int i = 1;
            prepStatement.setString(i++, item.getName());
            prepStatement.setTimestamp(i++, new Timestamp(item
                    .getDateOfStart()
                    .getTimeInMillis()));
            prepStatement.setTimestamp(i++, new Timestamp(item
                    .getDateOfEnd()
                    .getTimeInMillis()));
            prepStatement.setInt(i++, item.getClient());
            prepStatement.setString(i++, item.getDescription());
            prepStatement.setInt(i, item.getId());
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new PersistentException(e);
        } finally {
            try {
                if (prepStatement != null) {
                    prepStatement.close();
                }
            } catch (SQLException ex) {
                LOGGER.error(ex);
                throw new PersistentException(ex);
            }
        }
    }
}
