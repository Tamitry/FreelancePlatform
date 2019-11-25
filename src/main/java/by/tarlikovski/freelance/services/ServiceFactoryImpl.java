package by.tarlikovski.freelance.services;

import by.tarlikovski.freelance.dao.TransactionFactory;
import by.tarlikovski.freelance.errors.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ServiceFactoryImpl implements ServiceFactory {

    private static final Logger LOGGER
            = LogManager.getLogger(ServiceFactoryImpl.class);
    private TransactionFactory transFact;

    public ServiceFactoryImpl(final TransactionFactory tf)
            throws PersistentException {
        this.transFact = tf;
    }

    @Override
    public Service getServices(final String key)
            throws PersistentException {
        Service service = null;
        TransactionFactory tf = null;
        try {

        } catch (final PersistentException e) {
            LOGGER.error(e);
            throw new PersistentException(e);
        }
    }

    @Override
    public void close() {

    }
}
