package by.tarlikovski.freelance.control.command.registered;

import by.tarlikovski.freelance.bean.*;
import by.tarlikovski.freelance.control.command.Command;
import by.tarlikovski.freelance.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ToProfile extends Command {
    public ToProfile() {
        setAddress("/profile");
    }

    @Override
    public String exec(final HttpServletRequest request,
                       final HttpServletResponse response)
            throws ServiceException {
        int id = Integer.parseInt(request.getParameter("userid"));
        UserService userService = (UserService) factory.getService(ServiceName.USER_SERVICE);
        SkillService skillService = (SkillService) factory.getService(ServiceName.SKILL_SERVICE);
        OrderService orderService = (OrderService) factory.getService(ServiceName.ORDER_SERVICE);
        WorkService workService = (WorkService) factory.getService(ServiceName.WORK_SERVICE);
        if (userService.read(id).isPresent()) {
            User user = userService.read(id).get();
            List<Category> categories = skillService.findUserSkills(user);
            List<Order> orders = orderService.findByUser(user);
            List<Work> works = workService.findByUser(user);
            request.setAttribute("user", user);
            request.setAttribute("categories", categories);
            request.setAttribute("orders1", orders);
            request.setAttribute("works", works);
            return "To profile";
        } else {
            request.setAttribute("error", "wrongid");
            setAddress("/error");
            return "Error";
        }
    }
}
