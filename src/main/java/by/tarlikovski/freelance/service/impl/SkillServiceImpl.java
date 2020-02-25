package by.tarlikovski.freelance.service.impl;

import by.tarlikovski.freelance.bean.Category;
import by.tarlikovski.freelance.bean.Skill;
import by.tarlikovski.freelance.bean.Type;
import by.tarlikovski.freelance.bean.User;
import by.tarlikovski.freelance.dao.CategoryDao;
import by.tarlikovski.freelance.dao.SkillDao;
import by.tarlikovski.freelance.dao.DAOException;
import by.tarlikovski.freelance.dao.UserDao;
import by.tarlikovski.freelance.service.ServiceException;
import by.tarlikovski.freelance.service.SkillService;

import java.util.ArrayList;
import java.util.List;

public class SkillServiceImpl extends ServiceImpl implements SkillService {
    @Override
    public List<Category> findUserSkills(final User user) throws ServiceException {
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
            throw new ServiceException(ex);
        }
    }

    @Override
    public List<User> findUsersBySkill(final Category category)
            throws ServiceException {
        try {
            SkillDao skillDao = (SkillDao) transaction.createDao(Type.SKILL_DAO);
            UserDao userDao = (UserDao) transaction.createDao(Type.USER_DAO);
            List<Skill> skills = skillDao.findUsersBySkill(category);
            List<User> users = new ArrayList<>();
            for (Skill skill : skills) {
                users.add(userDao.read(skill.getUserId()).get());
            }
            transaction.commit();
            return users;
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public int createSkill(final User user,
                            final Category category)
            throws ServiceException {
        Skill skill = new Skill();
        try {
            int i;
            SkillDao skillDao = (SkillDao) transaction.createDao(Type.SKILL_DAO);
            skill.setCategoryId(category.getId());
            skill.setUserId(user.getId());
            i = skillDao.create(skill);
            transaction.commit();
            return i;
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int deleteSkill(final User user,
                            final Category category)
            throws ServiceException {
        try {
            int i = 0;
            SkillDao skillDao = (SkillDao) transaction.createDao(Type.SKILL_DAO);
            List<Skill> skills = skillDao.findByUser(user);
            for (Skill skill : skills) {
                if (skill.getCategoryId() == category.getId()) {
                    i += skillDao.delete(skill.getId());
                }
            }
            transaction.commit();
            return i;
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
