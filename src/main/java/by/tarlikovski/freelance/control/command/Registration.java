package by.tarlikovski.freelance.control.command;

import by.tarlikovski.freelance.bean.Role;
import by.tarlikovski.freelance.bean.ServiceName;
import by.tarlikovski.freelance.bean.User;
import by.tarlikovski.freelance.exception.PersistentException;
import by.tarlikovski.freelance.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Registration extends Command {

    public Registration() {
        setName("/index");
    }
    @Override
    public String exec(final HttpServletRequest request,
                       final HttpServletResponse response)
            throws PersistentException {
        UserService service = (UserService) factory.getService(ServiceName.REG_CONFIRM);
        User user = new User();
        if (request.getParameter("role").equals("client")) {
            user.setRole(Role.CLIENT);
        } else {
            user.setRole(Role.FREELANCER);
        }
        String login = (String) request.getParameter("login");
        String email = (String) request.getParameter("email");
        String pass = (String) request.getParameter("password");
        String repeat = (String) request.getParameter("repeat");
        String firstName = (String) request.getParameter("firstname");
        String lastName = (String) request.getParameter("lastname");
        if (service.findByLogin(login).isPresent()) {
            request.setAttribute("error", "User's is not unique.");
            setName("/error");
            return "error";
        }
        user.setLogin(login);
        if (service.findByEmail(email).isPresent()) {
            request.setAttribute("error", "User's e-mail is not unique.");
            setName("/error");
            return "error";
        }
        user.setEmail(email);
        if (!pass.equals(repeat)) {
            request.setAttribute("error", "Passwords aren't equal.");
            setName("/error");
            return "error";
        }
        user.setPassword(pass);
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
