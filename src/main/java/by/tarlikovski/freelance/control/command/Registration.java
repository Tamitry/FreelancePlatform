package by.tarlikovski.freelance.control.command;

import by.tarlikovski.freelance.bean.Role;
import by.tarlikovski.freelance.bean.ServiceName;
import by.tarlikovski.freelance.bean.User;
import by.tarlikovski.freelance.service.ServiceException;
import by.tarlikovski.freelance.service.UserService;
import by.tarlikovski.freelance.service.UserValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Registration extends Command {

    public Registration() {
        setAddress("/index");
    }
    @Override
    public String exec(final HttpServletRequest request,
                       final HttpServletResponse response)
            throws ServiceException {
        UserService service = (UserService) factory.getService(ServiceName.USER_SERVICE);
        UserValidator validator = (UserValidator) factory.getService(ServiceName.VALIDATOR);
        User user = new User();
        if (request.getParameter("role").equals("client")) {
            user.setRole(Role.CLIENT);
        } else {
            user.setRole(Role.FREELANCER);
        }

        user.setLogin((String) request.getParameter("log_in"));
        user.setEmail((String) request.getParameter("email"));
        user.setPassword((String) request.getParameter("password"));
        String repeat = (String) request.getParameter("repeat");
        user.setFirstName((String) request.getParameter("firstname"));
        user.setLastName((String) request.getParameter("lastname"));
        if (!user.getPassword().equals(repeat)) {
            request.setAttribute("error", "Passwords aren't equal.");
            setAddress("/error");
            return "error";
        }
        try {
            validator.validate(user);
        } catch (ServiceException e) {
            request.setAttribute("error", e.getMessage());
            setAddress("/error");
            return "error";
        }
        service.userRegistration(user);
        request.getSession().setAttribute("user", user);
        return "Success.";
    }
}
