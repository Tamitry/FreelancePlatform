package by.tarlikovski.freelance.control.command.guest;

import by.tarlikovski.freelance.bean.*;
import by.tarlikovski.freelance.control.command.Command;
import by.tarlikovski.freelance.service.*;

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
        OrderPropertyService propertyService = (OrderPropertyService) factory
                .getService(ServiceName.ORDER_PROPERTY_SERVICE);
        int orderid = Integer.parseInt(request.getParameter("orderid"));
        if (orderService.read(orderid).isPresent()) {
            Order order = orderService.read(orderid).get();
            request.setAttribute("order", order);
            List<Work> works = workService.findByOrder(order);
            List<Work> unconfWorks = new ArrayList<>();
            List<Work> confWorks = new ArrayList<>();
            List<Category> categories = propertyService.findByOrder(order);
            boolean subscribe = false;
            for (Work work : works) {
                if (work.getStatus().getStatusNum() == Status.NOT_CONFIRMED.getStatusNum()) {
                    unconfWorks.add(work);
                }
                if (work.getStatus().getStatusNum() == Status.CONFIRMED.getStatusNum()) {
                    confWorks.add(work);
                }
                if (request.getSession().getAttribute("user") != null
                        && work.getUser().getId() == ((User) request.getSession().getAttribute("user")).getId()) {
                    subscribe = true;
                }
            }
            request.setAttribute("confworks", confWorks);
            request.setAttribute("subscribe", subscribe);
            request.setAttribute("unconfworks", unconfWorks);
            request.setAttribute("properties", categories);
            return "To order";
        } else {
            request.setAttribute("error", "wrongid");
            setAddress("/error");
            return "Error";
        }
    }
}
