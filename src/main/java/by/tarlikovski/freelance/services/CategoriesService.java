package by.tarlikovski.freelance.services;

import by.tarlikovski.freelance.entities.Category;
import by.tarlikovski.freelance.errors.PersistentException;

import java.util.List;

public interface CategoriesService extends Service {
    List<Category> findAllCategories() throws PersistentException;
}
