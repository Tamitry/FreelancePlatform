package by.tarlikovski.freelance.control.command;

import by.tarlikovski.freelance.bean.Role;
import by.tarlikovski.freelance.bean.ServiceName;
import by.tarlikovski.freelance.bean.User;
import by.tarlikovski.freelance.service.ServiceException;
import by.tarlikovski.freelance.service.UserService;

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
        User user = new User();
        if (request.getParameter("role").equals("client")) {
            user.setRole(Role.CLIENT);
        } else {
            user.setRole(Role.FREELANCER);
        }

        String login = (String) request.getParameter("log_in");
        String email = (String) request.getParameter("email");
        String pass = (String) request.getParameter("password");
        String repeat = (String) request.getParameter("repeat");
        String firstName = (String) request.getParameter("firstname");
        String lastName = (String) request.getParameter("lastname");
        if (!pass.equals(repeat) && !pass.equals("")) {
            request.setAttribute("error", "Passwords aren't equal.");
            setAddress("/error");
            return "error";
        }
        user.setPassword(pass);
        if (service.findByLogin(login).isPresent() && !login.equals("")) {
            request.setAttribute("error", "User's is not unique.");
            setAddress("/error");//TODO Replace
            return "error";
        }
        user.setLogin(login);
        if (service.findByEmail(email).isPresent() && !email.equals("")) {
            request.setAttribute("error", "User's e-mail is not unique.");
            setAddress("/error");
            return "error";
        }
        user.setEmail(email);
        if (!firstName.equals("")) {
            user.setFirstName(firstName);
        }
        if (!lastName.equals("")){
            user.setLastName(lastName);
        }
        service.userRegistration(user);
        user = service.findByLogin(user.getLogin()).get();
        request.getSession().setAttribute("user", user);
        return "Success.";
    }
}
