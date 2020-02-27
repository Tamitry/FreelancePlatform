package by.tarlikovski.freelance.control.command.guest;

import by.tarlikovski.freelance.bean.ServiceName;
import by.tarlikovski.freelance.bean.User;
import by.tarlikovski.freelance.control.command.Command;
import by.tarlikovski.freelance.service.PasswordEncoder;
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
        PasswordEncoder passwordEncoder = (PasswordEncoder) factory.getService(ServiceName.ENCODER);
        User user = null;
        if (userService.findByLogin(request.getParameter("login")).isPresent()
                && !request.getParameter("login").equals("")) {
            user = userService.findByLogin(request.getParameter("login")).get();
        } else {
            request.setAttribute("error", "usernotexist");
            setAddress("/error");
            return "Error";
        }
        String password = request.getParameter("password");
        if (!request.getParameter("password").equals("")
                && passwordEncoder.check(password, user.getPassword())) {
            request.getSession().setAttribute("user", user);
            request.setAttribute("url", request.getContextPath());
            return "Redirect";
        } else {
            request.setAttribute("error", "wrongpassword");
            setAddress("/error");
            return "Error";
        }
    }
}
