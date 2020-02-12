package by.tarlikovski.freelance.control.command;

import by.tarlikovski.freelance.bean.Category;
import by.tarlikovski.freelance.bean.ServiceName;
import by.tarlikovski.freelance.service.CategoryService;
import by.tarlikovski.freelance.service.ServiceException;
import by.tarlikovski.freelance.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ToEditProfile extends Command {
    public ToEditProfile() {
        setAddress("/editprofile");
    }

    @Override
    public String exec(final HttpServletRequest request,
                       final HttpServletResponse response)
            throws ServiceException {
        int id = Integer.parseInt(request.getParameter("userid"));
        UserService userService = (UserService) factory.getService(ServiceName.USER_SERVICE);
        CategoryService catService = (CategoryService) factory.getService(ServiceName.CATEGORY_SERVICE);
        request.setAttribute("categories", catService.findAll());
        request.setAttribute("user", userService.findById(id).get());
        return "To edit profile.";
    }
}
