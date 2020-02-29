package by.tarlikovski.freelance.control.command.registered;

import by.tarlikovski.freelance.bean.Role;
import by.tarlikovski.freelance.bean.ServiceName;
import by.tarlikovski.freelance.bean.User;
import by.tarlikovski.freelance.bean.UserStatus;
import by.tarlikovski.freelance.control.command.Command;
import by.tarlikovski.freelance.service.ServiceException;
import by.tarlikovski.freelance.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Base64;
import java.util.Optional;
import java.util.Set;

public class BanUser extends Command {

    private static final Logger LOGGER = LogManager.getLogger(BanUser.class);

    public BanUser() {
        setAddress("/userban");
        Set<Role> roles = getRoles();
        roles.add(Role.CLIENT);
        roles.add(Role.FREELANCER);
        roles.add(Role.ADMIN);
    }

    @Override
    public String exec(final HttpServletRequest request,
                       final HttpServletResponse response)
            throws ServiceException {
        UserService userService = (UserService) factory.getService(ServiceName.USER_SERVICE);
        Optional<User> user;
        if (!(request.getParameter("userid").equals("") || request.getParameter("userid") == null)
                && ((User) request.getSession().getAttribute("user")).getRole() == Role.ADMIN) {
            user = userService.read(Integer.parseInt(request.getParameter("userid")));
        } else {
            user = userService.read(((User) request.getSession().getAttribute("user")).getId());
        }
        LOGGER.info("User " + ((User) request.getSession().getAttribute("user"))
                .getLogin() + " delete user " + userService.read(Integer
                .parseInt(request.getParameter("userid"))));
        if (user.isPresent()) {
            user.get().setUserStatus(UserStatus.BANNED);
            if (((User) request.getSession().getAttribute("user")).getRole() == Role.ADMIN) {
                String path = request.getHeader("Referer");
                request.setAttribute("url", path);
            } else {
                request.setAttribute("url", request.getContextPath());
                request.getSession().invalidate();
            }
            userService.update(user.get());;
            return "Redirect";
        } else {
            request.setAttribute("error", "usernotexist");
            setAddress("/error");
            return "error";
        }
    }
}
