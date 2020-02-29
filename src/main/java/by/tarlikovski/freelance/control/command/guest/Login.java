package by.tarlikovski.freelance.control.command.guest;

import by.tarlikovski.freelance.bean.ServiceName;
import by.tarlikovski.freelance.bean.User;
import by.tarlikovski.freelance.control.command.Command;
import by.tarlikovski.freelance.service.PasswordEncoder;
import by.tarlikovski.freelance.service.ServiceException;
import by.tarlikovski.freelance.service.UserService;
import by.tarlikovski.freelance.service.Validator;
import by.tarlikovski.freelance.service.impl.UserLogValidatorImpl;

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
        Validator userLogValidator = (Validator) factory.getService(ServiceName.LOG_VALIDATOR);
        UserService service = (UserService) factory.getService(ServiceName.USER_SERVICE);
        User user = new User();
        if (!request.getParameter("login").equals("")) {
            user.setLogin(request.getParameter("login"));
        } else {
            request.setAttribute("error", "loginpattern");
            setAddress("/error");
            return "Error";
        }
        String password = request.getParameter("password");
        if (!request.getParameter("password").equals("")) {
            user.setPassword(request.getParameter("password"));
        } else {
            request.setAttribute("error", "passpattern");
            setAddress("/error");
            return "Error";
        }
        try {
            userLogValidator.validate(user);
            request.getSession().setAttribute("user", service
                    .findByLogin(user.getLogin()).get());
            request.setAttribute("url", request.getContextPath());
            return "Redirect";
        } catch (ServiceException ex) {
            request.setAttribute("error", ex.getMessage());
            setAddress("/error");
            return "Error";
        }
    }
}
