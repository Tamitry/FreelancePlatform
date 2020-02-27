package by.tarlikovski.freelance.control.command.registered;

import by.tarlikovski.freelance.bean.Role;
import by.tarlikovski.freelance.control.command.Command;
import by.tarlikovski.freelance.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

public class Exit extends Command {
    public Exit() {
        setAddress("/index");
        Set<Role> roles = getRoles();
        roles.add(Role.ADMIN);
        roles.add(Role.FREELANCER);
        roles.add(Role.CLIENT);
    }

    @Override
    public String exec(final HttpServletRequest request,
                       final HttpServletResponse response)
            throws ServiceException {
        request.getSession().invalidate();
        request.setAttribute("url", request.getContextPath());
        return "Redirect";
    }
}
