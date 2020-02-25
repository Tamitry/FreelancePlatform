package by.tarlikovski.freelance.dao;

import by.tarlikovski.freelance.bean.Category;
import by.tarlikovski.freelance.bean.Skill;
import by.tarlikovski.freelance.bean.User;

import java.util.List;

public interface SkillDao extends Dao<Skill> {
    List<Skill> findByUser(User user) throws DAOException;

    List<Skill> findUsersBySkill(Category category) throws DAOException;
}
