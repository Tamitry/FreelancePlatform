package by.tarlikovski.freelance.dao.sqlImpl;

import by.tarlikovski.freelance.bean.Role;
import by.tarlikovski.freelance.bean.User;
import by.tarlikovski.freelance.bean.UserStatus;
import by.tarlikovski.freelance.dao.UserDao;
import by.tarlikovski.freelance.dao.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl extends BaseDaoImpl implements UserDao {
    private static final String FIND_FULL_NAME = "select UserId, FirstName, LastName, RegDate, UserLogin, UserEmail, UserPassword, UserRole, Status from users where CONCAT(FirstName, ' ', LastName) like ? and not Status = 2";
    private static final String FIND_BY_LOGIN = "select UserId, FirstName, LastName, RegDate, UserLogin, UserEmail, UserPassword, UserRole, Status from users where UserLogin = ? and not Status = 2";
    private static final String FIND_BY_EMAIL = "select UserId, FirstName, LastName, RegDate, UserLogin, UserEmail, UserPassword, UserRole, Status from users where UserEmail = ? and not Status = 2";
    private static final String FIND_ALL_FREELANCER = "select UserId, FirstName, LastName, RegDate, UserLogin, UserEmail, UserPassword, UserRole, Status from users where UserRole = 2 and not Status = 2";
    private static final String FIND_BY_ID = "select UserId, FirstName, LastName, RegDate, UserLogin, UserEmail, UserPassword, UserRole, Status from users where UserId = ?";
    private static final String CREATE = "insert into users (FirstName, LastName, UserLogin, UserEmail, UserPassword, UserRole) values (?,?,?,?,?,?)";
    private static final String UPDATE = "update users set FirstName = ?, LastName = ?, UserLogin = ?, UserEmail = ?, UserPassword = ?, UserRole = ?, Status = ? where UserId = ?";
    private static final String DELETE = "delete from users where UserId = ?";
    private static final Logger LOGGER = LogManager.getLogger(UserDaoImpl.class);

    @Override
    public List<User> findByFullName(final String name) throws DAOException {
        PreparedStatement prepState = null;
        ResultSet resSet = null;
        int i = 1;
        try {
            prepState = connection.prepareCall(FIND_FULL_NAME);
            prepState.setString(i, "%" + name + "%");
            resSet = prepState.executeQuery();
            List<User> users = new ArrayList<>();
            User user = null;
            while (resSet.next()) {
                user = new User();
                user.setId(resSet.getInt("UserId"));
                user.setFirstName(resSet.getString("FirstName"));
                user.setLastName(resSet.getString("LastName"));
                user.setRegDate(resSet.getTimestamp("RegDate"));
                user.setEmail(resSet.getString("UserEmail"));
                user.setLogin(resSet.getString("UserLogin"));
                user.setPassword(resSet.getString("UserPassword"));
                user.setRole(Role.getRole(resSet.getInt("UserRole")));
                user.setUserStatus(UserStatus.getStatus(resSet.getInt("Status")));
                users.add(user);
            }
            return users;
        } catch (SQLException ex) {
            LOGGER.debug(ex);
            throw new DAOException(ex);
        } finally {
            try {
                resSet.close();
            } catch (SQLException | NullPointerException e) {
                LOGGER.debug(e);
            }
            try {
                prepState.close();
            } catch (SQLException | NullPointerException e) {
                LOGGER.debug(e);
            }
        }
    }

    @Override
    public Optional<User> findByLogin(final String lg) throws DAOException {
        PreparedStatement prepState = null;
        ResultSet resSet = null;
        int i = 1;
        try {
            prepState = connection.prepareCall(FIND_BY_LOGIN);
            prepState.setString(i, lg);
            resSet = prepState.executeQuery();
            if (resSet.next()) {
                List<User> users = new ArrayList<>();
                User user = null;
                user = new User();
                user.setId(resSet.getInt("UserId"));
                user.setFirstName(resSet.getString("FirstName"));
                user.setLastName(resSet.getString("LastName"));
                user.setRegDate(resSet.getTimestamp("RegDate"));
                user.setEmail(resSet.getString("UserEmail"));
                user.setLogin(resSet.getString("UserLogin"));
                user.setPassword(resSet.getString("UserPassword"));
                user.setRole(Role.getRole(resSet.getInt("UserRole")));
                user.setUserStatus(UserStatus.getStatus(resSet.getInt("Status")));
                users.add(user);
                return Optional.ofNullable(user);
            } else {
                return Optional.empty();
            }
        } catch (SQLException ex) {
            LOGGER.debug(ex);
            throw new DAOException(ex);
        } finally {
            try {
                resSet.close();
            } catch (SQLException | NullPointerException e) {
                LOGGER.debug(e);
            }
            try {
                prepState.close();
            } catch (SQLException | NullPointerException e) {
                LOGGER.debug(e);
            }
        }
    }

    @Override
    public List<User> findAllFreelancers() throws DAOException {
        PreparedStatement prepState = null;
        ResultSet resSet = null;
        try {
            prepState = connection.prepareCall(FIND_ALL_FREELANCER);
            resSet = prepState.executeQuery();
            List<User> users = new ArrayList<>();
            User user = null;
            while (resSet.next()) {
                user = new User();
                user.setId(resSet.getInt("UserId"));
                user.setFirstName(resSet.getString("FirstName"));
                user.setLastName(resSet.getString("LastName"));
                user.setRegDate(resSet.getTimestamp("RegDate"));
                user.setEmail(resSet.getString("UserEmail"));
                user.setLogin(resSet.getString("UserLogin"));
                user.setPassword(resSet.getString("UserPassword"));
                user.setRole(Role.getRole(resSet.getInt("UserRole")));
                user.setUserStatus(UserStatus.getStatus(resSet.getInt("Status")));
                users.add(user);
            }
            return users;
        } catch (SQLException ex) {
            LOGGER.debug(ex);
            throw new DAOException(ex);
        } finally {
            try {
                resSet.close();
            } catch (SQLException | NullPointerException e) {
                LOGGER.debug(e);
            }
            try {
                prepState.close();
            } catch (SQLException | NullPointerException e) {
                LOGGER.debug(e);
            }
        }
    }

    //May be optional?
    @Override
    public Optional<User> findByEmail(final String email) throws DAOException {
        PreparedStatement prepState = null;
        ResultSet resSet = null;
        int i = 1;
        try {
            prepState = connection.prepareCall(FIND_BY_EMAIL);
            prepState.setString(i, email);
            resSet = prepState.executeQuery();
            if (!resSet.next()) {
                return Optional.empty();
            } else {
                List<User> users = new ArrayList<>();
                User user = null;
                user = new User();
                user.setId(resSet.getInt("UserId"));
                user.setFirstName(resSet.getString("FirstName"));
                user.setLastName(resSet.getString("LastName"));
                user.setRegDate(resSet.getTimestamp("RegDate"));
                user.setEmail(resSet.getString("UserEmail"));
                user.setLogin(resSet.getString("UserLogin"));
                user.setPassword(resSet.getString("UserPassword"));
                user.setRole(Role.getRole(resSet.getInt("UserRole")));
                user.setUserStatus(UserStatus.getStatus(resSet.getInt("Status")));
                users.add(user);
                return Optional.ofNullable(user);
            }
        } catch (SQLException ex) {
            LOGGER.debug(ex);
            throw new DAOException(ex);
        } finally {
            try {
                resSet.close();
            } catch (SQLException | NullPointerException e) {
                LOGGER.debug(e);
            }
            try {
                prepState.close();
            } catch (SQLException | NullPointerException e) {
                LOGGER.debug(e);
            }
        }
    }

    @Override
    public int create(final User entity) throws DAOException {
        PreparedStatement prepStat = null;
        ResultSet resSet = null;
        int i = 1;
        int v;
        try {
            prepStat = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            String firstName = entity.getFirstName() != null ? entity.getFirstName() : "";
            prepStat.setString(i++, firstName);
            String lastName = entity.getLastName() != null ? entity.getLastName() : "";
            prepStat.setString(i++, lastName);
            prepStat.setString(i++, entity.getLogin());
            prepStat.setString(i++, entity.getEmail());
            prepStat.setString(i++, entity.getPassword());
            prepStat.setInt(i, (entity.getRole().getRoleNum()));
            v = prepStat.executeUpdate();
            resSet = prepStat.getGeneratedKeys();
            if (resSet.next()) {
                i = 1;
                entity.setId(resSet.getInt(i));
                return v;
            } else {
                LOGGER.debug(new DAOException("An error occurred while adding to the table Users."));
                throw new DAOException("An error occurred while adding to the table Users.");
            }
        } catch (SQLException ex) {
            LOGGER.debug(ex);
            throw new DAOException(ex);
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
    public Optional<User> read(final Integer identity) throws DAOException {
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
                User user = new User();
                user.setId(resSet.getInt("UserId"));
                user.setFirstName(resSet.getString("FirstName"));
                user.setLastName(resSet.getString("LastName"));
                user.setRegDate(resSet.getTimestamp("RegDate"));
                user.setEmail(resSet.getString("UserEmail"));
                user.setLogin(resSet.getString("UserLogin"));
                user.setPassword(resSet.getString("UserPassword"));
                user.setRole(Role.getRole(resSet.getInt("UserRole")));
                user.setUserStatus(UserStatus.getStatus(resSet.getInt("Status")));
                return Optional.ofNullable(user);
            }
        } catch (SQLException ex) {
            LOGGER.debug(ex);
            throw new DAOException(ex);
        } finally {
            try {
                resSet.close();
            } catch (SQLException | NullPointerException e) {
                LOGGER.debug(e);
            }
            try {
                prepState.close();
            } catch (SQLException | NullPointerException e) {
                LOGGER.debug(e);
            }
        }
    }

    @Override
    public int update(final User entity) throws DAOException {
        PreparedStatement prepStat = null;
        int i = 1;
        try {
            prepStat = connection.prepareStatement(UPDATE);
            prepStat.setString(i++, entity.getFirstName());
            prepStat.setString(i++, entity.getLastName());
            prepStat.setString(i++, entity.getLogin());
            prepStat.setString(i++, entity.getEmail());
            prepStat.setString(i++, entity.getPassword());
            prepStat.setInt(i++, (entity.getRole().getRoleNum()));
            prepStat.setInt(i++, entity.getUserStatus().getStatusNum());
            prepStat.setInt(i, entity.getId());
            return prepStat.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.debug(ex);
            throw new DAOException(ex);
        } finally {
            try {
                prepStat.close();
            } catch (SQLException | NullPointerException ex) {
                LOGGER.debug(ex);
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
            LOGGER.debug(ex);
            throw new DAOException(ex);
        } finally {
            try {
                prepStat.close();
            } catch (SQLException | NullPointerException ex) {
                LOGGER.debug(ex);
            }
        }
    }
}
