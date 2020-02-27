package by.tarlikovski.freelance.control.command.guest;

import by.tarlikovski.freelance.control.command.Command;
import by.tarlikovski.freelance.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LocaleChange extends Command {
    @Override
    public String exec(final HttpServletRequest request,
                       final HttpServletResponse response)
            throws ServiceException {
        String path = request.getHeader("Referer");
        request.setAttribute("url", path);
        return "Redirect";
    }
}
