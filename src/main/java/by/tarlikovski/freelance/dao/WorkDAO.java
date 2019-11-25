package by.tarlikovski.freelance.dao;

import by.tarlikovski.freelance.entities.Work;
import by.tarlikovski.freelance.errors.PersistentException;

import java.util.List;

public interface WorkDAO extends DAO<Integer, Work> {
    List<Work> findByUser(final int i) throws PersistentException;
}
