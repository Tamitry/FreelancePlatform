package by.tarlikovski.freelance.control.command;

import by.tarlikovski.freelance.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Exit extends Command {
    public Exit() {
        setAddress("/index");
    }

    @Override
    public String exec(final HttpServletRequest request,
                       final HttpServletResponse response)
            throws ServiceException {
        request.getSession().invalidate();
        return "Exit";
    }
}
