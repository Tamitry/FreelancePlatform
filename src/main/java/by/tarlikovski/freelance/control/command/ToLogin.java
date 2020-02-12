package by.tarlikovski.freelance.control.command;

import by.tarlikovski.freelance.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ToLogin extends Command {

    public ToLogin() {
        setAddress("/login");
    }
    @Override
    public String exec(final HttpServletRequest request,
                       final HttpServletResponse response)
            throws ServiceException {
        return "To login";
    }
}
