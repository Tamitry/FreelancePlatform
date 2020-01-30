package by.tarlikovski.freelance.control.command;

import by.tarlikovski.freelance.exception.ControlException;
import by.tarlikovski.freelance.exception.PersistentException;
import by.tarlikovski.freelance.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommandManagerImpl implements CommandManager {
    private ServiceFactory factory;

    public CommandManagerImpl(final ServiceFactory fact) {
        factory = fact;
    }

    @Override
    public String execute(final Command command,
                          final HttpServletRequest request,
                          final HttpServletResponse response)
            throws ControlException {
        command.setFactory(factory);
        try {
            return command.exec(request, response);
        } catch (PersistentException ex) {
            throw new ControlException(ex);
        }
    }

    @Override
    public void close() throws ControlException {
        try {
            factory.close();
        } catch (PersistentException ex) {
            throw new ControlException(ex);
        }
    }
}
