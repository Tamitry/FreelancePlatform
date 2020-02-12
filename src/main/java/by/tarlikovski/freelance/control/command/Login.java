package by.tarlikovski.freelance.control.command;

import by.tarlikovski.freelance.bean.ServiceName;
import by.tarlikovski.freelance.bean.User;
import by.tarlikovski.freelance.service.ServiceException;
import by.tarlikovski.freelance.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login extends Command {

    public Login() {
        setAddress("/index");
    }

    @Override
    public String exec(final HttpServletRequest request,
                       final HttpServletResponse response)
            throws ServiceException {
        UserService userService = (UserService) factory.getService(ServiceName.USER_SERVICE);
        User user = null;
        if (userService.findByLogin(request.getParameter("login")).isPresent()
                && !request.getParameter("login").equals("")) {
            user = userService.findByLogin(request.getParameter("login")).get();
        } else {
            request.setAttribute("error", "User does not exist.");
            setAddress("/error");
            return "Error";
        }
        if (user.getPassword().equals(request.getParameter("password"))
                && !request.getParameter("password").equals("")) {
            request.getSession().setAttribute("user", user);
            return "Success";
        } else {
            request.setAttribute("error", "Wrong password.");
            setAddress("/error");
            return "Error";
        }
    }
}
