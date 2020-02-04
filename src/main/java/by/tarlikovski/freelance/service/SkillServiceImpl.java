package by.tarlikovski.freelance.service;

import by.tarlikovski.freelance.bean.Category;
import by.tarlikovski.freelance.bean.Skill;
import by.tarlikovski.freelance.bean.Type;
import by.tarlikovski.freelance.bean.User;
import by.tarlikovski.freelance.dao.CategoryDao;
import by.tarlikovski.freelance.dao.SkillDao;
import by.tarlikovski.freelance.exception.DAOException;
import by.tarlikovski.freelance.exception.PersistentException;

import java.util.ArrayList;
import java.util.List;

public class SkillServiceImpl extends ServiceImpl implements SkillService {
    @Override
    public List<Category> findUserSkills(final User user) throws PersistentException {
        try {
            SkillDao skillDao = (SkillDao) transaction.createDao(Type.SKILL_DAO);
            CategoryDao categoryDao = (CategoryDao) transaction.createDao(Type.CATEGORY_DAO);
            List<Skill> skills = skillDao.findByUser(user);
            List<Category> categories = new ArrayList<>();
            for (Skill skill : skills) {
                categories.add(categoryDao.read(skill.getCategoryId()).get());
            }
            transaction.commit();
            return categories;
        } catch (DAOException ex) {
            throw new PersistentException(ex);
        }
    }//TODO Cascade delete and update.

    @Override
    public void createSkill(final User user,
                            final Category category)
            throws PersistentException {
        Skill skill = new Skill();
        try {
            SkillDao skillDao = (SkillDao) transaction.createDao(Type.SKILL_DAO);
            skill.setCategoryId(category.getId());
            skill.setUserId(user.getId());
            skillDao.create(skill);
            transaction.commit();
        } catch (DAOException e) {
            throw new PersistentException(e);
        }
    }

    @Override
    public void deleteSkill(final User user,
                            final Category category)
            throws PersistentException {
        try {
            SkillDao skillDao = (SkillDao) transaction.createDao(Type.SKILL_DAO);
            List<Skill> skills = skillDao.findByUser(user);
            for (Skill skill : skills) {
                if (skill.getCategoryId() == category.getId()) {
                    skillDao.delete(skill.getId());
                }
            }
            transaction.commit();
        } catch (DAOException e) {
            throw new PersistentException(e);
        }
    }
}
