package by.tarlikovski.freelance.control.command.registered;

import by.tarlikovski.freelance.bean.*;
import by.tarlikovski.freelance.control.command.Command;
import by.tarlikovski.freelance.service.OrderService;
import by.tarlikovski.freelance.service.ServiceException;
import by.tarlikovski.freelance.service.UserService;
import by.tarlikovski.freelance.service.WorkService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.Set;

public class Subscribe extends Command {

    public Subscribe() {
        setAddress("/order");
        Set<Role> roles = getRoles();
        roles.add(Role.FREELANCER);
    }
    @Override
    public String exec(final HttpServletRequest request,
                       final HttpServletResponse response)
            throws ServiceException {
        OrderService orderService = (OrderService) factory.getService(ServiceName.ORDER_SERVICE);
        WorkService workService = (WorkService) factory.getService(ServiceName.WORK_SERVICE);
        UserService userService = (UserService) factory.getService(ServiceName.USER_SERVICE);
        int orderId = Integer.parseInt(request.getParameter("orderid"));
        User user = (User) request.getSession().getAttribute("user");
        if (user != null && orderService.read(orderId).isPresent()) {
            int userId = user.getId();
            Work work = new Work();
            work.setOrder(orderService.read(orderId).get());
            work.setUser(userService.read(userId).get());
            work.setStatus(Status.NOT_CONFIRMED);
            work.setGrade((byte) 5);
            workService.create(work);
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
