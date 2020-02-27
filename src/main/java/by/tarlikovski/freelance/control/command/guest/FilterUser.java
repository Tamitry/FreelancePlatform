package by.tarlikovski.freelance.control.command.guest;

import by.tarlikovski.freelance.bean.Category;
import by.tarlikovski.freelance.bean.ServiceName;
import by.tarlikovski.freelance.bean.User;
import by.tarlikovski.freelance.control.command.Command;
import by.tarlikovski.freelance.service.CategoryService;
import by.tarlikovski.freelance.service.ServiceException;
import by.tarlikovski.freelance.service.SkillService;
import by.tarlikovski.freelance.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class FilterUser extends Command {

    public FilterUser() {
        setAddress("/userlist");
    }

    @Override
    public String exec(final HttpServletRequest request,
                       final HttpServletResponse response)
            throws ServiceException {
        UserService userService = (UserService) factory.getService(ServiceName.USER_SERVICE);
        CategoryService categoryService = (CategoryService) factory.getService(ServiceName.CATEGORY_SERVICE);
        SkillService skillService = (SkillService) factory.getService(ServiceName.SKILL_SERVICE);
        String filter = request.getParameter("filter");
        List<User> users = new ArrayList<>();
        List<Category> categories = categoryService.findAll();
        if (filter.equals("")) {
            users = userService.findAllFreelancers();
        } else {
            Category filterCategory = null;
            for (Category category : categories) {
                if (category.getName().equals(filter)) {
                    filterCategory = category;
                }
            }
            if (filterCategory != null) {
                users = skillService.findUsersBySkill(filterCategory);
            }
        }
        request.setAttribute("filter", filter);
        request.setAttribute("categories", categoryService.findAll());
        request.setAttribute("users", users);
        return "User list.";
    }
}
