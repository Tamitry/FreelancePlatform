package by.tarlikovski.freelance.service;

import by.tarlikovski.freelance.bean.Category;
import by.tarlikovski.freelance.bean.User;
import by.tarlikovski.freelance.exception.PersistentException;

import java.util.List;

public interface SkillService extends Service {
    List<Category> findUserSkills(final User user) throws PersistentException;

    void createSkill(User user, Category category) throws PersistentException;

    void deleteSkill(User user, Category category) throws PersistentException;
}
