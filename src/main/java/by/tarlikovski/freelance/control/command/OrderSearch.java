package by.tarlikovski.freelance.control.command;

import by.tarlikovski.freelance.bean.Order;
import by.tarlikovski.freelance.bean.ServiceName;
import by.tarlikovski.freelance.service.CategoryService;
import by.tarlikovski.freelance.service.OrderService;
import by.tarlikovski.freelance.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class OrderSearch extends Command {
    public OrderSearch() {
        setAddress("/orderlist");
    }

    @Override
    public String exec(final HttpServletRequest request,
                       final HttpServletResponse response)
            throws ServiceException {
        OrderService orderService = (OrderService) factory.getService(ServiceName.ORDER_SERVICE);
        CategoryService categoryService =
                (CategoryService) factory.getService(ServiceName.CATEGORY_SERVICE);
        String search = request.getParameter("searchuser");
        List<Order> orders = orderService.findByName(search);
        for (Order order : orders) {
            if (order.getDescription().length() >= 100) {
                order.setDescription(order.getDescription().substring(0, 100) + "...");
            }
        }
        request.setAttribute("categories", categoryService.findAll());
        request.setAttribute("orders", orders);
        return "Orders search.";
    }
}
