package by.tarlikovski.freelance.control.command;

import by.tarlikovski.freelance.bean.Role;
import by.tarlikovski.freelance.control.ControlException;
import by.tarlikovski.freelance.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

public class AddOrder extends Command {
    public AddOrder() {
        setAddress("/order");
        Set<Role> roleSet = getRoles();
        roleSet.add(Role.CLIENT);
    }

    @Override
    public String exec(final HttpServletRequest request,
                       final HttpServletResponse response)
            throws ServiceException {
        return null;
    }
}
