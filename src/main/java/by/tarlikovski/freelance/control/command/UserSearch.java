package by.tarlikovski.freelance.control.command;

import by.tarlikovski.freelance.bean.ServiceName;
import by.tarlikovski.freelance.bean.User;
import by.tarlikovski.freelance.service.CategoryService;
import by.tarlikovski.freelance.service.ServiceException;
import by.tarlikovski.freelance.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UserSearch extends Command {
    public UserSearch() {
        setAddress("/userlist");
    }

    @Override
    public String exec(final HttpServletRequest request,
                       final HttpServletResponse response)
            throws ServiceException {
        UserService service = (UserService) factory.getService(ServiceName.USER_SERVICE);
        CategoryService categoryService = (CategoryService) factory.getService(ServiceName.CATEGORY_SERVICE);
        String search = request.getParameter("searchuser");
        List<User> users = service.findByName(search);
        request.setAttribute("categories", categoryService.findAll());
        request.setAttribute("users", users);
        return "Users list.";
    }
}
