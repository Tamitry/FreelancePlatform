package by.tarlikovski.freelance.service;

import by.tarlikovski.freelance.bean.Category;
import by.tarlikovski.freelance.bean.User;

import java.util.List;

public interface SkillService extends Service {
    List<Category> findUserSkills(final User user) throws ServiceException;

    int createSkill(User user, Category category) throws ServiceException;

    int deleteSkill(User user, Category category) throws ServiceException;
}
