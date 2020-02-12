package by.tarlikovski.freelance.control.command;

import by.tarlikovski.freelance.control.ControlException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommandManager {
    String execute(final Command command,
                            final HttpServletRequest request,
                            final HttpServletResponse response)
            throws ControlException;

    void close() throws ControlException;
}
