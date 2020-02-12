package by.tarlikovski.freelance.control.command;

import by.tarlikovski.freelance.control.ControlException;
import by.tarlikovski.freelance.service.ServiceException;
import by.tarlikovski.freelance.service.ServiceFactory;
import by.tarlikovski.freelance.service.impl.ServiceFactoryImpl;

public class CommandManagerFactory {
    public static CommandManager getManager() throws ControlException {
        ServiceFactory factory = null;
        try {
            factory = new ServiceFactoryImpl();
        } catch (ServiceException e) {
            throw new ControlException(e);
        }
        return new CommandManagerImpl(factory);
    }
}
