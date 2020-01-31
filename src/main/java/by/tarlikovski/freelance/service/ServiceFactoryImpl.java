package by.tarlikovski.freelance.service;

import by.tarlikovski.freelance.bean.ServiceName;
import by.tarlikovski.freelance.dao.transaction.Impl.TransactionFactoryImpl;
import by.tarlikovski.freelance.dao.transaction.TransactionFactory;
import by.tarlikovski.freelance.exception.DAOException;
import by.tarlikovski.freelance.exception.PersistentException;

public class ServiceFactoryImpl implements ServiceFactory {
    private TransactionFactory transactionFactory;

    public ServiceFactoryImpl() throws PersistentException {
        try {
            transactionFactory = new TransactionFactoryImpl();
        } catch (DAOException ex) {
            throw new PersistentException(ex);
        }
    }

    @Override
    public Service getService(final ServiceName type) throws PersistentException {
        try {
            switch (type) {
                case REG_FIRST:
                case REG_CONFIRM:
                case FIND_ALL:
                    UserServiceImpl userService = new UserServiceImpl();
                    userService.setTransaction(transactionFactory.createTransaction());
                    return userService;
                default:
                    throw new PersistentException("Service is not exist.");
            }
        } catch (DAOException ex) {
            throw new PersistentException(ex);
        }
    }

    public static void init() throws PersistentException {
        try {
            TransactionFactoryImpl.init();
        } catch (DAOException ex) {
            throw new PersistentException(ex);
        }
    }

    @Override
    public void close() throws PersistentException {
        try {
            transactionFactory.close();
        } catch (DAOException ex) {
            throw new PersistentException(ex);
        }
    }
}
