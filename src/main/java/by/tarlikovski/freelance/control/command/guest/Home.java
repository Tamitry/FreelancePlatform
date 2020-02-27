package by.tarlikovski.freelance.control.command.guest;

import by.tarlikovski.freelance.bean.Order;
import by.tarlikovski.freelance.bean.ServiceName;
import by.tarlikovski.freelance.control.command.Command;
import by.tarlikovski.freelance.service.OrderService;
import by.tarlikovski.freelance.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class Home extends Command {
    public Home() {
        setAddress("/index");
    }

    @Override
    public String exec(final HttpServletRequest request,
                       final HttpServletResponse response)
            throws ServiceException {
        OrderService orderService = (OrderService) factory.getService(ServiceName.ORDER_SERVICE);
        int days = request.getParameter("days") != null
        && request.getParameter("days").matches("\\d\\d?") ?
                Integer.parseInt(request.getParameter("days")) :
                7;
        List<Order> orders = orderService.findNewest(days);
        request.setAttribute("listoforders", orders);
        return "To homepage.";
    }
}
