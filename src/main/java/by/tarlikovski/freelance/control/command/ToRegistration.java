package by.tarlikovski.freelance.control.command;

import by.tarlikovski.freelance.exception.PersistentException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ToRegistration extends Command {

    public ToRegistration() {
        setName("/toregistration");
    }

    @Override
    public String exec(final HttpServletRequest request,
                       final HttpServletResponse response)
            throws PersistentException {
        return "To registration.";
    }
}
