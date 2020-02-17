package by.tarlikovski.freelance.control.command;

import by.tarlikovski.freelance.bean.Category;
import by.tarlikovski.freelance.bean.Role;
import by.tarlikovski.freelance.bean.ServiceName;
import by.tarlikovski.freelance.bean.User;
import by.tarlikovski.freelance.service.CategoryService;
import by.tarlikovski.freelance.service.ServiceException;
import by.tarlikovski.freelance.service.SkillService;
import by.tarlikovski.freelance.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ToEditProfile extends Command {
    public ToEditProfile() {
        setAddress("/editprofile");
        Set<Role> roles = getRoles();
        roles.add(Role.ADMIN);
        roles.add(Role.FREELANCER);
        roles.add(Role.CLIENT);
    }

    @Override
    public String exec(final HttpServletRequest request,
                       final HttpServletResponse response)
            throws ServiceException {
        int id = Integer.parseInt(request.getParameter("userid"));
        UserService userService = (UserService) factory.getService(ServiceName.USER_SERVICE);
        CategoryService catService = (CategoryService) factory.getService(ServiceName.CATEGORY_SERVICE);
        SkillService skillService = (SkillService) factory.getService(ServiceName.SKILL_SERVICE);
        User user = (User) request.getSession().getAttribute("user");
        List<Category> userSkills = skillService.findUserSkills(user);
        List<Category> allCategories = catService.findAll();
        List<Category> lastCategories = new ArrayList<>();
        for (Category category : allCategories) {
            boolean is = false;
            for (Category cat : userSkills) {
                if (cat.getId() == category.getId()) {
                    is = true;
                }
            }
            if (!is) {
                lastCategories.add(category);
            }
        }
        request.setAttribute("skills", userSkills);
        request.setAttribute("lastcategories", lastCategories);
        request.setAttribute("user", userService.read(id).get());
        return "To edit profile.";
    }
}
