package by.tarlikovski.freelance.control.command;

import by.tarlikovski.freelance.bean.Category;
import by.tarlikovski.freelance.bean.Order;
import by.tarlikovski.freelance.bean.ServiceName;
import by.tarlikovski.freelance.service.CategoryService;
import by.tarlikovski.freelance.service.OrderPropertyService;
import by.tarlikovski.freelance.service.OrderService;
import by.tarlikovski.freelance.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class FilterOrders extends Command {

    public FilterOrders() {
        setAddress("/orderlist");
    }

    @Override
    public String exec(final HttpServletRequest request,
                       final HttpServletResponse response)
            throws ServiceException {
        OrderService orderService = (OrderService) factory.getService(ServiceName.ORDER_SERVICE);
        OrderPropertyService propertyService =
                (OrderPropertyService) factory.getService(ServiceName.ORDER_PROPERTY_SERVICE);
        CategoryService categoryService =
                (CategoryService) factory.getService(ServiceName.CATEGORY_SERVICE);
        String filter = request.getParameter("filter");
        List<Order> orders = new ArrayList<>();
        List<Category> categories = categoryService.findAll();
        if (filter.equals("")) {
            orders = orderService.findAll();
        } else {
            Category filterCategory = null;
            for (Category category : categories) {
                if (category.getName().equals(filter)) {
                    filterCategory = category;
                }
            }
            if (filterCategory != null) {
                orders = propertyService.findByCategory(filterCategory);
            }
        }
        for (Order order : orders) {
            if (order.getDescription().length() >= 100) {
                order.setDescription(order.getDescription().substring(0, 100) + "...");
            }
        }
        request.setAttribute("categories", categories);
        request.setAttribute("orders", orders);
        request.setAttribute("filter", filter);
        return "Update list";
    }
}
