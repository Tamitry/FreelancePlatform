package by.tarlikovski.freelance.service;

import by.tarlikovski.freelance.bean.ServiceName;

public interface ServiceFactory {
    Service getService(ServiceName type) throws ServiceException;

    void close() throws ServiceException;

    //static ServiceFactory getFactory();
}
