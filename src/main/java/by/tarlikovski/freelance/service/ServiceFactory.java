package by.tarlikovski.freelance.service;

import by.tarlikovski.freelance.bean.ServiceName;
import by.tarlikovski.freelance.exception.PersistentException;

public interface ServiceFactory {
    Service getService(ServiceName type) throws PersistentException;

    void close() throws PersistentException;

    //static ServiceFactory getFactory();
}
