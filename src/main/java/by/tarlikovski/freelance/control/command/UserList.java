package by.tarlikovski.freelance.control.command;

import by.tarlikovski.freelance.bean.ServiceName;
import by.tarlikovski.freelance.bean.User;
import by.tarlikovski.freelance.exception.PersistentException;
import by.tarlikovski.freelance.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UserList extends Command {

    public UserList() {
        setName("/userlist");
    }

    @Override
    public String exec(final HttpServletRequest request,
                       final HttpServletResponse response)
            throws PersistentException {
        UserService service = (UserService) factory.getService(ServiceName.FIND_ALL);
        List<User> users = service.findAllFreelancers();
        request.setAttribute("users", users);
        return "Users list.";
    }
}
