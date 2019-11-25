package by.tarlikovski.freelance.dao;

import by.tarlikovski.freelance.entities.Client;
import by.tarlikovski.freelance.entities.Order;
import by.tarlikovski.freelance.errors.PersistentException;

import java.util.List;

public interface OrderDAO extends DAO<Integer, Order> {
    List<Order> findByClient(Client client) throws PersistentException;
    List<Order> findByName(String name) throws PersistentException;
}
