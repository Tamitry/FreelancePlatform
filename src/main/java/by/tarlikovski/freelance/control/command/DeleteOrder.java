package by.tarlikovski.freelance.control.command;

import by.tarlikovski.freelance.bean.ServiceName;
import by.tarlikovski.freelance.bean.Work;
import by.tarlikovski.freelance.service.OrderService;
import by.tarlikovski.freelance.service.ServiceException;
import by.tarlikovski.freelance.service.UserService;
import by.tarlikovski.freelance.service.WorkService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        orderService.delete(id);
        return "To index page.";
    }
}
