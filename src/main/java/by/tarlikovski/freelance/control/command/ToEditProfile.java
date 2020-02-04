package by.tarlikovski.freelance.control.command;

import by.tarlikovski.freelance.bean.ServiceName;
import by.tarlikovski.freelance.exception.PersistentException;
import by.tarlikovski.freelance.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ToEditProfile extends Command {
    public ToEditProfile() {
        setName("/toeditprofile");
    }

    @Override
    public String exec(final HttpServletRequest request,
                       final HttpServletResponse response)
            throws PersistentException {
        int id = Integer.parseInt(request.getParameter("userid"));
        UserService userService = (UserService) factory.getService(ServiceName.USER_SERVICE);
        request.setAttribute("user", userService.findById(id).get());
        return "To edit profile.";
    }
}
