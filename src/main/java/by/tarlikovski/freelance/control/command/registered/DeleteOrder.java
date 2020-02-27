package by.tarlikovski.freelance.control.command.registered;

import by.tarlikovski.freelance.bean.Order;
import by.tarlikovski.freelance.bean.ServiceName;
import by.tarlikovski.freelance.bean.User;
import by.tarlikovski.freelance.control.command.Command;
import by.tarlikovski.freelance.service.OrderService;
import by.tarlikovski.freelance.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class DeleteOrder extends Command {

    public DeleteOrder() {
        setAddress("/index");
    }

    @Override
    public String exec(final HttpServletRequest request,
                       final HttpServletResponse response)
            throws ServiceException {
        OrderService orderService = (OrderService) factory.getService(ServiceName.ORDER_SERVICE);
        int id = Integer.parseInt(request.getParameter("orderid"));
        User curUser = (User) request.getSession().getAttribute("user");
        Optional<Order> order = orderService.read(id);
        if (order.isPresent() && order.get().getClient().getId() == curUser.getId()) {
            orderService.delete(id);
            String path = request.getContextPath() + "/home.html";
            request.setAttribute("url", path);
            return "Redirect";
        } else {
            request.setAttribute("error", "noaccess");
            setAddress("/error");
            return "Error";
        }
    }
}
