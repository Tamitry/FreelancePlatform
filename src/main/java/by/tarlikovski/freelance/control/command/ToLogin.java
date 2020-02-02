package by.tarlikovski.freelance.control.command;

import by.tarlikovski.freelance.exception.PersistentException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ToLogin extends Command {

    public ToLogin() {
        setName("/login");
    }
    @Override
    public String exec(final HttpServletRequest request,
                       final HttpServletResponse response)
            throws PersistentException {
        return "To login";
    }
}
