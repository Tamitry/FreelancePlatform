package by.tarlikovski.freelance.control.command;

import by.tarlikovski.freelance.bean.Category;
import by.tarlikovski.freelance.bean.ServiceName;
import by.tarlikovski.freelance.bean.User;
import by.tarlikovski.freelance.exception.PersistentException;
import by.tarlikovski.freelance.service.Service;
import by.tarlikovski.freelance.service.SkillService;
import by.tarlikovski.freelance.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ToProfile extends Command {
    public ToProfile() {
        setName("/profile");
    }

    @Override
    public String exec(final HttpServletRequest request,
                       final HttpServletResponse response)
            throws PersistentException {
        int id = Integer.parseInt(request.getParameter("userid"));
        UserService userService = (UserService) factory.getService(ServiceName.USER_SERVICE);
        SkillService skillService = (SkillService) factory.getService(ServiceName.SKILL_SERVICE);
        User user = userService.findById(id).get();
        List<Category> categories = skillService.findUserSkills(user);
        request.setAttribute("user", user);
        request.setAttribute("categories", categories);
        return "To profile";
    }
}
