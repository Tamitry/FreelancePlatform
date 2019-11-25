package by.tarlikovski.freelance.services;

import by.tarlikovski.freelance.errors.PersistentException;

public interface ServiceFactory {
    Service getServices(final String key) throws PersistentException;
    void close() throws PersistentException;
}
