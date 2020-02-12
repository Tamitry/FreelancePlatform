package by.tarlikovski.freelance.control.command;

import by.tarlikovski.freelance.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ErrorPage extends Command {

    public ErrorPage() {
        setAddress("/error");
    }

    @Override
    public String exec(final HttpServletRequest request,
                       final HttpServletResponse response)
            throws ServiceException {
        return "Error.";
    }
}
