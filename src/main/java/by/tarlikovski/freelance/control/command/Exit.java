package by.tarlikovski.freelance.control.command;

import by.tarlikovski.freelance.exception.PersistentException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Exit extends Command {
    public Exit() {
        setName("/index");
    }

    @Override
    public String exec(final HttpServletRequest request,
                       final HttpServletResponse response)
            throws PersistentException {
        request.getSession().invalidate();
        return "Exit";
    }
}
