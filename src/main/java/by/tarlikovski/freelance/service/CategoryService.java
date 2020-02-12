package by.tarlikovski.freelance.service;

import by.tarlikovski.freelance.bean.Category;

import java.util.List;

public interface CategoryService extends Service {
    List<Category> findAll() throws ServiceException;
}
