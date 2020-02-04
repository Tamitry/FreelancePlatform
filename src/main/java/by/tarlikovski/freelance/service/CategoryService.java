package by.tarlikovski.freelance.service;

import by.tarlikovski.freelance.bean.Category;
import by.tarlikovski.freelance.exception.PersistentException;

import java.util.List;

public interface CategoryService extends Service {
    List<Category> findAll() throws PersistentException;
}
