package by.tarlikovski.freelance.control.command;

import by.tarlikovski.freelance.bean.*;
import by.tarlikovski.freelance.service.CategoryService;
import by.tarlikovski.freelance.service.ServiceException;
import by.tarlikovski.freelance.service.SkillService;
import by.tarlikovski.freelance.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

public class EditProfile extends Command {

    public EditProfile() {
        setAddress("/profile");
        Set<Role> roles = getRoles();
        roles.add(Role.ADMIN);
        roles.add(Role.FREELANCER);
        roles.add(Role.CLIENT);
    }

    @Override
    public String exec(final HttpServletRequest request,
                       final HttpServletResponse response)
            throws ServiceException {
        UserService userService = (UserService) factory.getService(ServiceName.USER_SERVICE);
        CategoryService categoryService = (CategoryService) factory.getService(ServiceName.CATEGORY_SERVICE);
        SkillService skillService = (SkillService) factory.getService(ServiceName.SKILL_SERVICE);
        List<Category> categories = categoryService.findAll();
        User user = (User) request.getSession().getAttribute("user");
        user.setFirstName(request.getParameter("firstname"));
        user.setLastName(request.getParameter("lastname"));
        for (Category category : categories) {
            String req = request.getParameter("" + category.getId());
            skillService.deleteSkill(user, category);
            if (req != null) {
                skillService.createSkill(user, category);
            }
        }
        return "Redirect"; //redirect
    }
}
