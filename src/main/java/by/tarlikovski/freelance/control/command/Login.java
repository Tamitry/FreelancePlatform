package by.tarlikovski.freelance.control.command;

import by.tarlikovski.freelance.bean.ServiceName;
import by.tarlikovski.freelance.bean.User;
import by.tarlikovski.freelance.exception.PersistentException;
import by.tarlikovski.freelance.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login extends Command {

    public Login() {
        setName("/index");
    }

    @Override
    public String exec(final HttpServletRequest request,
                       final HttpServletResponse response)
            throws PersistentException {
        UserService userService = (UserService) factory.getService(ServiceName.LOGIN);
        User user = null;
        if (userService.findByLogin(request.getParameter("login")).isPresent()) {
            user = userService.findByLogin(request.getParameter("login")).get();
        } else {
            request.setAttribute("error", "User does not exist.");
            setName("/error");
            return "Error";
        }
        if (user.getPassword().equals(request.getParameter("password"))) {
            request.getSession().setAttribute("user", user);
            return "Success";
        } else {
            request.setAttribute("error", "Wrong password.");
            setName("/error");
            return "Error";
        }
    }
}
