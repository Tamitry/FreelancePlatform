package by.tarlikovski.freelance.dao;

import by.tarlikovski.freelance.entities.Skill;
import by.tarlikovski.freelance.errors.PersistentException;

import java.util.List;

public interface SkillsDAO extends DAO<Integer, Skill> {
    List<Skill> findByUser(final int id) throws PersistentException;
}
