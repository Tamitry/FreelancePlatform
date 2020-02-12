package by.tarlikovski.freelance.control.command;

import by.tarlikovski.freelance.control.ControlException;
import by.tarlikovski.freelance.service.ServiceException;
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
        } catch (ServiceException ex) {
            throw new ControlException(ex);
        }
    }

    @Override
    public void close() throws ControlException {
        try {
            factory.close();
        } catch (ServiceException ex) {
            throw new ControlException(ex);
        }
    }
}
