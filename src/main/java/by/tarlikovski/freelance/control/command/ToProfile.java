package by.tarlikovski.freelance.control.command;

import by.tarlikovski.freelance.bean.ServiceName;
import by.tarlikovski.freelance.bean.User;
import by.tarlikovski.freelance.exception.PersistentException;
import by.tarlikovski.freelance.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ToProfile extends Command {
    public ToProfile() {
        setName("/profile");
    }

    @Override
    public String exec(final HttpServletRequest request,
                       final HttpServletResponse response)
            throws PersistentException {
        int id = Integer.parseInt(request.getParameter("userid"));
        UserService userService = (UserService) factory.getService(ServiceName.FIND_ALL);
        User user = userService.findById(id).get();
        request.setAttribute("user", user);
        return "To profile";
    }
}
