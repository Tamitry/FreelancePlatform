package by.tarlikovski.freelance.control.command;

import by.tarlikovski.freelance.bean.Role;
import by.tarlikovski.freelance.bean.ServiceName;
import by.tarlikovski.freelance.bean.User;
import by.tarlikovski.freelance.service.ServiceException;
import by.tarlikovski.freelance.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

public class DeleteUser extends Command {
    public DeleteUser() {
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
        UserService userService = (UserService) factory.getService(ServiceName.USER_SERVICE);
        userService.deleteUser(((User) request.getSession().getAttribute("user")).getId());
        request.getSession().invalidate();
        return "User deleted."; //redirect
    }
}
