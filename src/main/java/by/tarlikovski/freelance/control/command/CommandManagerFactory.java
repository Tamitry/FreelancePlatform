package by.tarlikovski.freelance.control.command;

import by.tarlikovski.freelance.service.ServiceFactory;

public class CommandManagerFactory {
    public static CommandManager getManager(final ServiceFactory factory) {
        return new CommandManagerImpl(factory);
    }
}
