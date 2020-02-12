package by.tarlikovski.freelance.dao.mysql;

import by.tarlikovski.freelance.bean.Skill;
import by.tarlikovski.freelance.bean.User;
import by.tarlikovski.freelance.dao.SkillDao;
import by.tarlikovski.freelance.dao.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SkillDaoImpl extends BaseDaoImpl implements SkillDao {
    private static final String FIND_BY_USER = "select SkillId, UserId, CategoryId from Skills where UserId = ?";
    private static final String CREATE = "insert into skills (UserId, CategoryId) value (?,?)";
    private static final String READ = "select SkillId, UserId, CategoryId from Skills where SkillId = ?";
    private static final String DELETE = "delete from Skills where SkillId = ?";

    @Override
    public List<Skill> findByUser(final User user) throws DAOException {
        PreparedStatement prepStat = null;
        ResultSet resSet = null;
        int i = 1;
        try {
            prepStat = connection.prepareStatement(FIND_BY_USER);
            prepStat.setInt(i, user.getId());
            resSet = prepStat.executeQuery();
            List<Skill> skills = new ArrayList<>();
            Skill skill = null;
            while (resSet.next()) {
                skill = new Skill();
                skill.setId(resSet.getInt("SkillId"));
                skill.setUserId(resSet.getInt("UserId"));
                skill.setCategoryId(resSet.getInt("CategoryId"));
                skills.add(skill);
            }
            return skills;
        } catch (SQLException e) {
            throw new DAOException(e);
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
    public int create(final Skill entity) throws DAOException {
        PreparedStatement prepStat = null;
        ResultSet resSet = null;
        int i = 1;
        int v;
        try {
            prepStat = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            prepStat.setInt(i++, entity.getUserId());
            prepStat.setInt(i, entity.getCategoryId());
            v = prepStat.executeUpdate();
            resSet = prepStat.getGeneratedKeys();
            if (resSet.next()) {
                i = 1;
                entity.setId(resSet.getInt(i));
                return v;
            } else {
                throw new DAOException("An error occurred while adding to the table Skills.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
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
    public Optional<Skill> read(final Integer identity) throws DAOException {
        PreparedStatement prepStat = null;
        ResultSet resSet = null;
        int i = 1;
        try {
            prepStat = connection.prepareStatement(READ);
            prepStat.setInt(i, identity);
            resSet = prepStat.executeQuery();
            if (resSet.next()) {
                Skill skill = new Skill();
                skill.setId(resSet.getInt("SkillId"));
                skill.setUserId(resSet.getInt("UserId"));
                skill.setCategoryId(resSet.getInt("CategoryId"));
                return Optional.ofNullable(skill);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new DAOException(e);
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
    public int update(final Skill entity) throws DAOException {
        throw new DAOException(new UnsupportedOperationException("This table can not be updated."));
    }

    @Override
    public int delete(final Integer identity) throws DAOException {
        PreparedStatement prepStat = null;
        int i = 1;
        try {
            prepStat = connection.prepareStatement(DELETE);
            prepStat.setInt(i, identity);
            return prepStat.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                prepStat.close();
            } catch (SQLException | NullPointerException e) {
            }
        }
    }
}
