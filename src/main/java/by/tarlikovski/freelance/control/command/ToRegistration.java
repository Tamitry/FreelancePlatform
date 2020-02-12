package by.tarlikovski.freelance.control.command;

import by.tarlikovski.freelance.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ToRegistration extends Command {

    public ToRegistration() {
        setAddress("/toregistration");
    }

    @Override
    public String exec(final HttpServletRequest request,
                       final HttpServletResponse response)
            throws ServiceException {
        return "To registration.";
    }
}
