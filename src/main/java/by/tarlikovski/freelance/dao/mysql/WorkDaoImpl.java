package by.tarlikovski.freelance.dao.mysql;

import by.tarlikovski.freelance.bean.Order;
import by.tarlikovski.freelance.bean.Status;
import by.tarlikovski.freelance.bean.User;
import by.tarlikovski.freelance.bean.Work;
import by.tarlikovski.freelance.dao.DAOException;
import by.tarlikovski.freelance.dao.WorkDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WorkDaoImpl extends BaseDaoImpl implements WorkDao {
    private static final String FIND_BY_USER = "select WorkId, OrderId, UserId, Grade, Status from works where UserId = ?";
    private static final String FIND_BY_ORDER = "select WorkId, OrderId, UserId, Grade, Status from works where OrderId = ?";
    private static final String READ = "select WorkId, OrderId, UserId, Grade, Status from works where WorkId = ?";
    private static final String CREATE = "insert into works (OrderId, UserId, Grade) values (?,?,?)";
    private static final String UPDATE = "update works set OrderId = ?, UserId = ?, Grade = ?, Status = ? where WorkId = ?";
    private static final String DELETE = "delete from works where WorkId = ?";
    @Override
    public List<Work> findByUser(final User user)
            throws DAOException {
        PreparedStatement prepState = null;
        ResultSet resSet = null;
        int i = 1;
        try {
            prepState = connection.prepareCall(FIND_BY_USER);
            prepState.setInt(i, user.getId());
            resSet = prepState.executeQuery();
            List<Work> works = new ArrayList<>();
            Work work = null;
            while (resSet.next()) {
                work = new Work();
                work.setId(resSet.getInt("WorkId"));
                Order order = new Order();
                order.setId(resSet.getInt("OrderId"));
                work.setOrder(order);
                User usr = new User();
                usr.setId(resSet.getInt("UserId"));
                work.setUser(usr);
                work.setGrade(resSet.getByte("Grade"));
                work.setStatus(Status.getStatus(resSet.getInt("Status")));
                works.add(work);
            }
            return works;
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
    public List<Work> findByOrder(final Order order)
            throws DAOException {
        PreparedStatement prepState = null;
        ResultSet resSet = null;
        int i = 1;
        try {
            prepState = connection.prepareCall(FIND_BY_ORDER);
            prepState.setInt(i, order.getId());
            resSet = prepState.executeQuery();
            List<Work> works = new ArrayList<>();
            Work work = null;
            while (resSet.next()) {
                work = new Work();
                work.setId(resSet.getInt("WorkId"));
                Order ord = new Order();
                ord.setId(resSet.getInt("OrderId"));
                work.setOrder(ord);
                User user = new User();
                user.setId(resSet.getInt("UserId"));
                work.setUser(user);
                work.setGrade(resSet.getByte("Grade"));
                work.setStatus(Status.getStatus(resSet.getInt("Status")));
                works.add(work);
            }
            return works;
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
    public int create(final Work entity)
            throws DAOException {
        PreparedStatement prepStat = null;
        ResultSet resSet = null;
        int i = 1;
        int v;
        try {
            prepStat = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            prepStat.setInt(i++, entity.getOrder().getId());
            prepStat.setInt(i++, entity.getUser().getId());
            prepStat.setByte(i, entity.getGrade());
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
    public Optional<Work> read(final Integer identity)
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
                Work work = new Work();
                work.setId(resSet.getInt("WorkId"));
                Order order = new Order();
                order.setId(resSet.getInt("OrderId"));
                work.setOrder(order);
                User user = new User();
                user.setId(resSet.getInt("UserId"));
                work.setUser(user);
                work.setGrade(resSet.getByte("Grade"));
                return Optional.ofNullable(work);
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
    public int update(final Work entity) throws DAOException {
        PreparedStatement prepStat = null;
        int i = 1;
        try {
            prepStat = connection.prepareStatement(UPDATE);
            prepStat.setInt(i++, entity.getUser().getId());
            prepStat.setByte(i++, entity.getGrade());
            prepStat.setInt(i++, entity.getStatus().getStatusNum());
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
