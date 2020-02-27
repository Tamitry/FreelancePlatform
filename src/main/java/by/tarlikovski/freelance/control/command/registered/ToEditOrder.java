package by.tarlikovski.freelance.control.command.registered;

import by.tarlikovski.freelance.bean.*;
import by.tarlikovski.freelance.control.command.Command;
import by.tarlikovski.freelance.service.CategoryService;
import by.tarlikovski.freelance.service.OrderPropertyService;
import by.tarlikovski.freelance.service.OrderService;
import by.tarlikovski.freelance.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ToEditOrder extends Command {

    public ToEditOrder() {
        setAddress("/editorder");
        Set<Role> roleSet = getRoles();
        roleSet.add(Role.CLIENT);
    }

    @Override
    public String exec(final HttpServletRequest request,
                       final HttpServletResponse response)
            throws ServiceException {
        int id = Integer.parseInt(request.getParameter("orderid"));
        OrderService orderService = (OrderService) factory.getService(ServiceName.ORDER_SERVICE);
        CategoryService catService = (CategoryService) factory.getService(ServiceName.CATEGORY_SERVICE);
        OrderPropertyService propertyService = (OrderPropertyService) factory.getService(ServiceName.ORDER_PROPERTY_SERVICE);
        User user = (User) request.getSession().getAttribute("user");
        if (orderService.read(id).isPresent()
                && orderService.read(id).get().getClient().getId() == user.getId()) {
            Order order = orderService.read(id).get();
            List<Category> orderCategories = propertyService.findByOrder(order);
            List<Category> allCategories = catService.findAll();
            List<Category> lastCategories = new ArrayList<>();
            for (Category category : allCategories) {
                boolean is = false;
                for (Category cat : orderCategories) {
                    if (cat.getId() == category.getId()) {
                        is = true;
                    }
                }
                if (!is) {
                    lastCategories.add(category);
                }
            }
            String date = order.getOrderDeadLine().toString();
            date = date.split(" ")[0];
            request.setAttribute("properties", orderCategories);
            request.setAttribute("lastcategories", lastCategories);
            request.setAttribute("order", order);
            request.setAttribute("date", date);
            request.getSession().setAttribute("orderid", order.getId());
            return "To edit profile.";
        } else {
            request.setAttribute("error", "noaccess");
            setAddress("/error");
            return "Error";
        }
    }
}
