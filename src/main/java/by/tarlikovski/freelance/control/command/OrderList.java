package by.tarlikovski.freelance.control.command;

import by.tarlikovski.freelance.bean.Order;
import by.tarlikovski.freelance.bean.ServiceName;
import by.tarlikovski.freelance.service.OrderService;
import by.tarlikovski.freelance.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class OrderList extends Command {
    public OrderList() {
        setAddress("/orderlist");
    }

    @Override
    public String exec(final HttpServletRequest request,
                       final HttpServletResponse response)
            throws ServiceException {
        //UserService userService = (UserService) factory.getService(ServiceName.USER_SERVICE);
        OrderService orderService = (OrderService) factory.getService(ServiceName.ORDER_SERVICE);
        List<Order> orders = orderService.findAll();
        for (Order order : orders) {
            if (order.getDescription().length() >= 100) {
                order.setDescription(order.getDescription().substring(0, 100) + "...");
            }
        }
        /*List<String> users = new ArrayList<>();
        for (Order order : orders) {
            Optional<User> client = userService.findById(order.getClientId());
            if (client.isPresent()) {
                users.add(client.get().getFirstName() + " " + client.get().getLastName());
            }
        }
        request.setAttribute("users", users);*/
        request.setAttribute("orders", orders);
        return "Users list.";
    }
}
