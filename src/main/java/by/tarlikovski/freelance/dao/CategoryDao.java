package by.tarlikovski.freelance.dao;

import by.tarlikovski.freelance.bean.Category;
import by.tarlikovski.freelance.bean.User;

import java.util.List;

public interface CategoryDao extends Dao<Category> {
    List<Category> findAll();

    List<Category> findSkills(User user);
}
