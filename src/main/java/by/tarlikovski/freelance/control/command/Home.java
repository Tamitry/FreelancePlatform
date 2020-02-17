package by.tarlikovski.freelance.control.command;

import by.tarlikovski.freelance.control.ControlException;
import by.tarlikovski.freelance.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Home extends Command {
    public Home() {
        setAddress("/index");
    }

    @Override
    public String exec(final HttpServletRequest request,
                       final HttpServletResponse response)
            throws ServiceException {
        return "To homepage.";
    }
}
