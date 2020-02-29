package by.tarlikovski.freelance.control.command.guest;

import by.tarlikovski.freelance.bean.Role;
import by.tarlikovski.freelance.bean.ServiceName;
import by.tarlikovski.freelance.bean.User;
import by.tarlikovski.freelance.control.command.Command;
import by.tarlikovski.freelance.service.PasswordEncoder;
import by.tarlikovski.freelance.service.ServiceException;
import by.tarlikovski.freelance.service.UserService;
import by.tarlikovski.freelance.service.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Registration extends Command {

    private static final Logger LOGGER = LogManager.getLogger(Registration.class);

    public Registration() {
        setAddress("/index");
    }
    @Override
    public String exec(final HttpServletRequest request,
                       final HttpServletResponse response)
            throws ServiceException {
        UserService service = (UserService) factory.getService(ServiceName.USER_SERVICE);
        PasswordEncoder encoder = (PasswordEncoder) factory.getService(ServiceName.ENCODER);
        Validator validator = (Validator) factory.getService(ServiceName.REG_VALIDATOR);
        User user = new User();
        if (request.getParameter("role").equals("client")) {
            user.setRole(Role.CLIENT);
        } else {
            user.setRole(Role.FREELANCER);
        }
        user.setLogin(request.getParameter("login"));
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));
        String repeat = request.getParameter("repeat");
        user.setFirstName(request.getParameter("firstname"));
        user.setLastName(request.getParameter("lastname"));
        if (!user.getPassword().equals(repeat)) {
            request.setAttribute("error", "notequalpass");
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
        user.setPassword(encoder.getSaltedHash(user.getPassword()));
        service.userRegistration(user);
        LOGGER.info("User " + user.getLogin() + "has been created.");
        request.getSession().setAttribute("user", user);
        request.setAttribute("url", request.getContextPath());
        return "Redirect";
    }
}
