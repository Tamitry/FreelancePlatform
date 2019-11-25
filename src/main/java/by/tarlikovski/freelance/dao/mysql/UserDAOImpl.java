package by.tarlikovski.freelance.dao.mysql;

import by.tarlikovski.freelance.dao.UserDAO;
import by.tarlikovski.freelance.entities.Client;
import by.tarlikovski.freelance.entities.Freelancer;
import by.tarlikovski.freelance.entities.User;
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

public class UserDAOImpl extends AbstractDAO implements UserDAO {
    private static final Logger LOGGER = LogManager.getLogger(UserDAOImpl.class);
    private static final String FIND_BY_NAME = "select `UserId`,`FirstName`,`LastName`,`DateOfRegistration`,`UserEmail`,`Login`,`UserEmail`,`Role` from users where coalesce(`FirstName`, '') || coalesce(`LastName`, '') LIKE ?";
    private static final String FIND_FREELANCER = "select `UserId`,`FirstName`,`LastName`,`DateOfRegistration`,`UserEmail`,`Login`,`UserEmail`,`Role` from users where `Role` = 1";
    private static final String FIND_ALL = "select `UserId`,`FirstName`,`LastName`,`DateOfRegistration`,`UserEmail`,`Login`,`UserEmail`,`Role` from users";
    private static final String FIND_BY_ID = "select `UserId`,`FirstName`,`LastName`,`DateOfRegistration`,`UserEmail`,`Login`,`UserEmail`,`Role` from users where `UserId` = ?";
    private static final String DELETE = "delete from `users` where `UserId` = ?";
    private static final String INSERT = "insert into `users` (`FirstName`,`LastName`,`DateOfRegistration`,`UserEmail`,`Login`,`UserPassword`,`Role`) values (?,?,?,?,?,?,?)";
    private static final String UPDATE = "update `users` set `FirstName` = ?,`LastName` = ?,`DateOfRegistration` = ?,`UserEmail` = ?,`Login` = ?,`UserPassword` = ?,`Role` = ? where `UserId` = ?";

    @Override
    public List<User> findByFullName(final String name) throws PersistentException {
        PreparedStatement prepStat = null;
        List<User> users = new ArrayList<>();
        try {
            prepStat = getConnection().prepareStatement(FIND_BY_NAME);
            prepStat.setString(1, "%" + name + "%");
        } catch (SQLException ex) {
            LOGGER.error(ex);
            throw new PersistentException(ex);
        }
        try (ResultSet resSet = prepStat.executeQuery()) {
            User user = null;
            while (resSet.next()) {
                if (resSet.getInt("Role") == 1) {
                    Freelancer freelancer = new Freelancer();
                    freelancer.setSkills(new ArrayList<>());
                    user = freelancer;
                } else {
                    Client client = new Client();
                    client.setOrders(new ArrayList<>());
                    user = client;
                }
                user.setId(resSet.getInt("UserId"));
                user.setEmail(resSet.getString("UserEmail"));
                user.setFirstName(resSet.getString("FirstName"));
                user.setLastName(resSet.getString("LastName"));
                user.setLogin(resSet.getString("Login"));
                user.setPassword(resSet.getString("Password"));
                user.setRole((short) resSet.getInt("Role"));
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(resSet
                        .getTimestamp("DateOfRegistration")
                        .getTime());
                user.setRegDate(calendar);
                users.add(user);
            }
            return users;
        } catch (SQLException ex) {
            LOGGER.error(ex);
            throw new PersistentException(ex);
        }
    }

    @Override
    public List<User> findFreelancers() throws PersistentException {
        PreparedStatement prepStat = null;
        List<User> users = new ArrayList<>();
        try {
            prepStat = getConnection().prepareStatement(FIND_FREELANCER);
            int i = 1;
            prepStat.setInt(i, 1);
        } catch (SQLException ex) {
            LOGGER.error(ex);
            throw new PersistentException(ex);
        }
        try (ResultSet resSet = prepStat.executeQuery()) {
            Freelancer freelancer = null;
            while (resSet.next()) {
                freelancer = new Freelancer();
                freelancer.setSkills(new ArrayList<>());
                freelancer.setId(resSet.getInt("UserId"));
                freelancer.setEmail(resSet.getString("UserEmail"));
                freelancer.setFirstName(resSet.getString("FirstName"));
                freelancer.setLastName(resSet.getString("LastName"));
                freelancer.setLogin(resSet.getString("Login"));
                freelancer.setPassword(resSet.getString("Password"));
                freelancer.setRole((short) resSet.getInt("Role"));
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(resSet
                        .getTimestamp("DateOfRegistration")
                        .getTime());
                freelancer.setRegDate(calendar);
                users.add(freelancer);
            }
            return users;
        } catch (SQLException ex) {
            LOGGER.error(ex);
            throw new PersistentException(ex);
        }
    }

    @Override
    public List<User> findAll() throws PersistentException {
        PreparedStatement prepStat = null;
        List<User> users = new ArrayList<>();
        try {
            prepStat = getConnection().prepareStatement(FIND_ALL);
        } catch (SQLException ex) {
            LOGGER.error(ex);
            throw new PersistentException(ex);
        }
        try (ResultSet resSet = prepStat.executeQuery()) {
            User user = null;
            while (resSet.next()) {
                if (resSet.getInt("Role") == 1) {
                    Freelancer freelancer = new Freelancer();
                    freelancer.setSkills(new ArrayList<>());
                    user = freelancer;
                } else {
                    Client client = new Client();
                    client.setOrders(new ArrayList<>());
                    user = client;
                }
                user.setId(resSet.getInt("UserId"));
                user.setEmail(resSet.getString("UserEmail"));
                user.setFirstName(resSet.getString("FirstName"));
                user.setLastName(resSet.getString("LastName"));
                user.setLogin(resSet.getString("Login"));
                user.setPassword(resSet.getString("Password"));
                user.setRole((short) resSet.getInt("Role"));
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(resSet
                        .getTimestamp("DateOfRegistration")
                        .getTime());
                user.setRegDate(calendar);
                users.add(user);
            }
            return users;
        } catch (SQLException ex) {
            LOGGER.error(ex);
            throw new PersistentException(ex);
        }
    }

    @Override
    public User findEntityById(final Integer id) throws PersistentException {
        PreparedStatement prepStat = null;
        try {
            prepStat = getConnection().prepareStatement(FIND_BY_ID);
            int i = 1;
            prepStat.setInt(i, id);
        } catch (SQLException ex) {
            LOGGER.error(ex);
            throw new PersistentException(ex);
        }
        try (ResultSet resSet = prepStat.executeQuery()) {
            User user = null;
            if (resSet.getInt("Role") == 1) {
                Freelancer freelancer = new Freelancer();
                freelancer.setSkills(new ArrayList<>());
                user = freelancer;
            } else {
                Client client = new Client();
                client.setOrders(new ArrayList<>());
                user = client;
            }
            user.setId(resSet.getInt("UserId"));
            user.setEmail(resSet.getString("UserEmail"));
            user.setFirstName(resSet.getString("FirstName"));
            user.setLastName(resSet.getString("LastName"));
            user.setLogin(resSet.getString("Login"));
            user.setPassword(resSet.getString("Password"));
            user.setRole((short) resSet.getInt("Role"));
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(resSet
                    .getTimestamp("DateOfRegistration")
                    .getTime());
            user.setRegDate(calendar);
            return user;
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
    public boolean create(final User item) throws PersistentException {
        PreparedStatement prepStatement = null;
        try {
            prepStatement = getConnection().prepareStatement(INSERT);
            int i = 1;
            prepStatement.setString(i++, item.getFirstName());
            prepStatement.setString(i++, item.getLastName());
            Timestamp timestamp = new Timestamp(item
                    .getRegDate()
                    .getTimeInMillis());
            prepStatement.setString(i++, item.getEmail());
            prepStatement.setString(i++, item.getLogin());
            prepStatement.setString(i++, item.getPassword());
            prepStatement.setInt(i++, item.getRole());
            return prepStatement.executeUpdate() != 0;
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
    public void update(final User item) throws PersistentException {
        PreparedStatement prepStatement = null;
        try {
            prepStatement = getConnection().prepareStatement(UPDATE);
            int i = 1;
            prepStatement.setString(i++, item.getFirstName());
            prepStatement.setString(i++, item.getLastName());
            Timestamp timestamp = new Timestamp(item
                    .getRegDate()
                    .getTimeInMillis());
            prepStatement.setString(i++, item.getEmail());
            prepStatement.setString(i++, item.getLogin());
            prepStatement.setString(i++, item.getPassword());
            prepStatement.setInt(i++, item.getRole());
            prepStatement.setInt(i++, item.getId());
            prepStatement.execute();
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
}
