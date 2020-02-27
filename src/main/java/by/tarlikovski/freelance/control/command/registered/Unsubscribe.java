package by.tarlikovski.freelance.control.command.registered;

import by.tarlikovski.freelance.bean.Role;
import by.tarlikovski.freelance.bean.ServiceName;
import by.tarlikovski.freelance.bean.User;
import by.tarlikovski.freelance.bean.Work;
import by.tarlikovski.freelance.control.command.Command;
import by.tarlikovski.freelance.service.OrderService;
import by.tarlikovski.freelance.service.ServiceException;
import by.tarlikovski.freelance.service.UserService;
import by.tarlikovski.freelance.service.WorkService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

public class Unsubscribe extends Command {

    public Unsubscribe() {
        setAddress("/order");
        Set<Role> roles = getRoles();
        roles.add(Role.FREELANCER);
    }

    @Override
    public String exec(final HttpServletRequest request,
                       final HttpServletResponse response)
            throws ServiceException {
        WorkService workService = (WorkService) factory.getService(ServiceName.WORK_SERVICE);
        UserService userService = (UserService) factory.getService(ServiceName.USER_SERVICE);
        OrderService orderService = (OrderService) factory.getService(ServiceName.ORDER_SERVICE);
        int orderId = Integer.parseInt(request.getParameter("orderid"));
        User user = (User) request.getSession().getAttribute("user");
        if (user != null && orderService.read(orderId).isPresent()) {
            List<Work> works = workService.findByUser(user);
            for (Work work : works) {
                if (work.getOrder().getId() == orderId) {
                    workService.delete(work.getId());
                }
            }
            String path = request.getHeader("Referer");
            request.setAttribute("url", path);
            return "Redirect";
        } else {
            request.setAttribute("error", "noaccess");
            setAddress("/error");
            return "Error";
        }
    }
}
