package by.tarlikovski.freelance.control.command;

import by.tarlikovski.freelance.bean.*;
import by.tarlikovski.freelance.service.CategoryService;
import by.tarlikovski.freelance.service.OrderPropertyService;
import by.tarlikovski.freelance.service.OrderService;
import by.tarlikovski.freelance.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

public class AddOrder extends Command {
    public AddOrder() {
        setAddress("/order");
        Set<Role> roleSet = getRoles();
        roleSet.add(Role.CLIENT);
    }

    @Override
    public String exec(final HttpServletRequest request,
                       final HttpServletResponse response)
            throws ServiceException {
        OrderService orderService = (OrderService) factory
                .getService(ServiceName.ORDER_SERVICE);
        OrderPropertyService propertyService = (OrderPropertyService) factory
                .getService(ServiceName.ORDER_PROPERTY_SERVICE);
        CategoryService categoryService = (CategoryService) factory
                .getService(ServiceName.CATEGORY_SERVICE);
        String name = request.getParameter("ordername");
        String date = request.getParameter("deadline");
        String desc = request.getParameter("desc");
        List<Category> categories = categoryService.findAll();
        Order order = new Order();
        order.setClient((User) request.getSession().getAttribute("user"));
        order.setDescription(desc);
        order.setOrderDeadLine(Timestamp.valueOf(date + " 00:00:00"));
        order.setOrderName(name);
        orderService.create(order);
        for (Category category : categories) {
            if(request.getParameter("" + category.getId()) != null) {
                propertyService.create(order, category);
            }
        }
        String path = request.getHeader("Referer");
        request.setAttribute("url", path);
        return "Redirect";
    }
}
