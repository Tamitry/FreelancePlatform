package by.tarlikovski.freelance.control.command;

import by.tarlikovski.freelance.bean.*;
import by.tarlikovski.freelance.service.OrderService;
import by.tarlikovski.freelance.service.ServiceException;
import by.tarlikovski.freelance.service.UserService;
import by.tarlikovski.freelance.service.WorkService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class ToOrder extends Command {
    public ToOrder() {
        setAddress("/order");
    }
    @Override
    public String exec(final HttpServletRequest request,
                       final HttpServletResponse response)
            throws ServiceException {
        OrderService orderService = (OrderService) factory.getService(ServiceName.ORDER_SERVICE);
        WorkService workService = (WorkService) factory.getService(ServiceName.WORK_SERVICE);
        UserService userService = (UserService) factory.getService(ServiceName.USER_SERVICE);
        int orderid = Integer.parseInt(request.getParameter("orderid"));
        Order order = orderService.read(orderid).get();
        request.setAttribute("order", order);
        List<Work> works = workService.findByOrder(order);
        List<Work> unconfWorks = new ArrayList<>();
        List<Work> confWorks = new ArrayList<>();
        boolean subscribe = false;
        for (Work work : works) {
            if (work.getStatus().getStatusNum() == Status.NOT_CONFIRMED.getStatusNum()) {
                unconfWorks.add(work);
            }
            if (work.getStatus().getStatusNum() == Status.CONFIRMED.getStatusNum()) {
                confWorks.add(work);
            }
            if (work.getUser().getId() == ((User) request.getSession().getAttribute("curuser")).getId()) {
                subscribe = true;
            }
        }
        request.setAttribute("confworks", confWorks);
        request.setAttribute("subscribe", subscribe);
        request.setAttribute("unconfworks", unconfWorks);
        return "To order";
    }
}
